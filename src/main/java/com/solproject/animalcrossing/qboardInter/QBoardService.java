package com.solproject.animalcrossing.qboardInter;

import java.util.List;

import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.qboard.QBoardVo;

public interface QBoardService {
	public int writeQBoard(QBoardVo vo);

	public int getQBoardCount();

	public List<QBoardVo> getQBoardPageList(Paging p);
	
	public int increaseViewCnt(int qbno);
	
	public QBoardVo getQBoard(int qbno);

	public int likeUp(int qbno);

	public int likeDown(int qbno);
}
