package com.project.BsBlog.mapper;

import com.project.BsBlog.vo.MemberVO;

public interface MemberMapper {

	// 가입
	int joinPro(MemberVO member);

	// 아이디 중복 확인
	int idDup_check(String member_id);

	// 로그인 전 비밀번호 조회
	String select_memberPassword(String member_id);

}
