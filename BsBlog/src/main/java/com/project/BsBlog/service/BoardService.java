package com.project.BsBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.BoardMapper;
import com.project.BsBlog.vo.DiaryVO;
import com.project.BsBlog.vo.NoteVO;

@Service
public class BoardService {
	@Autowired
	public BoardMapper mapper;

	// ----- diary -----
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

	// 글 수정
	public int modifyDiaryPro(DiaryVO diary) {
		return mapper.modifyDiaryPro(diary);
	}

	// 글 삭제시 실제 업로드된 파일명 조회
	public String selectDiaryRealFile(int diary_num) {
		return mapper.selectDiaryRealFile(diary_num);
	}

	// 글 삭제
	public int deleteDiaryPro(DiaryVO diary) {
		return mapper.deleteDiaryPro(diary);
	}

	// ----- note -----
	// 글 목록 조회
	public List<NoteVO> selectNote(int startRow, int listLimit, String searchType, String keyword) {
		return mapper.selectNote(startRow, listLimit, searchType, keyword);
	}

	// 글 목록 갯수 조회
	public int selectNoteCount(String searchType, String keyword) {
		return mapper.selectNoteCount(searchType, keyword);
	}
	
	// 글 작성
	public int writeNotePro(NoteVO note) {
		return mapper.writeNotePro(note);
	}

	// 글 상세 조회
	public NoteVO selectNoteDetail(int note_num) {
		return mapper.selectNoteDetail(note_num);
	}

	// 글 조회시 조회수 증가
	public void increaseNoteReadCount(int note_num) {
		mapper.increaseNoteReadCount(note_num);
	}

	// 글 수정
	public int modifyNotePro(NoteVO note) {
		return mapper.modifyNotePro(note);
	}

	// 글 삭제시 실제 업로드된 파일명 조회
	public String selectNoteRealFile(int note_num) {
		return mapper.selectNoteRealFile(note_num);
	}

	// 글 삭제
	public int deleteNotePro(NoteVO note) {
		return mapper.deleteNotePro(note);
	}

	



	
}
