package com.solproject.animalcrossing.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.boardInter.BlikeDao;
import com.solproject.animalcrossing.boardInter.BlikeService;

@Service
public class BlikeServiceImpl implements BlikeService{
	@Autowired
	private BlikeDao blikeDao;

	@Override
	public int likeUp(BlikeVo vo) {
		return blikeDao.likeUp(vo);
	}

	@Override
	public int getLike(int bno) {
		return blikeDao.getLike(bno);
	}

	@Override
	public int checkAllike(BlikeVo vo) {
		return blikeDao.checkAllike(vo);
	}

	@Override
	public int likeDown(BlikeVo vo) {
		return blikeDao.likeDown(vo);
	}
	
	
}
