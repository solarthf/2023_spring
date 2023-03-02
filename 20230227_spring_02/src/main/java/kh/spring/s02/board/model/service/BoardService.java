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
	public List<BoardVo> selectList(); // 전체 페이지 읽기
	public List<BoardVo> selectList(int currentPage, int limit); // paging 처리하여 읽기
	public int selectOneCount();
	
}
