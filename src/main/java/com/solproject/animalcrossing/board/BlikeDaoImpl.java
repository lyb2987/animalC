package com.solproject.animalcrossing.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.boardInter.BlikeDao;

@Repository
public class BlikeDaoImpl implements BlikeDao{
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Blike_Mapper";
	
	@Override
	public int likeUp(BlikeVo vo) {
		return sqlSession.insert(namespace + ".likeup", vo);
	}

	@Override
	public int getLike(int bno) {
		return sqlSession.selectOne(namespace + ".getlike", bno);
	}

	@Override
	public int checkAllike(BlikeVo vo) {
		return sqlSession.selectOne(namespace + ".checkAllike", vo);
	}

	@Override
	public int likeDown(BlikeVo vo) {
		return sqlSession.delete(namespace + ".likeDown", vo);
	}
	
}
