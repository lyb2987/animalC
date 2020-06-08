package com.solproject.animalcrossing.qlike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.qlikeInter.QlikeDao;
import com.solproject.animalcrossing.qlikeInter.QlikeService;

@Service
public class QlikeServiceImpl implements QlikeService{

	@Autowired
	QlikeDao qlikeDao;

	@Override
	public int checkAllike(QlikeVo vo) {
		return qlikeDao.checkAllike(vo);
	}

	@Override
	public int createLike(QlikeVo vo) {
		return qlikeDao.createLike(vo);
	}

	@Override
	public int deleteLike(QlikeVo vo) {
		return qlikeDao.deleteLike(vo);
	}
	
}
