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
import com.project.BsBlog.service.GuestbookService;
import com.project.BsBlog.vo.GuestbookVO;
import com.project.BsBlog.vo.NewsVO;
import com.project.BsBlog.vo.NoteVO;
import com.project.BsBlog.vo.PageInfo;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService service;
	
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
	
	// guestbook/guestbook.jsp
	@GetMapping(value = "/guestbook.gu")
	public String guestbook(@RequestParam(defaultValue = "") String searchType,
				@RequestParam(defaultValue = "") String keyword,
				@RequestParam(defaultValue = "1") int pageNum,
				Model model)  {
		System.out.println("searchType : " + searchType);
		System.out.println("keyword : " + keyword);
		System.out.println("pageNum : " + pageNum);
		
		int listLimit = 10; 
		
		int pageListLimit = 10; 

		int startRow = (pageNum - 1) * listLimit;
		
		// 글 목록 조회
		List<GuestbookVO> guestbook = service.selectGuestbook(startRow, listLimit, searchType, keyword);
		System.out.println("guestbook : " + guestbook);
		
		// 글 목록 갯수 조회
		int listCount = service.selectGuestbookCount(searchType, keyword);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		int endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("guestbook", guestbook);
		model.addAttribute("pageInfo", pageInfo);
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "guestbook/guestbook";
	}

// guestbook/guestbook_write.jsp
	@GetMapping(value = "/guestbook_write.gu")
	public String guestbook_write() {
		return "guestbook/guestbook_write";
	}
	
	@PostMapping(value = "/guestbook_writePro.gu")
	public String news_writePro(@ModelAttribute GuestbookVO guestbook, @RequestParam String sId, Model model, HttpSession session) {
		System.out.println("guestbook : " + guestbook);
		
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir); 
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = guestbook.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		guestbook.setGuestbook_file(originalFileName); // 실제로는 불필요한 컬럼
		guestbook.setGuestbook_realfile(uuid + "_" + originalFileName);
		
		int insertCount = service.writeGuestbookPro(guestbook);
		
		if(insertCount > 0) {
			try {
				String ftpBaseDir = "/upload";
				ftp.connect(ftpBaseDir);
				
				System.out.println("ftp접속 완");
				
				File f2 = new File(saveDir, guestbook.getGuestbook_realfile());
				mFile.transferTo(f2);

				System.out.println("톰캣 업로드 완");
				ftp.upload(f2, guestbook.getGuestbook_realfile());
				
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
			
			return "redirect:/guestbook.gu?sId=" + sId;
		} else {
			model.addAttribute("msg", "글 쓰기 실패!");
			return "guestbook/fail_back";
		}
		
	}
	
	// guestbook/guestbook_detail.jsp
	@GetMapping(value = "/guestbook_detail.gu")
	public String guestbook_detail(@RequestParam int guestbook_num, Model model, HttpSession session) {
		
		// 글 상세 조회
		GuestbookVO guestbookDetail = service.selectGuestbookDetail(guestbook_num);
		
		// 글 조회시 조회수 증가
		service.increaseGuestbookReadCount(guestbook_num);
		
		model.addAttribute("guestbookDetail", guestbookDetail);
		
		return "guestbook/guestbook_detail";
	}
	
	// guestbook/guestbook_modify.jsp
	@GetMapping(value = "/guestbook_modify.gu")
	public String guestbook_modify(@RequestParam int guestbook_num, Model model) {
		
		// 글 수정시 원본글 뿌리기
		GuestbookVO guestbookDetail = service.selectGuestbookDetail(guestbook_num);
		
		model.addAttribute("guestbookDetail", guestbookDetail);

		return "guestbook/guestbook_modify";
	}
	
	@PostMapping(value = "/guestbook_modifyPro.gu")
	public String guestbook_modifyPro(@ModelAttribute GuestbookVO guestbook, @RequestParam int guestbook_num, @RequestParam int pageNum, @RequestParam String sId,
									Model model, HttpSession session) {
		System.out.println("guestbook : " + guestbook);
		System.out.println("diary_num : " + guestbook_num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("기존 파일명 : " + guestbook.getGuestbook_file());
		System.out.println("기존 실제 파일명 : " + guestbook.getGuestbook_realfile());
		System.out.println("새 파일 객체 : " + guestbook.getFile());
		System.out.println("새 파일명 : " + guestbook.getFile().getOriginalFilename());
		
		String oldRealFile = guestbook.getGuestbook_realfile();
		
		String uploadDir = "/resources/upload";
		
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("실제 업로드 경로 : " + saveDir);
		
		File f = new File(saveDir);
		
		if(!f.exists()) { 
			f.mkdirs();
		}
		
		MultipartFile mFile = guestbook.getFile();
		
		String originalFileName = mFile.getOriginalFilename();
		long fileSize = mFile.getSize();
		System.out.println("파일명 : " + originalFileName);
		System.out.println("파일크기 : " + fileSize + " Byte");
		
		String uuid = UUID.randomUUID().toString();
		System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
		
		guestbook.setGuestbook_file(originalFileName);
		guestbook.setGuestbook_realfile(uuid + "_" + originalFileName);
		
		// 글 수정
		int updateCount = service.modifyGuestbookPro(guestbook);
		System.out.println("updateCount : " + updateCount);
		
		if(updateCount == 0) { // 수정 실패 시
			model.addAttribute("msg", "패스워드 틀림!");
			return "board/fail_back";
		} else { // 수정 성공 시
			if(!originalFileName.equals("")) {
				try {
					mFile.transferTo(new File(saveDir, guestbook.getGuestbook_realfile()));
					
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
			return "redirect:/guestbook_detail.gu?guestbook_num=" + guestbook.getGuestbook_num() + "&pageNum=" + pageNum + "&sId=" + sId;
		}
	}
		
	// guestbook/guestbook_delete.jsp
	@GetMapping(value = "/guestbook_delete.gu")
	public String guestbook_delete() {
		return "guestbook/guestbook_delete";
	}
	
	@PostMapping(value = "/guestbook_deletePro.gu")
	public String guestbook_deletePro(@ModelAttribute GuestbookVO guestbook, @RequestParam int pageNum, @RequestParam String sId, Model model, HttpSession session) {
	
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectGuestbookRealFile(guestbook.getGuestbook_num());
		
		// 글 삭제
		int deleteCount = service.deleteGuestbookPro(guestbook);
		
		if(deleteCount == 0) {
			model.addAttribute("msg", "패스워드 틀림!");
			return "board/fail_back";
		} else {
			String uploadDir = "/resources/upload";
			String saveDir = session.getServletContext().getRealPath(uploadDir);
			System.out.println("실제 업로드 경로 : " + saveDir);
			
			File f = new File(saveDir, realFile);
			if(f.exists()) { 
				f.delete();
			}
			
			return "redirect:/guestbook.gu?pageNum=" + pageNum + "&sId=" + sId;
		}
	}
	// TODO
	// 로그인, 가입
	// 가입시 인증
	// 네이버, 카카오톡 로그인
	// 댓글 혹은 답글
	
}
