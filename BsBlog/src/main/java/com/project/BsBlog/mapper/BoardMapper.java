package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.DiaryVO;
import com.project.BsBlog.vo.NoteVO;

public interface BoardMapper {

	// ----- diary -----
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

	// 글 수정
	int modifyDiaryPro(DiaryVO diary);

	// 글 삭제시 실제 업로드된 파일명 조회
	String selectDiaryRealFile(int diary_num);

	// 글 삭제
	int deleteDiaryPro(DiaryVO diary);

	// ----- note -----
	// 글 목록 조회
	List<NoteVO> selectNote(@Param("startRow") int startRow, @Param("listLimit") int listLimit, @Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 목록 갯수 조회
	int selectNoteCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 작성
	int writeNotePro(NoteVO note);

	// 글 상세 조회
	NoteVO selectNoteDetail(int note_num);
	
	// 글 조회시 조회수 증가
	void increaseNoteReadCount(int note_num);

	// 글 수정
	int modifyNotePro(NoteVO note);

	// 글 삭제시 실제 업로드된 파일명 조회
	String selectNoteRealFile(int note_num);

	// 글 삭제
	int deleteNotePro(NoteVO note);

	
}
