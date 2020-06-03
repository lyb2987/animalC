package com.solproject.animalcrossing.boardInter;

import com.solproject.animalcrossing.board.BlikeVo;

public interface BlikeDao {
	public int likeUp(BlikeVo vo);

	public int getLike(int bno);
	
	public int checkAllike(BlikeVo vo);

	public int likeDown(BlikeVo vo);
}
