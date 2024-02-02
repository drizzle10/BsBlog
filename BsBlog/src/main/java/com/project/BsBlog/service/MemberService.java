package com.project.BsBlog.service;

import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.MemberMapper;
import com.project.BsBlog.vo.MemberVO;
import com.project.BsBlog.vo.NoteVO;
import com.project.BsBlog.vo.ReportVO;

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
		String mailFrom = "bsblog@bsblog.com"; // 이메일 발송자
		String mailTo = email; // 이메일 받을 주소
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

	// 아이디 중복 확인
	public int idDup_check(String member_id) {
		return mapper.idDup_check(member_id);
	}

	// 메일 중복 확인
	public int mailDup_check(String member_email) {
		return mapper.mailDup_check(member_email);
	}
	
	// 로그인 전 비밀번호 조회
	public String select_memberPassword(String member_id) {
		return mapper.select_memberPassword(member_id);
	}

	// 나의 정보 조회
	public MemberVO selectMyInfo(String sId) {
		return mapper.selectMyInfo(sId);
	}

	// 정보 수정
	public int modifyMyInfoPro(MemberVO member) {
		return mapper.modifyMyInfoPro(member);
	}

	// 나의 신고 조회
	public ReportVO selectMyReport(String sId, String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectMyReport(sId, searchType, keyword, startRow, listLimit);
	}

	// 나의 신고 목록 갯수 조회
	public int selectMyReportCount(String sId, String searchType, String keyword) {
		return mapper.selectMyReportCount(sId, searchType, keyword);
	}

	// 나의 신고 상세 조회
	public ReportVO selectMyReportDetail(String sId) {
		return mapper.selectMyReportDetail(sId);
	}

	// 나의 좋아요 조회
	public List<NoteVO> selectMyHeart(String sId, String searchType, String keyword, int startRow, int listLimit) {
		return mapper.selectMyHeart(sId, searchType, keyword, startRow, listLimit);
	}

	// 나의 좋아요 갯수 조회
	public int selectMyHeartCount(String sId, String searchType, String keyword) {
		return mapper.selectMyHeartCount(sId, searchType, keyword);
	}
	
	// 탈퇴
	public int deleteMyInfoPro(int member_idx) {
		return mapper.deleteMyInfoPro(member_idx);
	}

	// 아이디 비밀번호 찾기용
	public void mail_send(String mailFrom, String mailTo, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(mailFrom);
			helper.setTo(mailTo);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	// 이메일 이용하여 아이디 찾기
	public String id_findPro(String member_email) {
		
		MemberVO member = mapper.selectMemberEmail(member_email);
		
		if(member == null) {
			return "이메일 없음";
		}
		
		String member_id = member.getMember_id();
		String setFrom = "bsblog@bsblog.com";
		String mailTo = member_email;
		String title = "BsBlog - 아이디 찾기 이메일 입니다.";
		String content = "" + "<br><br>" + "아이디는 " + member_id + " 입니다."; // 이메일 내용
		
		mail_send(setFrom, mailTo, title, content);
		return "이메일 있음";
	}

	// 이메일 이용하여 비밀번호 찾기
	public int password_findPro(String member_email) {
		makeRandomNumber();
		
		MemberVO member = mapper.selectMemberEmail(member_email);
		
		if(member == null) {
			return 0;
		}
		
		String setFrom = "bsblog@bsblog.com";
		String mailTo = member_email;
		String title = "BsBlog - 임시 비밀번호 이메일 입니다.";
		// --- ① 난수 생성 후 메일로 보낼 임시 비밀번호 적기
		String content = "" + "<br><br>" + "임시 비밀번호는 " + authNumber + " 입니다." + "<br>" + "임시 비밀번호로 로그인 후 비밀번호를 변경하여 주세요."; // 이메일 내용
		
		// 임시 비밀번호 전송 전 임시 비밀번호를 멤버 테이블에 넣는 과정
		// 1. BCryptPasswordEncoder 객체 생성
		// --- ② 생성한 난수를 비밀번호에 저장
		member.setMember_password(authNumber + "");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 2. BCryptPasswordEncoder 객체의 encode() 메서드를 호출하여 해싱 결과 리턴
		// --- ③ 비밀번호에 저장한 난수를 암호화
		String securePassword = encoder.encode(member.getMember_password());
		// 3. MemberVO 객체의 패스워드에 암호문 저장
		// --- ④ 암호화한 난수를 비밀번호에 엎어쓰기
		member.setMember_password(securePassword);
		int updateCount =  mapper.modifyMyInfoPro(member);

		// --- ⑤ 메일로 비밀번호 전송
		mail_send(setFrom, mailTo, title, content);
		
		return updateCount;
	}

	

	

}
