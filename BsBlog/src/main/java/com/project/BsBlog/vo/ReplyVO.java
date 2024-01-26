package com.project.BsBlog.vo;

import java.sql.Date;

public class ReplyVO {
//	CREATE TABLE reply(
//			reply_idx INT PRIMARY KEY, 
//			reply_id VARCHAR(16) NOT NULL,
//			reply_content VARCHAR(1000) NOT NULL,
//			reply_re_ref INT NOT NULL,
//			reply_re_lev INT NOT NULL,
//			reply_re_seq INT NOT NULL,
//			reply_date DATE NOT NULL,
//			FOREIGN KEY(reply_idx) REFERENCES news(news_num) ON DELETE CASCADE
//	);
	
	private int reply_idx; // 댓글 번호
	private String reply_id; // 댓글 작성자 아이디
	private String reply_content; // 댓글 내용
	private int reply_re_ref; // 원본 댓글 참조 번호
	private int reply_re_lev; // 댓글 레벨
	private int reply_re_seq; // 댓글 시퀀스 번호
	private int reply_ne_ref; // 댓글 참조 게시글 번호(참조키)
	private Date reply_date; // 댓글 작성일
	
	public int getReply_idx() {
		return reply_idx;
	}
	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}
	public String getReply_id() {
		return reply_id;
	}
	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public int getReply_re_ref() {
		return reply_re_ref;
	}
	public void setReply_re_ref(int reply_re_ref) {
		this.reply_re_ref = reply_re_ref;
	}
	public int getReply_re_lev() {
		return reply_re_lev;
	}
	public void setReply_re_lev(int reply_re_lev) {
		this.reply_re_lev = reply_re_lev;
	}
	public int getReply_re_seq() {
		return reply_re_seq;
	}
	public void setReply_re_seq(int reply_re_seq) {
		this.reply_re_seq = reply_re_seq;
	}
	public int getReply_ne_ref() {
		return reply_ne_ref;
	}
	public void setReply_ne_ref(int reply_ne_ref) {
		this.reply_ne_ref = reply_ne_ref;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [reply_idx=" + reply_idx + ", reply_id=" + reply_id + ", reply_content=" + reply_content
				+ ", reply_re_ref=" + reply_re_ref + ", reply_re_lev=" + reply_re_lev + ", reply_re_seq=" + reply_re_seq
				+ ", reply_ne_ref=" + reply_ne_ref + ", reply_date=" + reply_date + "]";
	}
	
	
}
