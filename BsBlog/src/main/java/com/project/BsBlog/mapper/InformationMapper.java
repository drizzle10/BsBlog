package com.project.BsBlog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.BsBlog.vo.GuestbookVO;
import com.project.BsBlog.vo.NewsVO;
import com.project.BsBlog.vo.ReplyVO;

// * 인터페이스 쓰는 이유?
public interface InformationMapper {


	// 글 목록
	// * 왜 param 쓰는지
	List<NewsVO> selectNews(@Param("startRow") int startRow, @Param("listLimit") int listLimit,  @Param("searchType") String searchType,  @Param("keyword") String keyword);

	// 글 목록 갯수 조회
	int selectNewsCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

	// 글 작성
	int writeNewsPro(NewsVO news);
	
	// 글 상세 조회
	NewsVO selectNewsDetail(int news_num);

	// 글 조회시 조회수 증가
	void increaseNewsReadCount(int news_num);
	
	// 글 수정
	int modifyNewsPro(NewsVO news);

	// 글 삭제 전 실제 업로드된 파일명 조회
	String selectRealFile(int news_num);

	// 글 삭제
	int deleteNewsPro(NewsVO news);

	// 댓글 작성
	int writeReplyPro(ReplyVO reply);

	// 댓글 조회
	List<ReplyVO> selectReply(int reply_ne_ref);

	// 댓글 갯수 조회
	int selectReplyCount(int reply_ne_ref);

	// 대댓글 순서번호 조정(reply_re_seq)
	void increaseReReplyReSeq(ReplyVO reply);

	// 대댓글 작성
	int reReplyWritePro(ReplyVO reply);

	// 원 댓글 삭제 + 원 댓글 삭제시 대댓글도 삭제
	int replyDeletePro(int reply_idx);

	// 홈에서 뉴스 목록 2개 불러오기
	List<NewsVO> selectNewsHome();

	// 홈에서 게스트북 목록 2개 불러오기
	List<GuestbookVO> selectGuestbookHome();

	// 댓글 수정
	int modifyReplyPro(ReplyVO reply);


	

}
