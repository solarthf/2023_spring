package kh.spring.s02.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s02.board.model.dao.BoardDao;
import kh.spring.s02.board.model.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao dao;
	
	@Override
	public int insert(BoardVo vo) {
		if(vo.getBoardNum() != 0) {
			// 답글 (원글 = 0) 순서 1 추가
			dao.updateForReply(vo.getBoardNum());
		}
		return dao.insert(vo);
	}

	@Override
	public int update(BoardVo vo) {
		return dao.update(vo);
	}

	@Override
	public int delete(int boardNum) {
		return dao.delete(boardNum);
	}

	@Override
	public BoardVo selectOne(int boardNum, String writer) {
		BoardVo result = dao.selectOne(boardNum);
		if(!result.getBoardWriter().equals(writer)) {
			dao.updateReadCount(boardNum); // 작성자가 같지 않다면 조회수 증가하게 해달라
		}
		return result;
//		if(dao.updateReadCount(boardNum) > 0) {
//			return dao.selectOne(boardNum);
//		} else {
//			return null;
//		}
	}

	@Override
	public List<BoardVo> selectList() {
		return dao.selectList();
	}

	@Override
	public int selectOneCount() {
		return dao.selectOneCount();
	}

	@Override
	public List<BoardVo> selectList(int currentPage, int limit) {
		return dao.selectList(currentPage, limit);
	}

	
	
}
