package com.solproject.animalcrossing.blikeInter;

import com.solproject.animalcrossing.blike.BlikeVo;

public interface BlikeService {
	public int likeUp(BlikeVo vo);
	
	public int getLike(int bno);

	public int checkAllike(BlikeVo vo);
	
	public int likeDown(BlikeVo vo);
}
