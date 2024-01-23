package com.project.BsBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.BsBlog.service.NoteService;

@Controller
public class NoteController {
	@Autowired
	public NoteService service;
	
	@GetMapping(value = "/note.bo")
	public String note() {
		return "board/note";
	}
	
}
