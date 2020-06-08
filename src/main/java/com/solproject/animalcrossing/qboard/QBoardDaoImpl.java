package com.solproject.animalcrossing.qboard;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.qboardInter.QBoardDao;

@Repository
public class QBoardDaoImpl implements QBoardDao{

	@Autowired
	SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.QBoard_Mapper";
	@Override
	public int writeQBoard(QBoardVo vo) {
		return sqlSession.insert(namespace + ".writeQBoard", vo);
	}
	@Override
	public int getQBoardCount() {
		return sqlSession.selectOne(namespace + ".getQBoardCount");
	}
	@Override
	public List<QBoardVo> getQBoardPageList(Paging p) {
		return sqlSession.selectList(namespace + ".getQBoardPageList", p);
	}
	@Override
	public int increaseViewCnt(int qbno) {
		return sqlSession.update(namespace + ".increaseViewCnt", qbno);
	}
	@Override
	public QBoardVo getBoard(int qbno) {
		return sqlSession.selectOne(namespace + ".getBoard", qbno);
	}
	@Override
	public int likeUp(int qbno) {
		return sqlSession.update(namespace + ".likeUp", qbno);
	}
	@Override
	public int likeDown(int qbno) {
		return sqlSession.update(namespace + ".likeDown", qbno);
	}
	
}
