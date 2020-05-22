package com.solproject.animalcrossing.boardInter;

import java.util.List;

import com.solproject.animalcrossing.board.CommentVo;

public interface CommentDao {
	
	public int commentWrite(CommentVo vo);
	
	public List<CommentVo> getCommentList(int cno);
	
}
