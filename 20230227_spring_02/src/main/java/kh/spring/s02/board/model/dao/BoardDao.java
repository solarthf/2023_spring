package kh.spring.s02.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kh.spring.s02.board.controller.BoardController;
import kh.spring.s02.board.model.vo.BoardVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession; // root-context의 id와 동일(대소문자 구분)하게 적는다.
	
	public int insert(BoardVo vo) {
		return sqlSession.insert("boardns.insertid", vo);
	}
	
	public int update(BoardVo vo) {
		return sqlSession.update("boardns.updateid", vo);
	}
	public int updateReadCount(int boardNum) {
		return sqlSession.update("boardns.updateReadCount", boardNum);
	}
	public int updateForReply(int boardNum) {
		return sqlSession.update("boardns.updateForReply", boardNum);
	}
	public int delete(BoardVo vo) {
		return sqlSession.delete("boardns.deleteid", vo);
	}
	
	public int delete(int boardNum) { // BoardVo vo 대신 PK(int), 여러개(List<Integer>)를 넣을수도 있다.
		return sqlSession.delete("boardns.deleteid", boardNum);
	}
	
	public BoardVo selectOne(int boardNum) { // PK
		return sqlSession.selectOne("boardns.selectOneid", boardNum);
	}
	public List<BoardVo> selectList() {
		return sqlSession.selectList("boardns.selectListid");
	}
	public List<BoardVo> selectList(int currentPage, int limit) {
//		int offset = (currentPage-1)*limit;
//		RowBounds rb = new RowBounds(offset, limit);				
//		return sqlSession.selectList("boardns.selectListid", null, rb);
		return sqlSession.selectList("boardns.selectListid", null, new RowBounds((currentPage-1)*limit, limit));
	}
	public int selectOneCount() {
		return sqlSession.selectOne("boardns.selectOneCount");
	}

	
}
