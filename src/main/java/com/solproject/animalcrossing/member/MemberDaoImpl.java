package com.solproject.animalcrossing.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solproject.animalcrossing.memberInter.MemberDao;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession sqlSession;
	private static final String namespace = "com.solproject.animalcrossing.mapper.Member_Mapper";
	
	// 회원 목록 가져오기
	@Override
	public List<MemberVo> selectMemberList() throws Exception {
		return sqlSession.selectList(namespace + ".selectML");
	}
	
	
	// 로그인
	@Override
	public MemberVo login(MemberVo vo) {
		return sqlSession.selectOne(namespace + ".login", vo);
	}

	// 멤버 조회
	@Override
	public MemberVo viewMember(MemberVo vo) {
		return sqlSession.selectOne(namespace + ".viewMember", vo);
	}

	// 회원가입
	@Override
	public boolean joinMember(MemberVo vo) {
		int intrst = sqlSession.insert(namespace + ".joinMember", vo);
		boolean result;
		
		if(intrst==1) 
			result = true;
		else
			result = false;
		
		return result;
	}
	
	// 중복확인을 위한 멤버 조회
	@Override
	public boolean checkId(MemberVo vo) {
			MemberVo vo2 = sqlSession.selectOne(namespace + ".checkId", vo);
		return (vo2.getId() == null) ? true : false;
	}


	@Override
	public boolean modifyMember(MemberVo vo) {
		int intresult = sqlSession.update(namespace+".modifyMember", vo);
		boolean result;
		
		if(intresult==1)
			result = true;
		else
			result = false;
		
		return result;
	}


	@Override
	public boolean deleteMember(MemberVo vo) {
		int intresult = sqlSession.delete(namespace + ".deleteMember", vo);
		boolean result;
			
		if(intresult==1)
			result = true;
		else
			result = false;
		
		return result;
	}

}
