package com.solproject.animalcrossing.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.boardInter.BoardDao;
import com.solproject.animalcrossing.boardInter.BoardService;
import com.solproject.animalcrossing.board.Paging;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;

	public int writeboard(BoardVo vo) {
			int result = boardDao.writeBoard(vo);
		return result;
	}

	@Override
	public List<BoardVo> getBoardList() {
		return boardDao.getBoardList();
	}

	@Override
	public List<String> getBoardDateList() {
		return boardDao.getBoardDateList();
	}

	@Override
	public List<BoardVo> getBoardFirstList() {
		return boardDao.getBoardFirstList();
	}

	@Override
	public int getBoardCount() {
		return boardDao.getBoardCount();
	}

	@Override
	public List<BoardVo> getBoardPageList(Paging paging) {
		return boardDao.getBoardPageList(paging);
	}

	@Override
	public BoardVo getBoard(int bno) {
		return boardDao.getBoard(bno);
	}


	
}
