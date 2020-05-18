package com.solproject.animalcrossing.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.boardInter.BoardDao;
import com.solproject.animalcrossing.boardInter.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;

	public int writeboard(BoardVo vo) {
			int result = boardDao.writeBoard(vo);
		return result;
	}


	
}
