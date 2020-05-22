package com.solproject.animalcrossing.board;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.boardInter.CommentDao;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Comment_Mapper";

	@Override
	public int commentWrite(CommentVo vo) {
		return sqlSession.insert(namespace+".commentWrite", vo);
	}

	@Override
	public List<CommentVo> getCommentList(int cno) {
		return sqlSession.selectList(namespace+".getCommentList", cno);
	}
}
