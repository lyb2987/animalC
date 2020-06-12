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

	@Override
	public String getAnswerContent(int abno) {
		return answerDao.getAnswerContent(abno);
	}

	@Override
	public int modifyAnswer(AnswerVo vo) {
		return answerDao.modifyAnswer(vo);
	}

	@Override
	public int deleteAnswer(int abno) {
		return answerDao.deleteAnswer(abno);
	}

	@Override
	public int likeUp(int abno) {
		return answerDao.likeUp(abno);
	}

	@Override
	public int likeDown(int abno) {
		return answerDao.likeDown(abno);
	}

	@Override
	public int getLikeCnt(int abno) {
		return answerDao.getLikeCnt(abno);
	}
	
	
}
