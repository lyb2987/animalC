package com.solproject.animalcrossing.alikeInter;

import com.solproject.animalcrossing.alike.AlikeVo;

public interface AlikeDao {
	
	public int checkAllike(AlikeVo vo);
	
	public int createLike(AlikeVo vo);

	public int deleteLike(AlikeVo vo);
}
