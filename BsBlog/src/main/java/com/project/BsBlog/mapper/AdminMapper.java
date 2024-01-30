package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.ReportVO;

public interface AdminMapper {

	// 회원 목록 조회
	List<MemberVO> selectMember (@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 회원수 조회
	int selectMemberCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 회원 상세 조회
	MemberVO selectMemberDetail(int member_idx);
	
	// 멤버 강제 탈퇴
	int deleteMemberPro(int member_idx);

	// 신고 전체 목록 조회
	List<ReportVO> selectReport(@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 신고 처리대기 목록 조회
	List<ReportVO> selectReportHold(@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 신고 처리완료 목록 조회
	List<ReportVO> selectReportComplete(@Param("searchType") String searchType, @Param("keyword") String keyword, @Param("startRow") int startRow, @Param("listLimit") int listLimit);

	// 신고 전체 목록 갯수 조회
	int selectReportCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 신고 처리대기 목록 갯수 조회
	int selectReportHoldCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 신고 처리완료 목록 갯수 조회
	int selectReportCompleteCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 신고 상세 조회
	ReportVO selectReportDetail(int report_idx);

	// 신고 관련 처리 상태 변경
	int modifyReportStatus(@Param("report_status") String report_status, @Param("report_idx") int report_idx);

	




	
}
