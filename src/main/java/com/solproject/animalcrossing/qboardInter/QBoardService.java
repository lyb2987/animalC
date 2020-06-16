package com.solproject.animalcrossing.qboardInter;

import java.util.List;

import com.solproject.animalcrossing.qboard.QBoardVo;
import com.solproject.animalcrossing.util.Paging;
import com.solproject.animalcrossing.util.SearchHelper;
import com.solproject.animalcrossing.util.SearchPaging;

public interface QBoardService {
	public int writeQBoard(QBoardVo vo);

	public int getQBoardCount();

	public List<QBoardVo> getQBoardPageList(Paging p);
	
	public int increaseViewCnt(int qbno);
	
	public QBoardVo getQBoard(int qbno);

	public int likeUp(int qbno);

	public int likeDown(int qbno);
	
	public int getLikeCnt(int qbno);
	
	public int deleteQBoard(int qbno);
	
	public int modifyQBoard(QBoardVo vo);

	public int increaseAcnt(int qbno);
	
	public int decreaseAcnt(int qbno);

	public int adoptionAnswer(QBoardVo vo);

	public Integer getAdoption(int qbno);
	
	public int cancleAnswer(int qbno);

	public int getSearchQBoardCount(SearchHelper sh);
	
	public List<QBoardVo> getSearchQBoardPageList(SearchPaging p);
}
