package com.solproject.animalcrossing.answer;

import java.util.List;

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

	@Override
	public List<AnswerVo> getAlist(int qbno) {
		return answerDao.getAlist(qbno);
	}
	
	
}
