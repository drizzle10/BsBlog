package com.project.BsBlog.vo;

import java.sql.Date;

public class MemberVO {
//	CREATE TABLE member(
//			member_idx INT PRIMARY KEY AUTO_INCREMENT,
//			member_id VARCHAR(16) UNIQUE NOT NULL,
//			member_password VARCHAR(20) NOT NULL,
//			member_name VARCHAR(10) NOT NULL,
//			member_address VARCHAR(100) NOT NULL,
//			member_phone VARCHAR(40) UNIQUE NOT NULL,
//			member_email VARCHAR(40) UNIQUE NOT NULL,
//			member_email_auth VARCHAR(1) NOT NULL,
//			member_date DATE NOT NULL
//	);
	
	private int member_idx;
	private String member_id;
	private String member_password;
	private String member_name;
	private String member_address;
	private String member_postcode;
	private String member_phone;
	private String member_email;
	private String member_email_auth;
	private Date member_date;
	
	public int getMember_idx() {
		return member_idx;
	}
	public void setMember_idx(int member_idx) {
		this.member_idx = member_idx;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_postcode() {
		return member_postcode;
	}
	public void setMember_postcode(String member_postcode) {
		this.member_postcode = member_postcode;
	}
	public String getMember_phone() {
		return member_phone;
	}
	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_email_auth() {
		return member_email_auth;
	}
	public void setMember_email_auth(String member_email_auth) {
		this.member_email_auth = member_email_auth;
	}
	public Date getMember_date() {
		return member_date;
	}
	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	
	@Override
	public String toString() {
		return "MemberVO [member_idx=" + member_idx + ", member_id=" + member_id + ", member_password="
				+ member_password + ", member_name=" + member_name + ", member_address=" + member_address
				+ ", member_postcode=" + member_postcode + ", member_phone=" + member_phone + ", member_email="
				+ member_email + ", member_email_auth=" + member_email_auth + ", member_date=" + member_date + "]";
	}
	
	
	
}
