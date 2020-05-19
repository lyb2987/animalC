package com.solproject.animalcrossing.board;


import java.util.List;

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

	@Override
	public List<BoardVo> getBoardList() {
		return sqlSession.selectList(namespace + ".getBoardList");
	}

	@Override
	public List<String> getBoardDateList() {
		return sqlSession.selectList(namespace + ".getBoardDateList");
	}

	@Override
	public List<BoardVo> getBoardFirstList() {
		return sqlSession.selectList(namespace + ".getBoardFistList");
	}

	@Override
	public int getBoardCount() {
		return sqlSession.selectOne(namespace+ ".getBoardCount");
	}

	

}
