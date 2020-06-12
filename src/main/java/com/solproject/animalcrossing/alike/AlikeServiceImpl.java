package com.solproject.animalcrossing.alike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.alikeInter.AlikeDao;
import com.solproject.animalcrossing.alikeInter.AlikeService;

@Service
public class AlikeServiceImpl implements AlikeService{

	@Autowired
	AlikeDao alikeDao;

	@Override
	public int checkAllike(AlikeVo vo) {
		return alikeDao.checkAllike(vo);
	}

	@Override
	public int createLike(AlikeVo vo) {
		return alikeDao.createLike(vo);
	}

	@Override
	public int deleteLike(AlikeVo vo) {
		return alikeDao.deleteLike(vo);
	}
	
}
