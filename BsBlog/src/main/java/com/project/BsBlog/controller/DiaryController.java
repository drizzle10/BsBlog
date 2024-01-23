package com.project.BsBlog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.BsBlog.handler.FTPHandler;
import com.project.BsBlog.service.DiaryService;
import com.project.BsBlog.vo.DiaryVO;
import com.project.BsBlog.vo.PageInfo;

@Controller
public class DiaryController {
	@Autowired
	public DiaryService service;
	
	private FTPHandler ftp = new FTPHandler();
	
	// board/diary.jsp
	@GetMapping(value = "/diary.bo")
	public String diary(@RequestParam(defaultValue = "") String searchType,
						@RequestParam(defaultValue = "") String keyword,
						@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNum : " + pageNum);
		
		int listLimit = 10; 
		
		int pageListLimit = 10; 

		int startRow = (pageNum - 1) * listLimit;
		
		// 글 목록 조회
		List<DiaryVO> diary = service.selectDiary(startRow, listLimit, searchType, keyword);
		
		// 글 갯수 조회
		int listCount = service.selectDiaryCount(searchType, keyword);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		int endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("diary", diary);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "board/diary";
	}
	
	// board/diary_write.jsp
	@GetMapping(value = "/diary_write.bo")
	public String diary_write() {
		return "board/diary_write";
	}
	
	@PostMapping(value = "/diary_writePro.bo")
	public String diary_writePro(@ModelAttribute DiaryVO diary, Model model, HttpSession session) {
		
		System.out.println(diary);
		
		// 주의! 파일 업로드 기능을 통해 전달받은 파일 객체를 다루기 위해서는
		// BoardVO 클래스 내에 MultipartFile 타입 변수와 Getter/Setter 정의 필수!
		// => input type="file" 태그의 name 속성과 동일한 변수명 사용해야함
//		System.out.println(board.getFile());
		
		// 가상 업로드 경로에 대한 실제 업로드 경로 알아내기
		// => 단, request 객체에 getServletContext() 메서드 대신, session 객체로 동일한 작업 수행
		//    (request 객체에 해당 메서드 없음)
		String uploadDir = "/resources/upload"; // 가상의 업로드 경로
		// => webapp/resources 폴더 내에 upload 폴더 생성 필요
		String saveDir = session.getServletContext().getRealPath(uploadDir);
//		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir); // 실제 경로를 갖는 File 객체 생성
		// 만약, 해당 경로 상에 디렉토리(폴더)가 존재하지 않을 경우 생성
		if(!f.exists()) { // 해당 경로가 존재하지 않을 경우
			// 경로 상의 존재하지 않는 모든 경로 생성
			f.mkdirs();
		}
		
//		// BoardVO 객체에 전달된 MultipartFile 객체 꺼내기
		MultipartFile mFile = diary.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		// 파일명 중복 방지를 위한 대책
		// 시스템에서 랜덤ID 값을 추출하여 파일명 앞에 붙여 "랜덤ID값_파일명" 형식으로 설정
		// 랜덤ID 는 UUID 클래스 활용(UUID : 범용 고유 식별자)
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		// BoardVO 객체에 원본 파일명과 업로드 될 파일명 저장
		// => 단, uuid 를 결합한 파일명을 사용할 경우 원본 파일명과 실제 파일명을 구분할 필요 없이
		//    하나의 컬럼에 저장해두고, 원본 파일명이 필요할 경우 "_" 를 구분자로 지정하여
		//    문자열을 분리하면 두번째 파라미터가 원본 파일명이 된다!
		diary.setDiary_file(originalFileName); // 실제로는 불필요한 컬럼
		diary.setDiary_realfile(uuid + "_" + originalFileName);
		
		int insertCount = service.writeDiaryPro(diary);
		
		if(insertCount > 0) {
			try {
				// FTP 접속
				String ftpBaseDir = "/upload";
				ftp.connect(ftpBaseDir);
				System.out.println("ftp접속 완");
				
				// ----------------------- 톰캣 업로드 --------------------------
				// 파일 등록 작업 성공 시 톰캣의 실제 폴더 위치에 파일 업로드 수행
				// => MultipartFile 객체의 transferTo() 메서드를 호출하여 파일 업로드 작업 수행
				//    (파라미터 : new File(업로드 경로, 업로드 할 파일명))
				File f2 = new File(saveDir, diary.getDiary_realfile());
				mFile.transferTo(f2);

				System.out.println("톰캣 업로드 완");
				// ----------------------- FTP 업로드 --------------------------
				// FileInputStream 객체를 생성하여 File 객체(f2) 입력스트림에 연결
				// => 톰캣 폴더에 업로드 된 파일을 입력스트림에 연결하여 FTP 서버로 전송(= 출력)
				ftp.upload(f2, diary.getDiary_realfile());
				
				System.out.println("ftp업로드 완");
				
				// ------------- FTP 업로드 후 톰캣 업로드 파일 삭제 시 ------------
				if(f2.exists()) {
					f2.delete();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// FTP 접속 해제
				ftp.disconnect();
			}
			
			return "redirect:/diary.bo";
		} else {
			model.addAttribute("msg", "글 쓰기 실패!");
			return "board/fail_back";
		}
		
	}
	
	//board/diary_detail.jsp
	@GetMapping(value = "/diary_detail.bo")
	public String diary_detail(@RequestParam int diary_num, @RequestParam int pageNum, Model model) {
		
		// 글 상세 조회
		DiaryVO diaryDetail = service.selectDiaryDetail(diary_num);
		
		// 글 조회시 조회수 증가
		service.increaseDiaryReadCount(diary_num);
		
		model.addAttribute("diaryDetail", diaryDetail);
		
		return "board/diary_detail";
	}
	
	// board/diary_modify.jsp
	@GetMapping(value = "/diary_modify.bo")
		public String diary_modify(@RequestParam int diary_num, Model model) {
		
		// 글 수정시 원본글 뿌리기	
		DiaryVO diaryDetail = service.selectDiaryDetail(diary_num);
		
		model.addAttribute("diaryDetail", diaryDetail);
		return "board/diary_modify";
	}
	
	// TODO
	// diary_modifyPro 
	// diary_delete + diary_deletePro
	// fail_back 안먹어서 수정해야함
	// 파일 썸네일 ㅠㅠㅠ
	
		
}
