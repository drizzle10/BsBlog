package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class EtcVO {

//	CREATE TABLE etc(
//			etc_num INT PRIMARY KEY,
//			etc_name VARCHAR(10) NOT NULL,
//			etc_password VARCHAR(20) NOT NULL,
//			etc_subject VARCHAR(50) NOT NULL,
//			etc_content VARCHAR(2000) NOT NULL,
//			etc_file VARCHAR(50) NOT NULL,
//			etc_realfile VARCHAR(50) NOT NULL,
//			etc_re_ref INT NOT NULL,
//			etc_re_lev INT NOT NULL,
//			etc_re_seq INT NOT NULL,
//			etc_readcount INT DEFAULT 0,
//			etc_date DATE NOT NULL
//	);
	
	private int etc_num;
	private String etc_name;
	private String etc_password;
	private String etc_subject;
	private String etc_content;
	private String etc_file;
	private String etc_realfile;
	private int etc_re_ref;
	private int etc_re_lev;
	private int etc_re_seq;
	private int etc_readcount;
	private Date etc_date;
	private MultipartFile file;
	
	public int getEtc_num() {
		return etc_num;
	}
	public void setEtc_num(int etc_num) {
		this.etc_num = etc_num;
	}
	public String getEtc_name() {
		return etc_name;
	}
	public void setEtc_name(String etc_name) {
		this.etc_name = etc_name;
	}
	public String getEtc_password() {
		return etc_password;
	}
	public void setEtc_password(String etc_password) {
		this.etc_password = etc_password;
	}
	public String getEtc_subject() {
		return etc_subject;
	}
	public void setEtc_subject(String etc_subject) {
		this.etc_subject = etc_subject;
	}
	public String getEtc_content() {
		return etc_content;
	}
	public void setEtc_content(String etc_content) {
		this.etc_content = etc_content;
	}
	public String getEtc_file() {
		return etc_file;
	}
	public void setEtc_file(String etc_file) {
		this.etc_file = etc_file;
	}
	public String getEtc_realfile() {
		return etc_realfile;
	}
	public void setEtc_realfile(String etc_realfile) {
		this.etc_realfile = etc_realfile;
	}
	public int getEtc_re_ref() {
		return etc_re_ref;
	}
	public void setEtc_re_ref(int etc_re_ref) {
		this.etc_re_ref = etc_re_ref;
	}
	public int getEtc_re_lev() {
		return etc_re_lev;
	}
	public void setEtc_re_lev(int etc_re_lev) {
		this.etc_re_lev = etc_re_lev;
	}
	public int getEtc_re_seq() {
		return etc_re_seq;
	}
	public void setEtc_re_seq(int etc_re_seq) {
		this.etc_re_seq = etc_re_seq;
	}
	public int getEtc_readcount() {
		return etc_readcount;
	}
	public void setEtc_readcount(int etc_readcount) {
		this.etc_readcount = etc_readcount;
	}
	public Date getEtc_date() {
		return etc_date;
	}
	public void setEtc_date(Date etc_date) {
		this.etc_date = etc_date;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		return "EtcVO [etc_num=" + etc_num + ", etc_name=" + etc_name + ", etc_password=" + etc_password
				+ ", etc_subject=" + etc_subject + ", etc_content=" + etc_content + ", etc_file=" + etc_file
				+ ", etc_realfile=" + etc_realfile + ", etc_re_ref=" + etc_re_ref + ", etc_re_lev=" + etc_re_lev
				+ ", etc_re_seq=" + etc_re_seq + ", etc_readcount=" + etc_readcount + ", etc_date=" + etc_date
				+ ", file=" + file + "]";
	}
	
	
	
}
