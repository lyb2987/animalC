package com.solproject.animalcrossing.qlike;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.qlikeInter.QlikeDao;

@Repository
public class QlikeDaoImpl implements QlikeDao{

	@Autowired
	SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Qlike_Mapper";
	
}
