package com.solproject.animalcrossing.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.answerInter.AnswerDao;
import com.solproject.animalcrossing.answerInter.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	AnswerDao answerDao;

	@Override
	public int writeAnswer(AnswerVo vo) {
		return answerDao.writeAnswer(vo);
	}
	
}
