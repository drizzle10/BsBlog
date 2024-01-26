package com.project.BsBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.GuestbookMapper;
import com.project.BsBlog.vo.GuestbookVO;
import com.project.BsBlog.vo.NewsVO;

@Service
public class GuestbookService {
	@Autowired
	GuestbookMapper mapper;
	

	// 글 목록 조회
	public List<GuestbookVO> selectGuestbook(int startRow, int listLimit, String searchType, String keyword) {
		return mapper.selectGuestbook(startRow, listLimit, searchType, keyword);
	}

	// 글 목록 갯수 조회
	public int selectGuestbookCount(String searchType, String keyword) {
		return mapper.selectGuestbookCount(searchType, keyword);
	}

	// 글 작성
	public int writeGuestbookPro(GuestbookVO guestbook) {
		return mapper.writeGuestbookPro(guestbook);
	}

	// 글 상세 조회
	public GuestbookVO selectGuestbookDetail(int guestbook_num) {
		return mapper.selectGuestbookDetail(guestbook_num);
	}

	// 글 조회시 조회수 증가
	public void increaseGuestbookReadCount(int guestbook_num) {
		mapper.increaseGuestbookReadCount(guestbook_num);
	}

	// 글 삭제시 실제 업로드된 파일명 조회
	public String selectGuestbookRealFile(int guestbook_num) {
		return mapper.selectGuestbookRealFile(guestbook_num);
	}
	
	// 글 수정
	public int modifyGuestbookPro(GuestbookVO guestbook) {
		return mapper.modifyGuestbookPro(guestbook);
	}

	// 글 삭제
	public int deleteGuestbookPro(GuestbookVO guestbook) {
		return mapper.deleteGuestbookPro(guestbook);
	}

	

}
