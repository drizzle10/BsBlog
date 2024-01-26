package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class GuestbookVO {
//	CREATE TABLE guestbook(
//			guestbook_num INT PRIMARY KEY,
//			guestbook_id VARCHAR(16) NOT NULL,
//			guestbook_password VARCHAR(20) NOT NULL,
//			guestbook_subject VARCHAR(50) NOT NULL,
//			guestbook_content VARCHAR(2000) NOT NULL,
//			guestbook_file VARCHAR(50) NOT NULL,
//			guestbook_realfile VARCHAR(50) NOT NULL,
//			guestbook_re_ref INT NOT NULL,
//			guestbook_re_lev INT NOT NULL,
//			guestbook_re_seq INT NOT NULL,
//			guestbook_readcount INT DEFAULT 0,
//			guestbook_date DATE NOT NULL
//	);
	
	private int guestbook_num;
	private String guestbook_id;
	private String guestbook_password;
	private String guestbook_subject;
	private String guestbook_content;
	private String guestbook_file;
	private String guestbook_realfile;
	private int guestbook_re_ref;
	private int guestbook_re_lev;
	private int guestbook_re_seq;
	private int guestbook_readcount;
	private Date guestbook_date;
	private MultipartFile file;
	
	public int getGuestbook_num() {
		return guestbook_num;
	}
	public void setGuestbook_num(int guestbook_num) {
		this.guestbook_num = guestbook_num;
	}
	public String getGuestbook_id() {
		return guestbook_id;
	}
	public void setGuestbook_id(String guestbook_id) {
		this.guestbook_id = guestbook_id;
	}
	public String getGuestbook_password() {
		return guestbook_password;
	}
	public void setGuestbook_password(String guestbook_password) {
		this.guestbook_password = guestbook_password;
	}
	public String getGuestbook_subject() {
		return guestbook_subject;
	}
	public void setGuestbook_subject(String guestbook_subject) {
		this.guestbook_subject = guestbook_subject;
	}
	public String getGuestbook_content() {
		return guestbook_content;
	}
	public void setGuestbook_content(String guestbook_content) {
		this.guestbook_content = guestbook_content;
	}
	public String getGuestbook_file() {
		return guestbook_file;
	}
	public void setGuestbook_file(String guestbook_file) {
		this.guestbook_file = guestbook_file;
	}
	public String getGuestbook_realfile() {
		return guestbook_realfile;
	}
	public void setGuestbook_realfile(String guestbook_realfile) {
		this.guestbook_realfile = guestbook_realfile;
	}
	public int getGuestbook_re_ref() {
		return guestbook_re_ref;
	}
	public void setGuestbook_re_ref(int guestbook_re_ref) {
		this.guestbook_re_ref = guestbook_re_ref;
	}
	public int getGuestbook_re_lev() {
		return guestbook_re_lev;
	}
	public void setGuestbook_re_lev(int guestbook_re_lev) {
		this.guestbook_re_lev = guestbook_re_lev;
	}
	public int getGuestbook_re_seq() {
		return guestbook_re_seq;
	}
	public void setGuestbook_re_seq(int guestbook_re_seq) {
		this.guestbook_re_seq = guestbook_re_seq;
	}
	public int getGuestbook_readcount() {
		return guestbook_readcount;
	}
	public void setGuestbook_readcount(int guestbook_readcount) {
		this.guestbook_readcount = guestbook_readcount;
	}
	public Date getGuestbook_date() {
		return guestbook_date;
	}
	public void setGuestbook_date(Date guestbook_date) {
		this.guestbook_date = guestbook_date;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "GuestbookVO [guestbook_num=" + guestbook_num + ", guestbook_id=" + guestbook_id
				+ ", guestbook_password=" + guestbook_password + ", guestbook_subject=" + guestbook_subject
				+ ", guestbook_content=" + guestbook_content + ", guestbook_file=" + guestbook_file
				+ ", guestbook_realfile=" + guestbook_realfile + ", guestbook_re_ref=" + guestbook_re_ref
				+ ", guestbook_re_lev=" + guestbook_re_lev + ", guestbook_re_seq=" + guestbook_re_seq
				+ ", guestbook_readcount=" + guestbook_readcount + ", guestbook_date=" + guestbook_date + ", file="
				+ file + "]";
	}
	
	
	
	
}
