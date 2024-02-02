package com.project.BsBlog.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.BsBlog.vo.PageInfo;
import com.project.BsBlog.vo.ReplyVO;
import com.project.BsBlog.handler.FTPHandler;
import com.project.BsBlog.service.InformationService;
import com.project.BsBlog.vo.NewsVO;

@Controller
public class InformationController {
	@Autowired
	private InformationService service;
	
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
	
	// information/welcome.jsp 
	// * 메소드에선 '/'붙고 html 파일에선 '/' 안 붙는 이유?
	@GetMapping(value = "/welcome.in")
	public String welcome() {
		return "information/welcome";
	}
	
	// information/career.jsp
	@GetMapping(value = "/career.in")
	public String career() {
		return "information/career";
	}
	
	// information/news.jsp
	@GetMapping(value = "/news.in")
	public String news(@RequestParam(defaultValue = "") String searchType,
						@RequestParam(defaultValue = "") String keyword,
						@RequestParam(defaultValue = "1") int pageNum,
						Model model) {
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNum : " + pageNum);
		
		int listLimit = 10; 
		
		int pageListLimit = 10; 

		int startRow = (pageNum - 1) * listLimit;
		
		System.out.println("startRow : " + startRow);
		// 글 목록 조회
		List<NewsVO> news = service.selectNews(startRow, listLimit, searchType, keyword);
		
		System.out.println("news : " + news);
		System.out.println("news 조회 완");
		// 글 목록 갯수 조회
		// * searchType과 keyword를 같이 보내야 하는 이유?
		int listCount = service.selectNewsCount(searchType, keyword);
		
		System.out.println("news 갯수 조회 완");
		System.out.println("listCount : " + listCount);
		// * 아래 식 정리
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		System.out.println("maxPage : " + maxPage);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		System.out.println("startPage : " + startPage);

		int endPage = startPage + pageListLimit - 1;
		System.out.println("endPage : " + endPage);

		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		System.out.println("페이지 계산 완");
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		System.out.println("pageInfo 객체 저장 완");
		System.out.println("pageInfo : " + pageInfo);
		
		model.addAttribute("news", news);
		model.addAttribute("pageInfo", pageInfo);
		
		System.out.println("news, pageInfo 모델에 저장");
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "information/news";
	}
	
	// information/news_write.jsp
	@GetMapping(value = "/news_write.in")
	public String news_write() {
		return "information/news_write";
	}
	
	// information/news_write.jsp
	// * 아래 전체 파악
	// * 글 작성시 wtpwebapps/upload/upload 폴더에 파일이 저장됨
	//   글 수정시 wtpwebapps/upload 폴더에 파일이 저장됨
	@PostMapping(value = "/news_writePro.in")
	public String news_writePro(@ModelAttribute NewsVO news, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
		System.out.println("news : " + news);
		
		// 주의! 파일 업로드 기능을 통해 전달받은 파일 객체를 다루기 위해서는
		// BoardVO 클래스 내에 MultipartFile 타입 변수와 Getter/Setter 정의 필수!
		// => input type="file" 태그의 name 속성과 동일한 변수명 사용해야함
//		System.out.println(board.getFile());
		
		// 가상 업로드 경로에 대한 실제 업로드 경로 알아내기
		// => 단, request 객체에 getServletContext() 메서드 대신, session 객체로 동일한 작업 수행
		//    (request 객체에 해당 메서드 없음)
		String uploadDir = "/resources/upload"; // 가상의 업로드 경로
		// => webapp/resources 폴더 내에 upload 폴더 생성 필요
		System.out.println("uploadDir : " + uploadDir);
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("saveDir : " + saveDir);
//		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir); // 실제 경로를 갖는 File 객체 생성
		System.out.println("@!#$!%%#@f : " + f);
		System.out.println("f.exists() : " + f.exists());
		// 만약, 해당 경로 상에 디렉토리(폴더)가 존재하지 않을 경우 생성
		if(!f.exists()) { // 해당 경로가 존재하지 않을 경우
			// 경로 상의 존재하지 않는 모든 경로 생성
			f.mkdirs();
		}
		
		System.out.println("새 파일 객체 : " + news.getFile());
		
//		// BoardVO 객체에 전달된 MultipartFile 객체 꺼내기
		MultipartFile mFile = news.getFile();
		
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
		news.setNews_file(originalFileName); // 실제로는 불필요한 컬럼
		news.setNews_realfile(uuid + "_" + originalFileName);
		
		int insertCount = service.writeNewsPro(news);
		
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
				File f2 = new File(saveDir, news.getNews_realfile());
				mFile.transferTo(f2);

				System.out.println("톰캣 업로드 완");
				// ----------------------- FTP 업로드 --------------------------
				// FileInputStream 객체를 생성하여 File 객체(f2) 입력스트림에 연결
				// => 톰캣 폴더에 업로드 된 파일을 입력스트림에 연결하여 FTP 서버로 전송(= 출력)
				ftp.upload(f2, news.getNews_realfile());
				System.out.println("!#$@^f2 : " + f2);
				System.out.println("ftp업로드 완");
				System.out.println("f2.exist() : " + f2.exists());
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
			
			return "redirect:/news.in?pageNum=" + pageNum + "&sId=" + sId;
		} else {
			model.addAttribute("msg", "글 작성이 실패되었습니다. 다시 시도해 주세요.");
			return "information/fail_back";
		}
		
	}
	
	// information/news_detail.jsp
	@GetMapping(value = "/news_detail.in")
	public String news_detail(@RequestParam int news_num, 
							Model model, HttpSession session) {
		
		// 글 상세 조회
		NewsVO newsDetail = service.selectNewsDetail(news_num);
		System.out.println("newsDetail : " + newsDetail);
		
		// 글 조회시 조회수 증가
		service.increaseNewsReadCount(news_num);
		
		List<ReplyVO> reply = service.selectReply(news_num);
		
		model.addAttribute("newsDetail", newsDetail);
		model.addAttribute("reply", reply);
		
		return "information/news_detail";
	}
	
	// information/news_modfiy.jsp
	@GetMapping(value = "/news_modify.in")
	public String news_modify(@RequestParam int news_num, Model model) {
		
		// 글 상세 조회 후 원본글 뿌리기
		NewsVO newsDetail = service.selectNewsDetail(news_num);
		
		model.addAttribute("newsDetail", newsDetail);
		
		return "information/news_modify";
	}
	
	// * 하나는 RequestParam 하고 하나는 안하면 매핑이 안되는 이유
	// 	* 글 작성시 wtpwebapps/upload/upload 폴더에 파일이 저장됨
	//    글 수정시 wtpwebapps/upload 폴더에 파일이 저장됨
	// * 파일 수정시 파일질라에는 수정하나 파일은 올라가지 않음	
	@PostMapping(value = "/news_modifyPro.in")
	public String news_modifyPro(@ModelAttribute NewsVO news, 
								@RequestParam int news_num, @RequestParam int pageNum, @RequestParam String sId,
								Model model, HttpSession session) {
		System.out.println("news : " + news);
		System.out.println("pageNum : " + pageNum);
		
		// 선택된 수정 업로드 파일명과 기존 파일명 출력
		System.out.println("기존 파일명 : " + news.getNews_file());
		System.out.println("기존 실제 파일명 : " + news.getNews_realfile());
		System.out.println("새 파일 객체 : " + news.getFile());
		System.out.println("새 파일명 : " + news.getFile().getOriginalFilename());
		
		// 기존 실제 파일명을 변수에 저장(= 새 파일 업로드 시 삭제하기 위함)
		String oldRealFile = news.getNews_realfile();
		
		// 가상 업로드 경로에 대한 실제 업로드 경로 알아내기
		// => 단, request 객체에 getServletContext() 메서드 대신, session 객체로 동일한 작업 수행
		//    (request 객체에 해당 메서드 없음)
		//************** 중요 *************
		// 아래 경로대로 해야 업로드, 수정시 파일이 한군데로 모임
		String uploadDir = "/resources/upload/upload"; // 가상의 업로드 경로
		System.out.println("uploadDir : " + uploadDir);
		// => webapp/resources 폴더 내에 upload 폴더 생성 필요
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir); // 실제 경로를 갖는 File 객체 생성
		System.out.println("f.exists() : " + f.exists());
		// 만약, 해당 경로 상에 디렉토리(폴더)가 존재하지 않을 경우 생성
		if(!f.exists()) { // 해당 경로가 존재하지 않을 경우
			// 경로 상의 존재하지 않는 모든 경로 생성
			f.mkdirs();
		}
		
		// BoardVO 객체에 전달된 MultipartFile 객체 꺼내기
		MultipartFile mFile = news.getFile();

		// 새 파일 업로드 여부와 관계없이 무조건 파일명을 가져와서 BoardVO 객체에 저장
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		news.setNews_file(originalFileName);
		news.setNews_realfile(uuid + "_" + originalFileName);
		
		int updateCount = service.modifyNewsPro(news);
		
		if(updateCount == 0) { // 수정 실패 시
			// 임시 폴더에 업로드 파일이 저장되어 있으며
			// transferTo() 메서드를 호출하지 않으면 임시 폴더의 파일은 자동 삭제됨
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "member/fail_back";
		} else { // 수정 성공 시
			// 수정 작업 성공 시 새 파일이 존재할 경우에만 실제 폴더 위치에 파일 업로드 수행
			// => 임시 폴더에 있는 업로드 파일을 실제 업로드 경로로 이동
			// => 새 파일 존재 여부는 업로드 할 파일명이 널스트링("") 이 아닌 것으로 판별
			if(!originalFileName.equals("")) {
				try {
					mFile.transferTo(new File(saveDir, news.getNews_realfile()));
					
					// 기존 업로드 된 실제 파일 삭제
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
			// *리다이렉트 이유
			return "redirect:/news_detail.in?news_num=" + news.getNews_num() + "&pageNum=" + pageNum + "&sId=" + sId;
		}
	}
	
	// information/news_delete.jsp
	@GetMapping(value = "/news_delete.in")
	public String news_delete() {
		return "information/news_delete";
	}
	
	@PostMapping(value = "/news_deletePro.in")
	public String news_deletePro(@ModelAttribute NewsVO news, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
		System.out.println("news : " + news);
		// Service - getRealFile() 메서드를 호출하여 삭제 전 실제 업로드 된 파일명 조회 작업 요청
		
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectRealFile(news.getNews_num());
		
		// 글 삭제
		// * 글 삭제시 파일질라에 업로드된 파일은 삭제되지 않음
		int deleteCount = service.deleteNewsPro(news);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "비밀번호가 틀렸습니다. 다시 시도해 주세요.");
			return "information/fail_back";
		} else { // 삭제 성공 시
			// File 객체의 delete() 메서드를 활용하여 실제 업로드 된 파일 삭제
			String uploadDir = "/resources/upload/upload"; // 가상의 업로드 경로
			// => webapp/resources 폴더 내에 upload 폴더 생성 필요
			String saveDir = session.getServletContext().getRealPath(uploadDir);
			System.out.println("실제 업로드 경로 : " + saveDir);
			
			File f = new File(saveDir, realFile); // 실제 경로와 실제 파일명을 갖는 File 객체 생성
			// 만약, 해당 경로 상에 파일이 존재할 경우 삭제
			if(f.exists()) { // 해당 경로에 파일이 존재할 경우
				f.delete();
			}
			return "redirect:/news.in?pageNum=" + pageNum + "&sId=" + sId;
		}
		
	}
	
	// 	* 글 작성시 wtpwebapps/upload/upload 폴더에 파일이 저장됨
	//    글 작성시 파일질라에 올라간다
	//    글 수정시 wtpwebapps/upload 폴더에 파일이 저장됨
	//    글 수정시 파일질라에 수정한 파일이 올라간다
	//    파일 다운시 wtpwebapps/upload 폴더에 동일 파일이 있다면 다운되지 않음
	//                               폴더에 동일 파일이 없다면 wtpwebapps/upload 폴더에 파일이 저장됨
	//    파일 다운시 파일질라에 올라간다
	//    파일 삭제시 파일이 삭제됨
	//    파일 삭제시 파일질라에 올라간 파일도 삭제가 된다
	
	/*
	 * - 스프링 프로젝트에서 다운, 수정, 삭제
		만약 프로젝트에서 파일올린걸 다운받는다고 하면
		다운받고 싶은 파일을 선택하면 파일질라에 바로 다운이 될때도 있지만 아래처럼 서버에 문구가 뜨며 다운이 안될때도 있다
		[Response] 550 Couldn't open the file or directory
		이럴 경우 아래의 세션을 kill하고 클라이언트에 다시 들어가 다시 로그인을 해보면 다운되어 있을수도 있다
		그래도 안뜬다면 클라이언트 보기 > 새로고침을 눌러보면 서버에 아래처럼 문구가 뜨면 정상적으로 다운이 된것이다
		[Response] 150 Starting data transfer.
		[Response] 226 Operation successful
		* 참고 : session 14가 잘돌아감
		* 프로젝트에 올려놓은 글을 수정하면서 파일을 수정하거나, 글을 삭제하면서 파일을 삭제할때도 위와 같이 파일질라에 테스트를 해본다
	 * */
	@GetMapping(value = "/newsFileDownload")
	public String newsFiledownload(@RequestParam String fileName, @RequestParam int news_num, @RequestParam int pageNum, @RequestParam String sId, HttpSession session) {
		// 원본 파일명 추출하기(사용자가 직접 다운로드 하는 경우 필요)
		// => 실제 파일명의 앞부분(UUID) 부분 제거
		//    ("UUID_파일명" 에서 _ 까지를 제외한 뒷부분의 파일명 부분만 추출)
		
		System.out.println("fileName : " + fileName);
		System.out.println("news_num : " + news_num);
		System.out.println("pageNum : " + pageNum);
		
		// FTP 접속
		String ftpBaseDir = "/upload";
		ftp.connect(ftpBaseDir);
		
		System.out.println("파일다운 되나");
//				String saveDir = "D:\\"; // 사용자 PC 에 직접 다운로드 시 경로
		
		// 가상 업로드 경로에 대한 실제 업로드 경로 알아내기
		// => 단, request 객체에 getServletContext() 메서드 대신, session 객체로 동일한 작업 수행
		//    (request 객체에 해당 메서드 없음)
		String uploadDir = "/resources/upload/upload"; // 가상의 업로드 경로
		// => webapp/resources 폴더 내에 upload 폴더 생성 필요
		String saveDir = session.getServletContext().getRealPath(uploadDir);
//				System.out.println("실제 업로드 경로 : " + saveDir);
		
		System.out.println("파일다운 되나2");
		// File 객체 생성
//				File f = new File(saveDir, original_file); // 사용자 PC 에 파일 다운로드 시 원본 파일명 사용
		File f = new File(saveDir, fileName); // 톰캣 서버에 파일 다운로드 시 실제 파일명 사용
		System.out.println("f : " + f);
		
		System.out.println("파일다운 되나3");
		// 만약, 톰캣 업로드 폴더에 해당 파일이 존재하지 않을 경우에만 다운로드 작업 수행
		if(!f.exists()) {
			// download() 메서드 호출하여 File 객체(다운받아 저장될 파일 정보)와 실제 파일명 전달 
			System.out.println("f.exists() : " + f.exists());
			ftp.download(f, fileName);
		}
		
		ftp.disconnect();
			
		return "redirect:/news_detail.in?news_num=" + news_num + "&pageNum=" + pageNum + "&sId=" + sId;
		
	}
	
	// information/news_detail.jsp
	@PostMapping(value = "/reply_writePro.re")
	public String reply_writePro(@ModelAttribute ReplyVO reply, @RequestParam int pageNum, Model model) {
		
		// 댓글 작성
		int insertCount = service.writeReplyPro(reply);
		
		if(insertCount < 0) {
			model.addAttribute("msg", "댓글 작성에 실패하였습니다.");
			return "information/fail_back";
		} else {
			return "redirect:/news_detail.in?news_num=" + reply.getReply_ne_ref() + "&pageNum=" + pageNum + "&sId=" + reply.getReply_id();
		}
	}
	
	// information/news_detail.jsp
	@PostMapping(value = "/reReply_writePro.re")
	public String reReplyWrite(@ModelAttribute ReplyVO reply, @RequestParam int pageNum, Model model) {
		
		// 순서번호(reply_re_seq) 조정 
		service.increaseReReplyReSeq(reply);
		
		// 대댓글 등록
		int insertCount = service.reReplyWritePro(reply);
		
		if(insertCount < 0) {
			model.addAttribute("msg", "댓글 작성에 실패하였습니다.");
			return "information/fail_back";
		} else {
			return "redirect:/news_detail.in?news_num=" + reply.getReply_ne_ref() + "&pageNum=" + pageNum + "&sId=" + reply.getReply_id();
		}
	}

	// information/news_detail.jsp
	@PostMapping(value = "/reply_modifyPro.re")
	public String reply_modifyPro(@ModelAttribute ReplyVO reply, @RequestParam int pageNum, Model model, HttpServletResponse response) throws IOException {
		
		System.out.println("reply : " + reply);
		
		// 댓글 수정
		int updateCount = service.modifyReplyPro(reply);
		
		if(updateCount < 0) {
			model.addAttribute("msg", "댓글 수정이 실패되었습니다. 다시 시도해 주세요.");
			return "information/fail_back";
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('댓글이 수정되었습니다.');</script>");
			
			return "redirect:/news_detail.in?news_num=" + reply.getReply_ne_ref() + "&pageNum=" + pageNum + "&sId=" + reply.getReply_id();
		}
	}
	
	
	// 댓글 삭제
	@ResponseBody
	@PostMapping(value = "/reply_deletePro.re")
	public int reply_deletePro(@RequestParam int reply_idx, @RequestParam int reply_ne_ref, @RequestParam int pageNum, Model model, @RequestParam String sId, HttpServletResponse response) throws IOException {
		// 원 댓글 삭제 + 원 댓글 삭제시 대댓글도 삭제
		int deleteCount = service.replyDeletePro(reply_idx);
		
		return deleteCount;
	}
	
	// TODO
	// 댓글 조회시 시분초 다 나오게
	// 댓글 페이징
	// 알림 기능(소켓?
	// sns로그인
	// 무한 스크롤?
	// 댓글 삭제 + 대댓글 위치조정 + 댓글 페이징
	
	// ftpClient.retrieve함수 false 해결
	// news_detail.jsp 실제 조회할때만 조회수 증가
	
}
