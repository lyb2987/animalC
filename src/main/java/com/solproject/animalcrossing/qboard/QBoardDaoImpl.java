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
	@Override
	public int getLikeCnt(int qbno) {
		return sqlSession.selectOne(namespace + ".getLikeCnt", qbno);
	}
	@Override
	public int deleteQBoard(int qbno) {
		return sqlSession.delete(namespace + ".deleteQBoard", qbno);
	}
	@Override
	public int modifyQBoard(QBoardVo vo) {
		return sqlSession.update(namespace + ".modifyQBoard", vo);
	}
	@Override
	public int increaseAcnt(int qbno) {
		return sqlSession.update(namespace + ".increaseAcnt", qbno);
	}
	@Override
	public int decreaseAcnt(int qbno) {
		return sqlSession.update(namespace + ".decreaseAcnt", qbno);
	}
	@Override
	public int adoptionAnswer(QBoardVo vo) {
		return sqlSession.update(namespace + ".adoptionAnswer", vo);
	}
	@Override
	public Integer getAdoption(int qbno) {
		return sqlSession.selectOne(namespace + ".getAdoption", qbno);
	}
	@Override
	public int cancleAnswer(int qbno) {
		return sqlSession.update(namespace + ".cancleAnswer", qbno);
	}
	
}
