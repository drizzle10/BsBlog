package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.NoteVO;
import com.project.BsBlog.vo.ReportVO;

public interface MemberMapper {

	// 가입
	int joinPro(MemberVO member);

	// 아이디 중복 확인
	int idDup_check(String member_id);
	
	// 메일 중복 확인
	int mailDup_check(String member_email);

	// 로그인 전 비밀번호 조회
	String select_memberPassword(String member_id);

	// 나의 정보 조회
	MemberVO selectMyInfo(String sId);

	// 정보 수정
	int modifyMyInfoPro(MemberVO member);

	// 나의 신고 조회
	ReportVO selectMyReport(@Param("sId") String sId, @Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 나의 신고 목록 갯수 조회
	int selectMyReportCount(@Param("sId") String sId, @Param("searchType") String searchType, @Param("keyword") String keyword);

	// 나의 신고 상세 조회
	ReportVO selectMyReportDetail(String sId);

	// 탈퇴
	int deleteMyInfoPro(int member_idx);

	// 이메일 이용하여 아이디 찾기
	MemberVO selectMemberEmail(String member_email);

	// 나의 좋아요 조회
	List<NoteVO> selectMyHeart(@Param("sId") String sId, @Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 나의 좋아요 갯수 조회
	int selectMyHeartCount(@Param("sId") String sId, @Param("searchType") String searchType, @Param("keyword") String keyword);



}
