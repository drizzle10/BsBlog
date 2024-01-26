package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class DiaryVO {

//	CREATE TABLE diary(
//			diary_num INT PRIMARY KEY,
//			diary_id VARCHAR(16) NOT NULL,
//			diary_password VARCHAR(20) NOT NULL,
//			diary_subject VARCHAR(50) NOT NULL,
//			diary_content VARCHAR(2000) NOT NULL,
//			diary_file VARCHAR(50) NOT NULL,
//			diary_realfile VARCHAR(50) NOT NULL,
//			diary_re_ref INT NOT NULL,
//			diary_re_lev INT NOT NULL,
//			diary_re_seq INT NOT NULL,
//			diary_readcount INT DEFAULT 0,
//			diary_date DATE NOT NULL
//	);
	
	private int diary_num;
	private String diary_id;
	private String diary_password;
	private String diary_subject;
	private String diary_content;
	private String diary_file;
	private String diary_realfile;
	private int diary_re_ref;
	private int diary_re_lev;
	private int diary_re_seq;
	private int diary_readcount;
	private Date diary_date;
	private MultipartFile file;
	
	public int getDiary_num() {
		return diary_num;
	}
	public void setDiary_num(int diary_num) {
		this.diary_num = diary_num;
	}
	public String getDiary_id() {
		return diary_id;
	}
	public void setDiary_id(String diary_id) {
		this.diary_id = diary_id;
	}
	public String getDiary_password() {
		return diary_password;
	}
	public void setDiary_password(String diary_password) {
		this.diary_password = diary_password;
	}
	public String getDiary_subject() {
		return diary_subject;
	}
	public void setDiary_subject(String diary_subject) {
		this.diary_subject = diary_subject;
	}
	public String getDiary_content() {
		return diary_content;
	}
	public void setDiary_content(String diary_content) {
		this.diary_content = diary_content;
	}
	public String getDiary_file() {
		return diary_file;
	}
	public void setDiary_file(String diary_file) {
		this.diary_file = diary_file;
	}
	public String getDiary_realfile() {
		return diary_realfile;
	}
	public void setDiary_realfile(String diary_realfile) {
		this.diary_realfile = diary_realfile;
	}
	public int getDiary_re_ref() {
		return diary_re_ref;
	}
	public void setDiary_re_ref(int diary_re_ref) {
		this.diary_re_ref = diary_re_ref;
	}
	public int getDiary_re_lev() {
		return diary_re_lev;
	}
	public void setDiary_re_lev(int diary_re_lev) {
		this.diary_re_lev = diary_re_lev;
	}
	public int getDiary_re_seq() {
		return diary_re_seq;
	}
	public void setDiary_re_seq(int diary_re_seq) {
		this.diary_re_seq = diary_re_seq;
	}
	public int getDiary_readcount() {
		return diary_readcount;
	}
	public void setDiary_readcount(int diary_readcount) {
		this.diary_readcount = diary_readcount;
	}
	public Date getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Date diary_date) {
		this.diary_date = diary_date;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "DiaryVO [diary_num=" + diary_num + ", diary_id=" + diary_id + ", diary_password=" + diary_password
				+ ", diary_subject=" + diary_subject + ", diary_content=" + diary_content + ", diary_file=" + diary_file
				+ ", diary_realfile=" + diary_realfile + ", diary_re_ref=" + diary_re_ref + ", diary_re_lev="
				+ diary_re_lev + ", diary_re_seq=" + diary_re_seq + ", diary_readcount=" + diary_readcount
				+ ", diary_date=" + diary_date + ", file=" + file + "]";
	}
	
	
	
	
	
}
