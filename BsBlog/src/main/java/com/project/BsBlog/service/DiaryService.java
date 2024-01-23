package com.project.BsBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.DiaryMapper;
import com.project.BsBlog.vo.DiaryVO;

@Service
public class DiaryService {
	@Autowired
	public DiaryMapper mapper;

	// 글 목록 조회
	public List<DiaryVO> selectDiary(int startRow, int listLimit, String searchType, String keyword) {
		return mapper.selectDiary(startRow, listLimit, searchType, keyword);
	}

	// 글 갯수 조회
	public int selectDiaryCount(String searchType, String keyword) {
		return mapper.selectDiaryCount(searchType, keyword);
	}
	
	// 글 작성
	public int writeDiaryPro(DiaryVO diary) {
		return mapper.writeDiaryPro(diary);
	}

	// 글 상세 조회
	public DiaryVO selectDiaryDetail(int diary_num) {
		return mapper.selectDiaryDetail(diary_num);
	}

	// 글 조회시 조회수 증가
	public void increaseDiaryReadCount(int diary_num) {
		mapper.increaseDiaryReadCount(diary_num);
	}

	
}
