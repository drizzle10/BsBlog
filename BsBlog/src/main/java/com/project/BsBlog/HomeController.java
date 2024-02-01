package com.project.BsBlog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.BsBlog.service.GuestbookService;
import com.project.BsBlog.service.InformationService;
import com.project.BsBlog.vo.GuestbookVO;
import com.project.BsBlog.vo.NewsVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	InformationService Iservice;
	@Autowired
	GuestbookService Gservice;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		// 홈에서 뉴스 목록 2개 불러오기
		List<NewsVO> news = Iservice.selectNewsHome();
		
		// 홈에서 게스트북 목록 2개 불러오기
		List<GuestbookVO> guestbook = Gservice.selectGuestbookHome();
		System.out.println(guestbook);

		model.addAttribute("news", news);
		model.addAttribute("guestbook", guestbook);
		
		
		return "home";
	}
	
}











