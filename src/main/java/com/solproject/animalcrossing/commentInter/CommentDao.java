package com.solproject.animalcrossing.commentInter;

import java.util.List;

import com.solproject.animalcrossing.comment.CommentVo;

public interface CommentDao {
	
	public int commentWrite(CommentVo vo);
	
	public List<CommentVo> getCommentList(int bno);
	
	public int modifyComment(CommentVo vo);
	
	public int deleteComment(int cno);
	
	public int getCommentCnt(int bno);
}
