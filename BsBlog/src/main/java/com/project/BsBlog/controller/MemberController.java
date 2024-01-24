package com.project.BsBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.BsBlog.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
}
