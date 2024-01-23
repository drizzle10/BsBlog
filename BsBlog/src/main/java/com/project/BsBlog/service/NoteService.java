package com.project.BsBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.NoteMapper;

@Service
public class NoteService {
	@Autowired
	public NoteMapper mapper;
}
