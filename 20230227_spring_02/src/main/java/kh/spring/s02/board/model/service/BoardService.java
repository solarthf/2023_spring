package kh.spring.s02.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kh.spring.s02.board.model.vo.BoardVo;

public interface BoardService {
	public int insert(BoardVo vo);
	public int update(BoardVo vo);
//	public int updateReadCount(int boardNum);
//	public int updateForReply(int boardNum);
	public int delete(int boardNum);
	public BoardVo selectOne(int boardNum, String writer);
	public int selectOneCount();
	public int selectOneCount(String searchWord);

	public List<BoardVo> selectList(); // 전체 페이지 읽기
	public List<BoardVo> selectList(int currentPage, int limit); // paging 처리하여 읽기
	public List<BoardVo> selectList(int currentPage, int limit, String searchWord); // paging처리 + 검색어
	
	public List<BoardVo> selectReplyList(int boardNum); // 어떤 글의 답글전체 읽기
	public List<BoardVo> selectReplyList(int boardNum, int currentPage, int limit); // 어떤 글의 paging 처리한 답글전체 읽기
}
