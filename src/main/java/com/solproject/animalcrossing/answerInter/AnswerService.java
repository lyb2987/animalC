package com.solproject.animalcrossing.answerInter;

import java.util.List;

import com.solproject.animalcrossing.answer.AnswerVo;

public interface AnswerService {
	public int writeAnswer(AnswerVo vo);
	
	public List<AnswerVo> getAlist(int qbno);
}
