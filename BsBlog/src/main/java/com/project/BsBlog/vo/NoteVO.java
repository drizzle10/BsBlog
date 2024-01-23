package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoteVO {

//	CREATE TABLE note(
//			note_num INT PRIMARY KEY,
//			note_name VARCHAR(10) not null,
//			note_password VARCHAR(20) not null,
//			note_subject VARCHAR(50) not null,
//			note_content VARCHAR(2000) not null,
//			note_file VARCHAR(50) not null,
//			note_realfile VARCHAR(50) not null,
//			note_re_ref INT NOT NULL,
//			note_re_lev INT NOT NULL,
//			note_re_seq INT NOT NULL,
//			note_readcount INT default 0,
//			note_date DATE not null
//	);
	
	private int note_num;
	private String note_name;
	private String note_password;
	private String note_subject;
	private String note_content;
	private String note_file;
	private String note_realfile;
	private int note_re_ref;
	private int note_re_lev;
	private int note_re_seq;
	private int note_readcount;
	private Date note_date;
	private MultipartFile file;
	
	public int getNote_num() {
		return note_num;
	}
	public void setNote_num(int note_num) {
		this.note_num = note_num;
	}
	public String getNote_name() {
		return note_name;
	}
	public void setNote_name(String note_name) {
		this.note_name = note_name;
	}
	public String getNote_password() {
		return note_password;
	}
	public void setNote_password(String note_password) {
		this.note_password = note_password;
	}
	public String getNote_subject() {
		return note_subject;
	}
	public void setNote_subject(String note_subject) {
		this.note_subject = note_subject;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public String getNote_file() {
		return note_file;
	}
	public void setNote_file(String note_file) {
		this.note_file = note_file;
	}
	public String getNote_realfile() {
		return note_realfile;
	}
	public void setNote_realfile(String note_realfile) {
		this.note_realfile = note_realfile;
	}
	public int getNote_re_ref() {
		return note_re_ref;
	}
	public void setNote_re_ref(int note_re_ref) {
		this.note_re_ref = note_re_ref;
	}
	public int getNote_re_lev() {
		return note_re_lev;
	}
	public void setNote_re_lev(int note_re_lev) {
		this.note_re_lev = note_re_lev;
	}
	public int getNote_re_seq() {
		return note_re_seq;
	}
	public void setNote_re_seq(int note_re_seq) {
		this.note_re_seq = note_re_seq;
	}
	public int getNote_readcount() {
		return note_readcount;
	}
	public void setNote_readcount(int note_readcount) {
		this.note_readcount = note_readcount;
	}
	public Date getNote_date() {
		return note_date;
	}
	public void setNote_date(Date note_date) {
		this.note_date = note_date;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "NoteVO [note_num=" + note_num + ", note_name=" + note_name + ", note_password=" + note_password
				+ ", note_subject=" + note_subject + ", note_content=" + note_content + ", note_file=" + note_file
				+ ", note_realfile=" + note_realfile + ", note_re_ref=" + note_re_ref + ", note_re_lev=" + note_re_lev
				+ ", note_re_seq=" + note_re_seq + ", note_readcount=" + note_readcount + ", note_date=" + note_date
				+ ", file=" + file + "]";
	}
	
	
	
}
