package com.project.BsBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.BsBlog.mapper.InformationMapper;
import com.project.BsBlog.vo.NewsVO;
import com.project.BsBlog.vo.ReplyVO;

@Service
public class InformationService {

	@Autowired
	private InformationMapper mapper;
	
	// 글 목록
	public List<NewsVO> selectNews(int startRow, int listLimit, String searchType, String keyword) {
		return mapper.selectNews(startRow, listLimit, searchType, keyword);
	}

	
	// 글 목록 갯수 조회
	public int selectNewsCount(String searchType, String keyword) {
		return mapper.selectNewsCount(searchType, keyword);
	}
	
	// 글 작성
	public int writeNewsPro(NewsVO news) {
		System.out.println("여기는 서비스");
		System.out.println("news.getFile() : " + news.getFile());
		System.out.println("news.getNews_file() : " + news.getNews_file());
		System.out.println("news.getNews_realfile() : " + news.getNews_realfile());
		return mapper.writeNewsPro(news);
	}

	// 글 상세 조회
	public NewsVO selectNewsDetail(int news_num) {
		return mapper.selectNewsDetail(news_num);
	}
	
	// 글 조회시 조회수 증가
	public void increaseNewsReadCount(int news_num) {
		mapper.increaseNewsReadCount(news_num);
	}
	
	// 글 수정
	public int modifyNewsPro(NewsVO news) {
		return mapper.modifyNewsPro(news);
	}

	// 글 삭제 전 실제 업로드된 파일명 조회
	// * 굳이 조회하는 이유?
	public String selectRealFile(int news_num) {
		return mapper.selectRealFile(news_num);
	}

	// 글 삭제
	public int deleteNewsPro(NewsVO news) {
		return mapper.deleteNewsPro(news);
	}

	// 댓글 작성
	public int writeReplyPro(ReplyVO reply) {
		return mapper.writeReplyPro(reply);
	}


	// 댓글 조회
	public List<ReplyVO> selectReply(int reply_ne_ref) {
		return mapper.selectReply(reply_ne_ref);
	}

	// 댓글 갯수 조회
	public int selectReplyCount(int reply_ne_ref) {
		return mapper.selectReplyCount(reply_ne_ref);
	}


	// 대댓글 순서번호(reply_re_seq) 조정
	public void increaseReReplyReSeq(ReplyVO reply) {
		mapper.increaseReReplyReSeq(reply);
	}


	// 대댓글 등록
	public int reReplyWritePro(ReplyVO reply) {
		return mapper.reReplyWritePro(reply);
	}

	// 원 댓글 삭제 + 원 댓글 삭제시 대댓글도 삭제
	public int replyDeletePro(int reply_idx) {
		return mapper.replyDeletePro(reply_idx);
	}

	

	

}
