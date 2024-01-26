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
		String securePasswd = encoder.encode(member.getMember_password());
		// 3. MemberVO 객체의 패스워드에 암호문 저장
		member.setMember_password(securePasswd);

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
	
	// 메일 중복 확인
	// * responsebody 이유?
	@ResponseBody
	@PostMapping(value = "/idDup_check")
	public int idDup_check(@RequestParam("member_id") String member_id, @ModelAttribute MemberVO member) {
		System.out.println(member_id);
		System.out.println(member);
		
		int idDup_checkCount = service.idDup_check(member_id);

		System.out.println(idDup_checkCount);
		
		return idDup_checkCount;
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
	
	// 로그아웃
	@GetMapping(value = "/logout.me")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// TODO
	// 마이페이지
	// 관리자
	// 댓글
	// 게시판에 아이디 넣을지?
	// 게시판 신고기능
	
}
