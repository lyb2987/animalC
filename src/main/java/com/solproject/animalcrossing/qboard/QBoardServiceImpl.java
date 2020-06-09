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

	@Override
	public int increaseViewCnt(int qbno) {
		return qBoardDao.increaseViewCnt(qbno);
	}

	@Override
	public QBoardVo getQBoard(int qbno) {
		return qBoardDao.getBoard(qbno);
	}

	@Override
	public int likeUp(int qbno) {
		return qBoardDao.likeUp(qbno);
	}

	@Override
	public int likeDown(int qbno) {
		return qBoardDao.likeDown(qbno);
	}

	@Override
	public int getLikeCnt(int qbno) {
		return qBoardDao.getLikeCnt(qbno);
	}

	@Override
	public int deleteQBoard(int qbno) {
		return qBoardDao.deleteQBoard(qbno);
	}

	@Override
	public int modifyQBoard(QBoardVo vo) {
		return qBoardDao.modifyQBoard(vo);
	}

	@Override
	public int increaseAcnt(int qbno) {
		return qBoardDao.increaseAcnt(qbno);
	}

	@Override
	public int decreaseAcnt(int qbno) {
		return qBoardDao.decreaseAcnt(qbno);
	}
	
	
}
