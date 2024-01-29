package com.project.BsBlog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.BsBlog.service.AdminService;
import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.PageInfo;
import com.project.BsBlog.vo.ReportVO;

@Controller
public class AdminController {

	@Autowired
	AdminService service;
	
	int listLimit; 
	int pageListLimit; 
	int startRow;
	int listCount;
	int maxPage;
	int startPage;
	int endPage;
	
	// admin/member_list
	@GetMapping(value = "member.ad")
	public String member_list(@RequestParam(defaultValue = "") String searchType,
								@RequestParam(defaultValue = "") String keyword, 
								@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		listLimit = 10; 
		
		pageListLimit = 10; 

		startRow = (pageNum - 1) * listLimit;
		
		// 회원 목록 조회
		List<MemberVO> memberList = service.selectMember(searchType, keyword, startRow, listLimit);
		
		// 회원수 조회
		listCount = service.selectMemberCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/member";
	}
	
	// admin/member_detail.jsp
	@GetMapping(value = "/member_detail.ad")
	public String member_detail(@RequestParam int member_idx, Model model,  HttpSession session) {
		
		// 멤버 상세 조회
		MemberVO memberDetail = service.selectMemberDetail(member_idx);
		
		model.addAttribute("memberDetail", memberDetail);
		
		return "admin/member_detail";
	}
	
	// admin/report.jsp
	@GetMapping(value = "/report.ad")
	public String report(@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		listLimit = 10; 
		
		pageListLimit = 10; 

		startRow = (pageNum - 1) * listLimit;
		
		// 신고 전체 목록 조회
		List<ReportVO> reportList = service.selectReport(searchType, keyword, startRow, listLimit);
		
		// 신고 전체 목록 갯수 조회
		listCount = service.selectReportCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/report";
	}
	
	// admin/report_hold.jsp
	@GetMapping(value = "/report_hold.ad")
	public String report_hold(@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		listLimit = 10; 
		
		pageListLimit = 10; 

		startRow = (pageNum - 1) * listLimit;
		
		// 신고 처리대기 목록 조회
		List<ReportVO> reportList = service.selectReportHold(searchType, keyword, startRow, listLimit);
		
		// 신고 처리대기 목록 갯수 조회
		listCount = service.selectReportHoldCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/report_hold";
	}
	
	// admin/report_complete.jsp
	@GetMapping(value = "/report_complete.ad")
	public String report_complete(@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		listLimit = 10; 
		
		pageListLimit = 10; 
		
		startRow = (pageNum - 1) * listLimit;
		
		// 신고 처리완료 목록 조회
		List<ReportVO> reportList = service.selectReportComplete(searchType, keyword, startRow, listLimit);
		
		// 신고 처리완료 목록 갯수 조회
		listCount = service.selectReportCompleteCount(searchType, keyword);
		
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		endPage = startPage + pageListLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		
		model.addAttribute("reportList", reportList);
		model.addAttribute("pageInfo", pageInfo);
		
		return "admin/report_complete";
	}
	
	

	// admin/report_detail.jsp
	@GetMapping(value = "/report_detail.ad")
	public String report_detail(@RequestParam int report_idx, Model model,  HttpSession session) {
		
		// 신고 상세 조회
		ReportVO reportDetail = service.selectReportDetail(report_idx);
		
		model.addAttribute("reportDetail", reportDetail);
		
		return "admin/report_detail";
	}

	// admin/report_detail.jsp
	@GetMapping(value = "/report_status.ad")
	public String report_status(@RequestParam String report_status, @RequestParam int report_idx, 
								@RequestParam int pageNum, @RequestParam String sId,
								Model model) {
		System.out.println("report_status : " + report_status);
		System.out.println("report_idx : " + report_idx);
		
		// 신고 관련 처리 상태 변경
		int updateCount = service.modifyReportStatus(report_status, report_idx);
		
		if(updateCount == 0) {
			model.addAttribute("msg", "신고 관련 처리가 실패되었습니다. 다시 시도해주세요.");
			return "admin/fail_back";
		} else {
			return "redirect:/report_detail.ad?report_idx=" + report_idx + "&pageNum=" + pageNum + "&sId=" + sId ;
		}
		
	}

}
