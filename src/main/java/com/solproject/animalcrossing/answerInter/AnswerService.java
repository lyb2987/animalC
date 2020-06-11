package com.solproject.animalcrossing.answerInter;

import java.util.List;

import com.solproject.animalcrossing.answer.AnswerVo;

public interface AnswerService {
	public int writeAnswer(AnswerVo vo);
	
	public List<AnswerVo> getAlist(int qbno);

	public String getAnswerContent(int abno);

	public int modifyAnswer(AnswerVo vo);

	public int deleteAnswer(int abno);
}
