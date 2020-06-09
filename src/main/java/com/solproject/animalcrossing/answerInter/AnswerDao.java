package com.solproject.animalcrossing.answerInter;

import java.util.List;

import com.solproject.animalcrossing.answer.AnswerVo;

public interface AnswerDao {
	public int writeAnswer(AnswerVo vo);
	
	public List<AnswerVo> getAlist(int qbno);
}
