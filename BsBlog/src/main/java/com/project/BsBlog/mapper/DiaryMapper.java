package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.DiaryVO;

public interface DiaryMapper {

	// 글 목록 조회
	List<DiaryVO> selectDiary(@Param("startRow") int startRow, @Param("listLimit") int listLimit, @Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 갯수 조회
	int selectDiaryCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 작성
	int writeDiaryPro(DiaryVO diary);

	// 글 상세 조회
	DiaryVO selectDiaryDetail(int diary_num);

	// 글 조회시 조회수 증가
	void increaseDiaryReadCount(int diary_num);


}
