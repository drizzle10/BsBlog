package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoteVO {

//	CREATE TABLE note(
//			note_num INT PRIMARY KEY,
//			note_id VARCHAR(16) NOT NULL,
//			note_password VARCHAR(20) NOT NULL,
//			note_subject VARCHAR(50) NOT NULL,
//			note_content VARCHAR(2000) NOT NULL,
//			note_file VARCHAR(50) NOT NULL,
//			note_realfile VARCHAR(50) NOT NULL,
//			note_re_ref INT NOT NULL,
//			note_re_lev INT NOT NULL,
//			note_re_seq INT NOT NULL,
//			note_readcount INT DEFAULT 0,
//			note_date DATE NOT NULL
//	);
	
	private int note_num;
	private String note_id;
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
	// ------ 마이페이지 내 좋아요한 글 불러오기 위함----
	private Date heart_date;
	
	public int getNote_num() {
		return note_num;
	}
	public void setNote_num(int note_num) {
		this.note_num = note_num;
	}
	public String getNote_id() {
		return note_id;
	}
	public void setNote_id(String note_id) {
		this.note_id = note_id;
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
	public Date getHeart_date() {
		return heart_date;
	}
	public void setHeart_date(Date heart_date) {
		this.heart_date = heart_date;
	}
	
	@Override
	public String toString() {
		return "NoteVO [note_num=" + note_num + ", note_id=" + note_id + ", note_password=" + note_password
				+ ", note_subject=" + note_subject + ", note_content=" + note_content + ", note_file=" + note_file
				+ ", note_realfile=" + note_realfile + ", note_re_ref=" + note_re_ref + ", note_re_lev=" + note_re_lev
				+ ", note_re_seq=" + note_re_seq + ", note_readcount=" + note_readcount + ", note_date=" + note_date
				+ ", file=" + file + ", heart_date=" + heart_date + "]";
	}
	
	
}
