package kh.spring.s01.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kh.spring.s01.board.model.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired 
	private BoardDao dao;
	@Override
	public int selectCount() {
		return dao.selectCount();
	}

}
