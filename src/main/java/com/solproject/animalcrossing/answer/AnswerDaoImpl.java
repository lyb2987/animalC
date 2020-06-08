package com.solproject.animalcrossing.answer;

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
}
