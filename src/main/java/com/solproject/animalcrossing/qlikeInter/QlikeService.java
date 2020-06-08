package com.solproject.animalcrossing.qlikeInter;

import com.solproject.animalcrossing.qlike.QlikeVo;

public interface QlikeService {

	public int checkAllike(QlikeVo vo);
	
	public int createLike(QlikeVo vo);
	
	public int deleteLike(QlikeVo vo);
}
