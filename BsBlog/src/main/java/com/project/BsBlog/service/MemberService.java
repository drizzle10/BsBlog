package com.project.BsBlog.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.MemberMapper;
import com.project.BsBlog.vo.MemberVO;

@Service
public class MemberService {

	@Autowired
	MemberMapper mapper;
	// root-context.xml bean 등록 필수
	// * 왜?
	@Autowired
	private JavaMailSenderImpl mailSender;

	// 난수
	private int authNumber; 
	
	// 인증코드 생성
	public void makeRandomNumber() {
		// 난수 범위 111111 ~ 999999 (6자리)
		Random r = new Random();
		int randomNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호 : " + randomNum);
		authNumber = randomNum;
	}
	
	// 이메일 인증 => 인증 메일 양식
	public String mailAuth_check(String email) {
		// * 정리
		// 인증 코드 생성 함수 호출
		makeRandomNumber();
		String mailFrom = "bsblog@bsblog.com";
		String mailTo = email;
		String title = "BsBlog - 회원 가입 인증 이메일 입니다."; // 이메일 제목
		String content = "" + // html 형식으로 작성
				"<br><br>" + "인증 번호는 " + authNumber + " 입니다." + "<br>" + "가입시 인증번호를 입력해주세요."; // 이메일 내용 삽입
		mailAuth_send(mailFrom, mailTo, title, content);
		return Integer.toString(authNumber);
	}
	
	// 이메일 전송 메소드
	// * MimeMessage pom.xml 작성 필요 
	// * MimeMessageHelper도 마찬가지
	// * 위 두개 쓰는 이유?
	public void mailAuth_send(String mailFrom, String mailTo, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(mailFrom);
			helper.setTo(mailTo);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	// 가입
	public int joinPro(MemberVO member) {
		return mapper.joinPro(member);
	}

	// 아이디 중복 체크
	public int idDup_check(String member_id) {
		return mapper.idDup_check(member_id);
	}

}
