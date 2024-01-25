package com.project.BsBlog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// member/login.jsp
	@GetMapping(value = "/login.me")
	public String login() {
		return "member/login";
	}

	@PostMapping(value = "/joinPro.me")
	public String joinPro(@ModelAttribute MemberVO member, Model model) {
		System.out.println(member);
		// joinPro하기
		return "";
	}
	
	// 이메일 인증
	@GetMapping("/mail_auth_check")
	// * responsebody 이유
	@ResponseBody
	public String mail_auth_check(@ModelAttribute MemberVO member, Model model, HttpSession session, String email) {
		System.out.println("이메일 인증 이메일 : " + email);
		// * 서비스로 보내는 이유
		return service.mail_auth_check(email);

	}
	
	
	
}
