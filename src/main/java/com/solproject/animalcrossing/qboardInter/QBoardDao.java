package com.solproject.animalcrossing.qboardInter;

import java.util.List;

import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.qboard.QBoardVo;

public interface QBoardDao {
	public int writeQBoard(QBoardVo vo);
	
	public int getQBoardCount();
	
	public List<QBoardVo> getQBoardPageList(Paging p);
	
	public int increaseViewCnt(int qbno);
	
	public QBoardVo getBoard(int qbno);
	
	public int likeUp(int qbno);
	
	public int likeDown(int qbno);

	public int getLikeCnt(int qbno);

	public int deleteQBoard(int qbno);

	public int modifyQBoard(QBoardVo vo);

	public int increaseAcnt(int qbno);

	public int decreaseAcnt(int qbno);
}
