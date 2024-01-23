package com.project.BsBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.EtcMapper;

@Service
public class EtcService {
	@Autowired
	public EtcMapper mapper;
}
