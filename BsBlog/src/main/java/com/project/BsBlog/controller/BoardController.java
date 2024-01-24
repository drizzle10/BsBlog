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
	public String diary_writePro(@ModelAttribute DiaryVO diary, Model model, HttpSession session) {
		
		System.out.println(diary);
		
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
	
	@PostMapping(value = "/diary_modifyPro.bo")
	public String diary_modifyPro(@ModelAttribute DiaryVO diary, @RequestParam int diary_num, @RequestParam int pageNum,
									Model model, HttpSession session) {
		System.out.println("diary_num : " + diary_num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("기존 파일명 : " + diary.getDiary_file());
		System.out.println("기존 실제 파일명 : " + diary.getDiary_realfile());
		System.out.println("새 파일 객체 : " + diary.getFile());
		System.out.println("새 파일명 : " + diary.getFile().getOriginalFilename());
		
		String oldRealFile = diary.getDiary_realfile();
		
		String uploadDir = "/resources/upload";
		
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
			model.addAttribute("msg", "패스워드 틀림!");
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
			return "redirect:/diary_detail.bo?diary_num=" + diary.getDiary_num() + "&pageNum=" + pageNum;
		}
	}
	
	// board/diary_delete.jsp
	@GetMapping(value = "/diary_delete.bo")
	public String diary_delete() {
		return "board/diary_delete";
	}
	
	@PostMapping(value = "/diary_deletePro.bo")
	public String diary_deletePro(@ModelAttribute DiaryVO diary, @RequestParam int pageNum, Model model, HttpSession session) {
	
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectDiaryRealFile(diary.getDiary_num());
		
		// 글 삭제
		int deleteCount = service.deleteDiaryPro(diary);
		
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
			
			return "redirect:/diary.bo?pageNum=" + pageNum;
		}
	}
	
	@GetMapping(value = "/diaryFileDownload")
	public String diaryFiledownload(@RequestParam String fileName, @RequestParam int diary_num, @RequestParam int pageNum, HttpSession session) {
		System.out.println("fileName : " + fileName);
		System.out.println("diary_num : " + diary_num);
		System.out.println("pageNum : " + pageNum);
		
		String ftpBaseDir = "/upload";
		ftp.connect(ftpBaseDir);
		
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir, fileName); 
		
		if(!f.exists()) {
			System.out.println("f.exists() : " + f.exists());
			ftp.download(f, fileName);
		}
		
		ftp.disconnect();
			
		return "redirect:/diary_detail.bo?diary_num=" + diary_num + "&pageNum=" + pageNum;
		
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
	public String note_writePro(@ModelAttribute NoteVO note, Model model, HttpSession session) {
		
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
			
			return "redirect:/note.bo";
		} else {
			model.addAttribute("msg", "글 쓰기 실패!");
			return "board/fail_back";
		}
		
	}
	
	//board/note_detail.jsp
	@GetMapping(value = "/note_detail.bo")
	public String note_detail(@RequestParam int note_num, @RequestParam int pageNum, Model model) {
		
		// 글 상세 조회
		NoteVO noteDetail = service.selectNoteDetail(note_num);
		
		// 글 조회시 조회수 증가
		service.increaseNoteReadCount(note_num);
		
		model.addAttribute("noteDetail", noteDetail);
		
		return "board/note_detail";
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
	public String note_modifyPro(@ModelAttribute NoteVO note, @RequestParam int note_num, @RequestParam int pageNum,
									Model model, HttpSession session) {
		System.out.println("diary_num : " + note_num);
		System.out.println("pageNum : " + pageNum);
		System.out.println("기존 파일명 : " + note.getNote_file());
		System.out.println("기존 실제 파일명 : " + note.getNote_realfile());
		System.out.println("새 파일 객체 : " + note.getFile());
		System.out.println("새 파일명 : " + note.getFile().getOriginalFilename());
		
		String oldRealFile = note.getNote_realfile();
		
		String uploadDir = "/resources/upload";
		
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
			model.addAttribute("msg", "패스워드 틀림!");
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
			return "redirect:/note_detail.bo?note_num=" + note.getNote_num() + "&pageNum=" + pageNum;
		}
	}

	// board/note_delete.jsp
	@GetMapping(value = "/note_delete.bo")
	public String note_delete() {
		return "board/note_delete";
	}
	
	@PostMapping(value = "/note_deletePro.bo")
	public String note_deletePro(@ModelAttribute NoteVO note, @RequestParam int pageNum, Model model, HttpSession session) {
	
		// 글 삭제시 실제 업로드된 파일명 조회
		String realFile = service.selectNoteRealFile(note.getNote_num());
		
		// 글 삭제
		int deleteCount = service.deleteNotePro(note);
		
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
			
			return "redirect:/note.bo?pageNum=" + pageNum;
		}
	}
	
	@GetMapping(value = "/noteFileDownload")
	public String noteFiledownload(@RequestParam String fileName, @RequestParam int note_num, @RequestParam int pageNum, HttpSession session) {
		System.out.println("fileName : " + fileName);
		System.out.println("note_num : " + note_num);
		System.out.println("pageNum : " + pageNum);
		
		String ftpBaseDir = "/upload";
		ftp.connect(ftpBaseDir);
		
		String uploadDir = "/resources/upload"; 
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		File f = new File(saveDir, fileName); 
		
		if(!f.exists()) {
			System.out.println("f.exists() : " + f.exists());
			ftp.download(f, fileName);
		}
		
		ftp.disconnect();
			
		return "redirect:/note_detail.bo?note_num=" + note_num + "&pageNum=" + pageNum;
		
	}
}
