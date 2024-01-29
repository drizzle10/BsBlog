package com.project.BsBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.AdminMapper;
import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.ReportVO;

@Service
public class AdminService {
	@Autowired
	AdminMapper mapper;

	// 회원 목록 조회
	public List<MemberVO> selectMember(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectMember(searchType, keyword, startRow, listLimit);
	}

	// 회원수 조회
	public int selectMemberCount(String searchType, String keyword) {
		return mapper.selectMemberCount(searchType, keyword);
	}

	// 멤버 상세 조회
	public MemberVO selectMemberDetail(int member_idx) {
		return mapper.selectMemberDetail(member_idx);
	}

	// 신고 전체 목록 조회
	public List<ReportVO> selectReport(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectReport(searchType, keyword, startRow, listLimit);
	}
	
	// 신고 처리대기 목록 조회
	public List<ReportVO> selectReportHold(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectReportHold(searchType, keyword, startRow, listLimit);
	}

	// 신고 처리완료 목록 조회
	public List<ReportVO> selectReportComplete(String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectReportComplete(searchType, keyword, startRow, listLimit);
	}

	// 신고 전체 목록 갯수 조회
	public int selectReportCount(String searchType, String keyword) {
		return mapper.selectReportCount(searchType, keyword);
	}

	// 신고 처리대기 목록 갯수 조회
	public int selectReportHoldCount(String searchType, String keyword) {
		return mapper.selectReportHoldCount(searchType, keyword);
	}

	// 신고 처리완료 목록 갯수 조회
	public int selectReportCompleteCount(String searchType, String keyword) {
		return mapper.selectReportCompleteCount(searchType, keyword);
	}

	// 신고 상세 조회
	public ReportVO selectReportDetail(int report_idx) {
		return mapper.selectReportDetail(report_idx);
	}

	// 신고 관련 처리 상태 변경
	public int modifyReportStatus(String report_status, int report_idx) {
		return mapper.modifyReportStatus(report_status, report_idx);
	}





}
