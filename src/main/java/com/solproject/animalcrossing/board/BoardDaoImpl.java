package com.solproject.animalcrossing.board;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.boardInter.BoardDao;
import com.solproject.animalcrossing.board.Paging;

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

	@Override
	public List<BoardVo> getBoardPageList(Paging paging) {
		return sqlSession.selectList(namespace + ".getBoardPageList", paging);
	}

	@Override
	public BoardVo getBoard(int bno) {
		return sqlSession.selectOne(namespace + ".getBoard", bno);
	}

	@Override
	public int increaseViewCnt(int bno) {
		return sqlSession.update(namespace + ".increaseViewCnt", bno);
	}

	@Override
	public int modifyBoard(BoardVo vo) {
		
		System.out.println(vo.getBtitle());
		System.out.println(vo.getBkind());
		System.out.println(vo.getBcontent());
		System.out.println(vo.getBno());
		return sqlSession.update(namespace + ".modifyBoard", vo);
	}

	@Override
	public int deleteBoard(int bno) {
		return sqlSession.delete(namespace + ".deleteBoard", bno);
	}

	

}
