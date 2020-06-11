package com.solproject.animalcrossing.answer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.answerInter.AnswerDao;

@Repository
public class AnswerDaoImpl implements AnswerDao{

	@Autowired
	SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Answer_Mapper";
	
	@Override
	public int writeAnswer(AnswerVo vo) {
		return sqlSession.insert(namespace + ".writeAnswer", vo);
	}

	@Override
	public List<AnswerVo> getAlist(int qbno) {
		return sqlSession.selectList(namespace + ".getAlist", qbno);
	}

	@Override
	public String getAnswerContent(int abno) {
		return sqlSession.selectOne(namespace + ".getAnswerContent", abno);
	}

	@Override
	public int modifyAnswer(AnswerVo vo) {
		return sqlSession.update(namespace + ".modifyAnswer", vo);
	}
	
	@Override
	public int deleteAnswer(int abno) {
		return sqlSession.delete(namespace + ".deleteAnswer", abno);
	}
}
