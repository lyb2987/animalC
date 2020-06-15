package com.solproject.animalcrossing.clike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.clikeInter.ClikeDao;
import com.solproject.animalcrossing.clikeInter.ClikeService;

@Service
public class ClikeServiceImpl implements ClikeService{
	@Autowired
	private ClikeDao clikeDao;

	@Override
	public int likeUp(ClikeVo vo) {
		return clikeDao.likeUp(vo);
	}

	@Override
	public int getLike(int cno) {
		return clikeDao.getLike(cno);
	}

	@Override
	public int checkAllike(ClikeVo vo) {
		return clikeDao.checkAllike(vo);
	}

	@Override
	public int likeDown(ClikeVo vo) {
		return clikeDao.likeDown(vo);
	}
	
}
