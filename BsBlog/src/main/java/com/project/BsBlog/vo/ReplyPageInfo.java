package com.project.BsBlog.vo;

public class ReplyPageInfo {
	private int replyPageNum; // 현재 페이지 번호
	private int replyListLimit; // 페이지 당 게시물 목록 갯수
	private int replyListCount; // 총 게시물 수
	private int replyPageListLimit; // 페이지 당 표시할 페이지 번호 수
	private int replyMaxPage; // 전체 페이지 수
	private int replyStartPage; // 시작 페이지 번호
	private int replyEndPage; // 끝 페이지 번호
	
	public ReplyPageInfo() {}

	public ReplyPageInfo(int replyPageNum, int replyListLimit, int replyListCount, int replyPageListLimit,
			int replyMaxPage, int replyStartPage, int replyEndPage) {
		super();
		this.replyPageNum = replyPageNum;
		this.replyListLimit = replyListLimit;
		this.replyListCount = replyListCount;
		this.replyPageListLimit = replyPageListLimit;
		this.replyMaxPage = replyMaxPage;
		this.replyStartPage = replyStartPage;
		this.replyEndPage = replyEndPage;
	}

	public int getReplyPageNum() {
		return replyPageNum;
	}

	public void setReplyPageNum(int replyPageNum) {
		this.replyPageNum = replyPageNum;
	}

	public int getReplyListLimit() {
		return replyListLimit;
	}

	public void setReplyListLimit(int replyListLimit) {
		this.replyListLimit = replyListLimit;
	}

	public int getReplyLlistCount() {
		return replyListCount;
	}

	public void setReplyLlistCount(int replyLlistCount) {
		this.replyListCount = replyLlistCount;
	}

	public int getReplyPageListLimit() {
		return replyPageListLimit;
	}

	public void setReplyPageListLimit(int replyPageListLimit) {
		this.replyPageListLimit = replyPageListLimit;
	}

	public int getReplyMaxPage() {
		return replyMaxPage;
	}

	public void setReplyMaxPage(int replyMaxPage) {
		this.replyMaxPage = replyMaxPage;
	}

	public int getReplyStartPage() {
		return replyStartPage;
	}

	public void setReplyStartPage(int replyStartPage) {
		this.replyStartPage = replyStartPage;
	}

	public int getReplyEndPage() {
		return replyEndPage;
	}

	public void setReplyEndPage(int replyEndPage) {
		this.replyEndPage = replyEndPage;
	}

	@Override
	public String toString() {
		return "ReplyPageInfo [replyPageNum=" + replyPageNum + ", replyListLimit=" + replyListLimit
				+ ", replyListCount=" + replyListCount + ", replyPageListLimit=" + replyPageListLimit
				+ ", replyMaxPage=" + replyMaxPage + ", replyStartPage=" + replyStartPage + ", replyEndPage="
				+ replyEndPage + "]";
	}

	
	
	
}
