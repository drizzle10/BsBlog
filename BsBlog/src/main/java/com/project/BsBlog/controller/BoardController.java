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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.BsBlog.handler.FTPHandler;
import com.project.BsBlog.service.BoardService;
import com.project.BsBlog.vo.DiaryVO;
import com.project.BsBlog.vo.NewsVO;
import com.project.BsBlog.vo.NoteVO;
import com.project.BsBlog.vo.PageInfo;

@Controller
public class BoardController {
	@Autowired
	public BoardService service;
	
	private FTPHandler ftp = new FTPHandler();

	/*
	private int pageNum; // 현재 페이지 번호
	private int listLimit; // 페이지 당 게시물 목록 갯수
	private int listCount; // 총 게시물 수
	private int pageListLimit; // 페이지 당 표시할 페이지 번호 수
	private int maxPage; // 전체 페이지 수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호
	*/
	
	int listLimit;
	int pageListLimit;
	int startRow;
	int listCount;
	int maxPage;
	int startPage;
	int endPage;
	
	// ----- diary -----
	// board/diary.jsp
	@GetMapping(value = "/diary.bo")
	public String diary(@RequestParam(defaultValue = "") String searchType,
						@RequestParam(defaultValue = "") String keyword,
						@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNum : " + pageNum);
		
		listLimit = 10; 
		
		pageListLimit = 10; 

		startRow = (pageNum - 1) * listLimit;
		
		// 글 목록 조회
		List<DiaryVO> diary = service.selectDiary(startRow, listLimit, searchType, keyword);
		System.out.println("diary : " + diary);
		
		// 글 갯수 조회
		listCount = service.selectDiaryCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		endPage = startPage + pageListLimit - 1;

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
	public String diary_writePro(@ModelAttribute DiaryVO diary, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
		
		System.out.println("diary : " + diary);
		
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir); 
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = diary.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		diary.setDiary_file(originalFileName);
		diary.setDiary_realfile(uuid + "_" + originalFileName);
		
		// 글 작성
		int insertCount = service.writeDiaryPro(diary);
		
		if(insertCount > 0) {
			try {
				String ftpBaseDir = "/upload";
				ftp.connect(ftpBaseDir);
				
				System.out.println("ftp접속 완");
				
				File f2 = new File(saveDir, diary.getDiary_realfile());
				mFile.transferTo(f2);

				System.out.println("톰캣 업로드 완");

				ftp.upload(f2, diary.getDiary_realfile());
				
				System.out.println("ftp업로드 완");
				
				if(f2.exists()) {
					f2.delete();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				ftp.disconnect();
			}
			
			return "redirect:/diary.bo?pageNum=" + pageNum + "&sId=" + sId;
		} else {
			model.addAttribute("msg", "글 작성이 실패되었습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		}
		
	}
	
	//board/diary_detail.jsp
	@GetMapping(value = "/diary_detail.bo")
	public String diary_detail(@RequestParam int diary_num, @RequestParam int pageNum, Model model, HttpSession session ) {
		
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
	
	@PostMapping(value = "/diary_modifyPro.bo")
	public String diary_modifyPro(@ModelAttribute DiaryVO diary, 
								@RequestParam int diary_num, @RequestParam int pageNum, @RequestParam String sId,
									Model model, HttpSession session) {
		System.out.println("diary : " + diary);
		System.out.println("diary_num : " + diary_num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("기존 파일명 : " + diary.getDiary_file());
		System.out.println("기존 실제 파일명 : " + diary.getDiary_realfile());
		System.out.println("새 파일 객체 : " + diary.getFile());
		System.out.println("새 파일명 : " + diary.getFile().getOriginalFilename());
		
		String oldRealFile = diary.getDiary_realfile();
		
		String uploadDir = "/resources/upload/upload";
		
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir);
		
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = diary.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		diary.setDiary_file(originalFileName);
		diary.setDiary_realfile(uuid + "_" + originalFileName);
		
		// 글 수정
		int updateCount = service.modifyDiaryPro(diary);
		
		if(updateCount == 0) { // 수정 실패 시
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		} else { // 수정 성공 시
			if(!originalFileName.equals("")) {
				try {
					mFile.transferTo(new File(saveDir, diary.getDiary_realfile()));
					
					File f2 = new File(saveDir, oldRealFile);
					if(f2.exists()) {
						f2.delete();
					}
				} catch (IllegalStateException e) {
					System.out.println("IllegalStateException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
				}
			}
			return "redirect:/diary_detail.bo?diary_num=" + diary.getDiary_num() + "&pageNum=" + pageNum + "&sId=" + sId;
		}
	}
	
	// board/diary_delete.jsp
	@GetMapping(value = "/diary_delete.bo")
	public String diary_delete() {
		return "board/diary_delete";
	}
	
	@PostMapping(value = "/diary_deletePro.bo")
	public String diary_deletePro(@ModelAttribute DiaryVO diary, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
	
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectDiaryRealFile(diary.getDiary_num());
		
		// 글 삭제
		int deleteCount = service.deleteDiaryPro(diary);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		} else {
			String uploadDir = "/resources/upload/upload";
			String saveDir = session.getServletContext().getRealPath(uploadDir);
			System.out.println("실제 업로드 경로 : " + saveDir);
			
			File f = new File(saveDir, realFile);
			if(f.exists()) { 
				f.delete();
			}
			
			return "redirect:/diary.bo?pageNum=" + pageNum + "&sId=" + sId;
		}
	}
	
	@GetMapping(value = "/diaryFileDownload")
	public String diaryFiledownload(@RequestParam String fileName, @RequestParam int diary_num, @RequestParam int pageNum, @RequestParam String sId, HttpSession session) {
		System.out.println("fileName : " + fileName);
		System.out.println("diary_num : " + diary_num);
		System.out.println("pageNum : " + pageNum);
		
		String ftpBaseDir = "/upload";
		ftp.connect(ftpBaseDir);
		
		String uploadDir = "/resources/upload/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir, fileName); 
		
		if(!f.exists()) {
			System.out.println("f.exists() : " + f.exists());
			ftp.download(f, fileName);
		}
		
		ftp.disconnect();
			
		return "redirect:/diary_detail.bo?diary_num=" + diary_num + "&pageNum=" + pageNum + "sId=" + sId;
		
	}
	
	// ----- note -----
	// board/note.jsp
	@GetMapping(value = "/note.bo")
	public String note(@RequestParam(defaultValue = "") String searchType, 
						@RequestParam(defaultValue = "") String keyword,
						@RequestParam(defaultValue = "1") int pageNum, Model model) {
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNum : " + pageNum);
		
		listLimit = 10; 
		
		pageListLimit = 10; 

		startRow = (pageNum - 1) * listLimit;
		
		// 글 목록 조회
		List<NoteVO> note = service.selectNote(startRow, listLimit, searchType, keyword);
		System.out.println("note : " + note);
		
		// 글 목록 갯수 조회
		listCount = service.selectNoteCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("note", note);
		model.addAttribute("pageInfo", pageInfo);
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "board/note";
	}
	
	// board/note_write.jsp
	@GetMapping(value = "/note_write.bo")
	public String note_write() {
		return "board/note_write";
	}
	
	@PostMapping(value = "/note_writePro.bo")
	public String note_writePro(@ModelAttribute NoteVO note, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
		
		System.out.println(note);
		
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir); 
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = note.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		note.setNote_file(originalFileName);
		note.setNote_realfile(uuid + "_" + originalFileName);
		
		// 글 작성
		int insertCount = service.writeNotePro(note);
		
		if(insertCount > 0) {
			try {
				String ftpBaseDir = "/upload";
				ftp.connect(ftpBaseDir);
				
				System.out.println("ftp접속 완");
				
				File f2 = new File(saveDir, note.getNote_realfile());
				mFile.transferTo(f2);

				System.out.println("톰캣 업로드 완");

				ftp.upload(f2, note.getNote_realfile());
				
				System.out.println("ftp업로드 완");
				
				if(f2.exists()) {
					f2.delete();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				ftp.disconnect();
			}
			
			return "redirect:/note.bo?pageNum=" + pageNum + "&sId=" + sId;
		} else {
			model.addAttribute("msg", "글 작성이 실패되었습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		}
		
	}
	
	//board/note_detail.jsp
	@GetMapping(value = "/note_detail.bo")
	public String note_detail(@RequestParam int note_num, @RequestParam int pageNum, Model model, HttpSession session) {
		
		String sId = (String) session.getAttribute("sId");
		int heart;
		
		// 글 상세 조회
		NoteVO noteDetail = service.selectNoteDetail(note_num);
		
		// 글 조회시 조회수 증가
		service.increaseNoteReadCount(note_num);
		
		model.addAttribute("noteDetail", noteDetail);
		
		/*
		 * heart1.png(좋아요) = 꽉찬 하트
		 * heart2.png(안좋아요) =  빈 하트
		 * */
		
		// note_detail 들어가면 보이는 좋아요 출력
		if(sId == null) { // 비회원
			heart = 2;
			model.addAttribute("heart", heart);
		} else { // 회원
			int result = service.selectHeart(sId, note_num);
			if(result == 0) { // 좋아요 조회 결과 좋아요 한게 없다면
				heart = 2; // 빈 하트 출력
				model.addAttribute("heart", heart);
			} else { // 좋아요 조회 결과 좋아요 한게 있다면
				heart = 1; // 꽉찬 하트 출력
				model.addAttribute("heart", heart);
			}
		}
		
		return "board/note_detail";
	}
	
	@ResponseBody
	@GetMapping(value = "/addHeart")
	public int addHeart(@RequestParam int note_num, HttpSession session, Model model) {
		String sId = (String) session.getAttribute("sId");
		int heart;
		
		if(sId == null) { // 비회원
			System.out.println("아이디 없는자 좋아요 실패");
			heart = 2;
			return heart;
		} else { // 회원
			int insertCount = service.addHeart(sId, note_num); // 좋아요 추가
			
			if(insertCount == 0) { // 좋아요 추가 실패했다면
				System.out.println("좋아요 실패");
				heart = 2; // 빈 하트
				return heart;
			} else { // 좋아요 추가 성공했다면
				heart = 1; // 꽉찬 하트
				return heart;
			}
		}
		
	}

	@ResponseBody
	@GetMapping(value = "/deleteHeart")
	public int deleteHeart(@RequestParam int note_num, HttpSession session, Model model) {
		String sId = (String) session.getAttribute("sId");
		int heart;
		
		if(sId == null) { // 비회원
			System.out.println("아이디 없는자 안좋아요 실패");
			heart = 2; 
			return heart;
		} else { // 회원
			int deleteCount = service.deleteHeart(sId, note_num); // 좋아요 해제
			
			if(deleteCount == 0) { // 좋아요 해제 성공
				System.out.println("안좋아요 실패");
				heart = 1; // 빈 하트 출력
				return heart;
			} else { // 좋아요 해제 실패
				heart = 2;  // 꽉찬 하트 출력
				return heart;
			}
			
		}
		
	}
	
	// board/note_modify.jsp
	@GetMapping(value = "/note_modify.bo")
		public String note_modify(@RequestParam int note_num, Model model) {
		
		// 글 수정시 원본글 뿌리기	
		NoteVO noteDetail = service.selectNoteDetail(note_num);
		
		model.addAttribute("noteDetail", noteDetail);
		
		return "board/note_modify";
	}
	
	@PostMapping(value = "/note_modifyPro.bo")
	public String note_modifyPro(@ModelAttribute NoteVO note, @RequestParam int note_num, @RequestParam int pageNum, @RequestParam String sId,
									Model model, HttpSession session) {
		System.out.println("note : " + note);
		System.out.println("diary_num : " + note_num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("기존 파일명 : " + note.getNote_file());
		System.out.println("기존 실제 파일명 : " + note.getNote_realfile());
		System.out.println("새 파일 객체 : " + note.getFile());
		System.out.println("새 파일명 : " + note.getFile().getOriginalFilename());
		
		String oldRealFile = note.getNote_realfile();
		
		String uploadDir = "/resources/upload/upload";
		
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir);
		
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = note.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		note.setNote_file(originalFileName);
		note.setNote_realfile(uuid + "_" + originalFileName);
		
		// 글 수정
		int updateCount = service.modifyNotePro(note);
		
		if(updateCount == 0) { // 수정 실패 시
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		} else { // 수정 성공 시
			if(!originalFileName.equals("")) {
				try {
					mFile.transferTo(new File(saveDir, note.getNote_realfile()));
					
					File f2 = new File(saveDir, oldRealFile);
					if(f2.exists()) {
						f2.delete();
					}
				} catch (IllegalStateException e) {
					System.out.println("IllegalStateException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("IOException");
					e.printStackTrace();
				}
			}
			return "redirect:/note_detail.bo?note_num=" + note.getNote_num() + "&pageNum=" + pageNum + "&sId=" + sId;
		}
	}

	// board/note_delete.jsp
	@GetMapping(value = "/note_delete.bo")
	public String note_delete() {
		return "board/note_delete";
	}
	
	@PostMapping(value = "/note_deletePro.bo")
	public String note_deletePro(@ModelAttribute NoteVO note, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
	
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectNoteRealFile(note.getNote_num());
		
		// 글 삭제
		int deleteCount = service.deleteNotePro(note);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "board/fail_back";
		} else {
			String uploadDir = "/resources/upload/upload";
			String saveDir = session.getServletContext().getRealPath(uploadDir);
			System.out.println("실제 업로드 경로 : " + saveDir);
			
			File f = new File(saveDir, realFile);
			if(f.exists()) { 
				f.delete();
			}
			
			return "redirect:/note.bo?pageNum=" + pageNum + "&sId" + sId;
		}
	}
	
	@GetMapping(value = "/noteFileDownload")
	public String noteFiledownload(@RequestParam String fileName, @RequestParam int note_num, @RequestParam int pageNum, @RequestParam String sId, HttpSession session) {
		System.out.println("fileName : " + fileName);
		System.out.println("note_num : " + note_num);
		System.out.println("pageNum : " + pageNum);
		
		String ftpBaseDir = "/upload";
		ftp.connect(ftpBaseDir);
		
		String uploadDir = "/resources/upload/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir, fileName); 
		
		if(!f.exists()) {
			System.out.println("f.exists() : " + f.exists());
			ftp.download(f, fileName);
		}
		
		ftp.disconnect();
			
		return "redirect:/note_detail.bo?note_num=" + note_num + "&pageNum=" + pageNum + "sId" + sId;
		
	}
}
