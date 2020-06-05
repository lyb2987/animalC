package com.solproject.animalcrossing.qboardInter;

import java.util.List;

import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.qboard.QBoardVo;

public interface QBoardDao {
	public int writeQBoard(QBoardVo vo);
	
	public int getQBoardCount();
	
	public List<QBoardVo> getQBoardPageList(Paging p);
}
