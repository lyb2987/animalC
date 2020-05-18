package com.solproject.animalcrossing.board;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.boardInter.BoardDao;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Board_Mapper";
	
	@Override
	public int writeBoard(BoardVo vo) {
			int result = sqlSession.insert(namespace + ".writeBoard", vo);
		return result;
	}

	

}
