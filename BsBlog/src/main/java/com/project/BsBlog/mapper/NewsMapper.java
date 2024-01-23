package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.NewsVO;

// * 인터페이스 쓰는 이유?
public interface NewsMapper {


	// 글 목록
	// * 왜 param 쓰는지
	List<NewsVO> selectNews(@Param("startRow") int startRow, @Param("listLimit") int listLimit,  @Param("searchType") String searchType,  @Param("keyword") String keyword);

	// 글 목록 갯수 조회
	int selectNewsCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 작성
	int writeNewsPro(NewsVO news);
	
	// 글 상세 조회
	NewsVO selectNewsDetail(int news_num);

	// 글 조회시 조회수 증가
	void increaseNewsReadCount(int news_num);
	
	// 글 수정
	int modifyNewsPro(NewsVO news);

	// 글 삭제 전 실제 업로드된 파일명 조회
	String selectRealFile(int news_num);

	// 글 삭제
	int deleteNewsPro(NewsVO news);

	

}
