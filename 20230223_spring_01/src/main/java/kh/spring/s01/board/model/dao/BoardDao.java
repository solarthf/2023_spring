package kh.spring.s01.board.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public int selectCount() {
		return sqlSession.selectOne("board.count");
	}
}
