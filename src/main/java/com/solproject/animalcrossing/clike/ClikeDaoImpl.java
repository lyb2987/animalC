package com.solproject.animalcrossing.clike;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.clikeInter.ClikeDao;

@Repository
public class ClikeDaoImpl implements ClikeDao{
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Clike_Mapper";
	
	@Override
	public int likeUp(ClikeVo vo) {
		return sqlSession.insert(namespace + ".likeup", vo);
	}
	@Override
	public int getLike(int cno) {
		return sqlSession.selectOne(namespace + ".getlike", cno);
	}
	@Override
	public int checkAllike(ClikeVo vo) {
		return sqlSession.selectOne(namespace + ".checkAllike", vo);
	}
	@Override
	public int likeDown(ClikeVo vo) {
		return sqlSession.delete(namespace + ".likeDown", vo);
	}
}
