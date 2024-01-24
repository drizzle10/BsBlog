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
	public String news_writePro(@ModelAttribute GuestbookVO guestbook, Model model, HttpSession session) {
		System.out.println(guestbook);
		
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
			
			return "redirect:/guestbook.gu";
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
		
	
}