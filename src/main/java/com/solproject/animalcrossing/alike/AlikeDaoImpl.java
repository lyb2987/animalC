package com.solproject.animalcrossing.alike;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.alikeInter.AlikeDao;

@Repository
public class AlikeDaoImpl implements AlikeDao{

	@Autowired
	SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Alike_Mapper";
	
	@Override
	public int checkAllike(AlikeVo vo) {
		return sqlSession.selectOne(namespace + ".checkAllike", vo);
	}
	@Override
	public int createLike(AlikeVo vo) {
		return sqlSession.insert(namespace + ".createLike", vo);
	}
	@Override
	public int deleteLike(AlikeVo vo) {
		return sqlSession.delete(namespace + ".deleteLike", vo);
	}
	
}
