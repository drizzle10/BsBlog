package com.project.BsBlog.vo;

import java.sql.Date;

public class ReportVO {

//	CREATE TABLE report(
//			report_idx INT PRIMARY KEY,
//			report_guestbook_num INT NOT NULL,
//			report_guestbook_id VARCHAR(16) NOT NULL,
//			report_guestbook_subject VARCHAR(50) NOT NULL,
//			report_guestbook_content VARCHAR(5000) NOT NULL,
//			report_id VARCHAR(16) NOT NULL,
//			report_content VARCHAR(1000) NOT NULL,
//			report_status VARCHAR(5) NOT NULL,
//			report_date DATE NOT NULL
//	);
	
	private int report_idx;					// 신고 번호
	private int	report_guestbook_num;		// guestbook 번호
	private String report_guestbook_id;		// guestbook 작성자
	private String	report_guestbook_subject;	// guestbook 제목
	private String	report_guestbook_content;	// guestbook 내용
	private String report_id;				// 신고 한 사람
	private String report_content;			// 신고 사유
	private String report_status;			// 신고 처리 상태
	private Date report_date;				// 신고 일자
	
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public int getReport_guestbook_num() {
		return report_guestbook_num;
	}
	public void setReport_guestbook_num(int report_guestbook_num) {
		this.report_guestbook_num = report_guestbook_num;
	}
	public String getReport_guestbook_id() {
		return report_guestbook_id;
	}
	public void setReport_guestbook_id(String report_guestbook_id) {
		this.report_guestbook_id = report_guestbook_id;
	}
	public String getReport_guestbook_subject() {
		return report_guestbook_subject;
	}
	public void setReport_guestbook_subject(String report_guestbook_subject) {
		this.report_guestbook_subject = report_guestbook_subject;
	}
	public String getReport_guestbook_content() {
		return report_guestbook_content;
	}
	public void setReport_guestbook_content(String report_guestbook_content) {
		this.report_guestbook_content = report_guestbook_content;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	
	@Override
	public String toString() {
		return "ReportVO [report_idx=" + report_idx + ", report_guestbook_num=" + report_guestbook_num
				+ ", report_guestbook_id=" + report_guestbook_id + ", report_guestbook_subject="
				+ report_guestbook_subject + ", report_guestbook_content=" + report_guestbook_content + ", report_id="
				+ report_id + ", report_content=" + report_content + ", report_status=" + report_status
				+ ", report_date=" + report_date + "]";
	}
	
	
	
	
	
}
