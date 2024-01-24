package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.GuestbookVO;

public interface GuestbookMapper {

	// 글 작성
	int writeGuestbookPro(GuestbookVO guestbook);

	// 글 목록 조회
	List<GuestbookVO> selectGuestbook(@Param("startRow") int startRow, @Param("listLimit") int listLimit, @Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 목록 갯수 조회
	int selectGuestbookCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 상세 조회
	GuestbookVO selectGuestbookDetail(int guestbook_num);

	// 글 조회시 조회수 증가
	void increaseGuestbookReadCount(int guestbook_num);

	// 글 삭제시 실제 업로드된 파일명 조회
	String selectGuestbookRealFile(int guestbook_num);

	// 글 삭제
	int deleteGuestbookPro(GuestbookVO guestbook);
	
	
}
