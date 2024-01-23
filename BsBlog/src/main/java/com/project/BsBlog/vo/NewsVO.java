package com.project.BsBlog.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NewsVO {

//	CREATE TABLE news(
//			news_num INT PRIMARY KEY ,
//			news_name VARCHAR(10) NOT NULL,
//			news_password VARCHAR(20) NOT NULL,
//			news_subject VARCHAR(50) NOT NULL,
//			news_content VARCHAR(2000) NOT NULL,
//			news_file VARCHAR(50) NOT NULL,
//			news_realfile VARCHAR(50) NOT NULL,
//			news_re_ref INT NOT NULL,
//			news_re_lev INT NOT NULL,
//			news_re_seq INT NOT NULL,
//			news_readcount INT DEFAULT 0,
//			news_date DATE NOT NULL
//	);
	
	private int news_num;
	private String news_name;
	private String news_password;
	private String news_subject;
	private String news_content;
	private String news_file;
	private String news_realfile;
	private int news_re_ref;
	private int news_re_lev;
	private int news_re_seq;
	private int news_readcount;
	private Date news_date;
	private MultipartFile file;
	
	public int getNews_num() {
		return news_num;
	}
	public void setNews_num(int news_num) {
		this.news_num = news_num;
	}
	public String getNews_name() {
		return news_name;
	}
	public void setNews_name(String news_name) {
		this.news_name = news_name;
	}
	public String getNews_password() {
		return news_password;
	}
	public void setNews_password(String news_password) {
		this.news_password = news_password;
	}
	public String getNews_subject() {
		return news_subject;
	}
	public void setNews_subject(String news_subject) {
		this.news_subject = news_subject;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_file() {
		return news_file;
	}
	public void setNews_file(String news_file) {
		this.news_file = news_file;
	}
	public String getNews_realfile() {
		return news_realfile;
	}
	public void setNews_realfile(String news_realfile) {
		this.news_realfile = news_realfile;
	}
	public int getNews_re_ref() {
		return news_re_ref;
	}
	public void setNews_re_ref(int news_re_ref) {
		this.news_re_ref = news_re_ref;
	}
	public int getNews_re_lev() {
		return news_re_lev;
	}
	public void setNews_re_lev(int news_re_lev) {
		this.news_re_lev = news_re_lev;
	}
	public int getNews_re_seq() {
		return news_re_seq;
	}
	public void setNews_re_seq(int news_re_seq) {
		this.news_re_seq = news_re_seq;
	}
	public int getNews_readcount() {
		return news_readcount;
	}
	public void setNews_readcount(int news_readcount) {
		this.news_readcount = news_readcount;
	}
	public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "NewsVO [news_num=" + news_num + ", news_name=" + news_name + ", news_password=" + news_password
				+ ", news_subject=" + news_subject + ", news_content=" + news_content + ", news_file=" + news_file
				+ ", news_realfile=" + news_realfile + ", news_re_ref=" + news_re_ref + ", news_re_lev=" + news_re_lev
				+ ", news_re_seq=" + news_re_seq + ", news_readcount=" + news_readcount + ", news_date=" + news_date
				+ ", file=" + file + "]";
	}
	
	
	
}
