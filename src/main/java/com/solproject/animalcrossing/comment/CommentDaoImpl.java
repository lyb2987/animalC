package com.solproject.animalcrossing.comment;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.commentInter.CommentDao;

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
	public List<CommentVo> getCommentList(int bno) {
		return sqlSession.selectList(namespace+".getCommentList", bno);
	}

	@Override
	public int modifyComment(CommentVo vo) {
		return sqlSession.update(namespace + ".modifyComment", vo);
	}

	@Override
	public int deleteComment(int cno) {
		return sqlSession.delete(namespace + ".deleteComment", cno);
	}

	@Override
	public int getCommentCnt(int bno) {
		return sqlSession.selectOne(namespace + ".getCommentCnt", bno);
	}
}
