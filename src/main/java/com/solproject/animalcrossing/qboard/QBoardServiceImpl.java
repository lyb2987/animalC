package com.solproject.animalcrossing.qboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.qboardInter.QBoardDao;
import com.solproject.animalcrossing.qboardInter.QBoardService;

@Service
public class QBoardServiceImpl implements QBoardService{
	
	@Autowired
	private QBoardDao qBoardDao;

	@Override
	public int writeQBoard(QBoardVo vo) {
		return qBoardDao.writeQBoard(vo);
	}

	@Override
	public int getQBoardCount() {
		return qBoardDao.getQBoardCount();
	}

	@Override
	public List<QBoardVo> getQBoardPageList(Paging p) {
		return qBoardDao.getQBoardPageList(p);
	}
	
	
}
