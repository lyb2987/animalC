package com.solproject.animalcrossing.clikeInter;

import com.solproject.animalcrossing.clike.ClikeVo;

public interface ClikeService {
	public int likeUp(ClikeVo vo);

	public int getLike(int cno);
	
	public int checkAllike(ClikeVo vo);

	public int likeDown(ClikeVo vo);
}
