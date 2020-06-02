package com.solproject.animalcrossing.boardInter;

import java.util.List;

import com.solproject.animalcrossing.board.CommentVo;

public interface CommentService {

	public int commentWrite(CommentVo vo);

	public List<CommentVo> getCommentList(int bno);

	public int modifyComment(CommentVo vo);

	public int deleteComment(int cno);
	
	public int getCommentCnt(int bno);
}
