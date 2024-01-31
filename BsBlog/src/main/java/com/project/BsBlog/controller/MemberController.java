package com.project.BsBlog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.BsBlog.service.MemberService;
import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.PageInfo;
import com.project.BsBlog.vo.ReportVO;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	// member/join.jsp
	@GetMapping(value = "/join.me")
	public String join() {
		return "member/join";
	}

	@PostMapping(value = "/joinPro.me")
	public String joinPro(@ModelAttribute MemberVO member, Model model) {
		System.out.println(member);
		// *아래 정리하기
		// BCryptPasswordEncoder 활용한 해싱 
		// 1. BCryptPasswordEncoder 객체 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 2. BCryptPasswordEncoder 객체의 encode() 메서드를 호출하여 해싱 결과 리턴
		String securePassword = encoder.encode(member.getMember_password());
		// 3. MemberVO 객체의 패스워드에 암호문 저장
		member.setMember_password(securePassword);

		int insertCount = service.joinPro(member);

		if (insertCount > 0) { // 가입 성공
			System.out.println("가입 성공");
			model.addAttribute("msg", "가입에 성공하셨습니다. 감사합니다.");
			return "member/join_success";
		} else { // 가입 실패
			model.addAttribute("msg", "가입에 실패하셨습니다. 다시 시도해주세요.");
			return "member/fail_back";
		}
	}
	
	// 이메일 인증
	@GetMapping("/mailAuth_check")
	// * responsebody 이유
	@ResponseBody
	public String mailAuth_check(@ModelAttribute MemberVO member, Model model, HttpSession session, String email) {
		System.out.println("이메일 인증 이메일 : " + email);
		// * 서비스로 보내는 이유
		return service.mailAuth_check(email);

	}
	
	// 아이디 중복 확인
	// * responsebody 이유?
	@ResponseBody
	@PostMapping(value = "/idDup_check")
	public int idDup_check(@RequestParam String member_id, @ModelAttribute MemberVO member) {
		System.out.println(member_id);
		System.out.println(member);
		
		int idDup_checkCount = service.idDup_check(member_id);

		System.out.println(idDup_checkCount);
		
		return idDup_checkCount;
	}
	
	// 메일 중복 확인
	@ResponseBody
	@PostMapping(value = "/mailDup_check")
	public int mailDup_check(@RequestParam String member_email) {
		int mailDup_checkCount = service.mailDup_check(member_email);
		
		return mailDup_checkCount;
	}
	
	
	// member/login.jsp
	@GetMapping(value = "/login.me")
	public String login() {
		return "member/login";
	}

	@PostMapping(value = "/loginPro.me")
	public String loginPro(@ModelAttribute MemberVO member, Model model, HttpSession session) {
		// 1. BCryptPasswordEncoder 객체 생성
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// 2. member 테이블에서 id 에 해당하는 패스워드 조회 후 리턴값 저장(getPasswd())
		String member_password = service.select_memberPassword(member.getMember_id());
		
		// 3. 조회 결과를 활용하여 로그인 성공 여부 판별
		//    1) 아이디가 없을 경우(passwd 값이 null) 실패
		//    2) 패스워드 비교(BCryptPasswordEncoder 객체의 matches() 메서드 활용)
		//       2-1) 다를 경우 실패
		//       2-2) 같을 경우 성공
		
		if(member_password == null || !encoder.matches(member.getMember_password(), member_password)) {
			model.addAttribute("msg", "로그인에 실패하였습니다. 다시 시도해주세요.");
			return "member/fail_back";
		} else {
			session.setAttribute("sId", member.getMember_id());
			return "redirect:/";
		}
	}
	
	// 내가 신고한 글과 신고 받은 글 조회
	
	// 로그아웃
	@GetMapping(value = "/logout.me")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	// member/my_info.jsp
	@GetMapping(value = "/my_info.me")
	public String my_info(@RequestParam String sId, Model model) {
		
		// 나의 정보 조회
		MemberVO member = service.selectMyInfo(sId);
		
		model.addAttribute("member", member);
		
		return "member/my_info";
	}
	
	// member/my_info_modify.jsp
	@GetMapping(value = "/my_info_modify.me")
	public String my_info_modify(@RequestParam String sId, Model model) {
		
		MemberVO member= service.selectMyInfo(sId);
	
		model.addAttribute("member", member);
		
		return "member/my_info_modify";
	}
	
	@PostMapping(value = "/my_info_modifyPro.me")
	public String my_info_modifyPro(@ModelAttribute MemberVO member, @RequestParam String sId, Model model) {
		
		System.out.println("member : " + member);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// BCryptPasswordEncoder 객체의 encode() 메서드를 호출하여 해싱 결과 리턴
		String securePassword = encoder.encode(member.getMember_password());
		// MemberVO 객체의 패스워드에 암호문 저장
		member.setMember_password(securePassword);
		
		// 정보 수정
		int updateCount = service.modifyMyInfoPro(member);
		
		if(updateCount > 0) {
			return "redirect:/my_info.me?sId=" + sId;
		} else {
			model.addAttribute("msg", "정보 수정이 실패되었습니다. 다시 시도해 주세요.");
			return "member/fail_back";
		}
	}
	
	@GetMapping(value = "/my_info_delete.me")
	public String my_info_delete(@RequestParam int member_idx, Model model, HttpSession session) {
		
		// 탈퇴
		int deleteCount = service.deleteMyInfoPro(member_idx);
		
		if (deleteCount > 0) {
			session.invalidate();
			return "redirect:/";
		} else {
			model.addAttribute("msg", "탈퇴에 실패하였습니다. 다시 시도해 주세요.");
			return "member/fail_back";
		}
		
	}
	
	// member/my_report
	@GetMapping(value = "/my_report.me")
	public String my_report(@RequestParam String sId, @RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		System.out.println(searchType);
		System.out.println(keyword);
		System.out.println(sId);
		
		int listLimit = 10; 
		
		int pageListLimit = 10; 

		int startRow = (pageNum - 1) * listLimit;
		
		// 나의 신고 조회
		ReportVO report = service.selectMyReport(sId, searchType, keyword, startRow, listLimit);
		
		// 나의 신고 목록 갯수 조회
		int listCount = service.selectMyReportCount(sId, searchType, keyword);
		
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;

		int endPage = startPage + pageListLimit - 1;

		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(
				pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		model.addAttribute("report", report);
		model.addAttribute("pageInfo", pageInfo);
		
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "member/my_report";
	}
	
	// member/my_report_detail.jsp
	@GetMapping(value = "/my_report_detail.me")
	public String my_report_detail(@RequestParam String sId, Model model) {
		
		// 나의 신고 상세 조회
		ReportVO reportDetail = service.selectMyReportDetail(sId);
		
		model.addAttribute("reportDetail", reportDetail);
		
		return "member/my_report_detail";
	}
	
	// member/id_find.jsp
	@GetMapping(value = "/id_find.me")
	public String id_find() {
		return "member/id_find";
	}

	// member/password_find.jsp
	@GetMapping(value = "/password_find.me")
	public String password_find() {
		return "member/password_find";
	}
	
	@PostMapping(value = "/id_findPro.me")
	public String id_FindPro(@RequestParam String member_email, Model model) {
		// 이메일 이용하여 아이디 찾기
		String result = service.id_findPro(member_email);
		
		if (result == null) {
			model.addAttribute("msg", "아이디 찾기가 실패되었습니다. 다시 시도해 주세요.");
			return "member/fail_back";
		}
		
		return "redirect:/id_find_success.me";
	}
	
	@PostMapping(value = "/password_findPro.me")
	public String password_FindPro(@RequestParam String member_email, Model model) {
		// 이메일 이용하여 비밀번호 찾기
		int updateCount = service.password_findPro(member_email);
		
		if (updateCount == 0) {
			model.addAttribute("msg", "비밀번호 찾기가 실패되었습니다. 다시 시도해 주세요.");
			return "member/fail_back";
		}
		
		return "redirect:/password_find_success.me";
	}
	
	// member/id_find_success.jsp
	@GetMapping(value = "/id_find_success.me")
	public String id_find_success() {
		return "member/id_find_success";
	}

	// member/password_find_success.jsp
	@GetMapping(value = "/password_find_success.me")
	public String password_find_success() {
		return "member/password_find_success";
	}
	
}
