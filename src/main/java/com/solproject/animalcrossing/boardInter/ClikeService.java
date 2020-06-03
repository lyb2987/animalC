package com.solproject.animalcrossing.boardInter;

import com.solproject.animalcrossing.board.ClikeVo;

public interface ClikeService {
	public int likeUp(ClikeVo vo);

	public int getLike(int cno);
	
	public int checkAllike(ClikeVo vo);

	public int likeDown(ClikeVo vo);
}
