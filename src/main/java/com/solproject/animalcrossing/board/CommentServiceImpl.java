package com.solproject.animalcrossing.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.boardInter.CommentDao;
import com.solproject.animalcrossing.boardInter.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public int commentWrite(CommentVo vo) {
		return commentDao.commentWrite(vo);
	}

	@Override
	public List<CommentVo> getCommentList(int cno) {
		return commentDao.getCommentList(cno);
	}
	

}
