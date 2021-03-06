package com.solproject.animalcrossing.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.commentInter.CommentDao;
import com.solproject.animalcrossing.commentInter.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public int commentWrite(CommentVo vo) {
		return commentDao.commentWrite(vo);
	}

	@Override
	public List<CommentVo> getCommentList(int bno) {
		return commentDao.getCommentList(bno);
	}

	@Override
	public int modifyComment(CommentVo vo) {
		return commentDao.modifyComment(vo);
	}

	@Override
	public int deleteComment(int cno) {
		return commentDao.deleteComment(cno);
	}

	@Override
	public int getCommentCnt(int bno) {
		return commentDao.getCommentCnt(bno);
	}
	

}
