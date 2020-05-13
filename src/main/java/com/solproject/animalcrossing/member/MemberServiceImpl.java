package com.solproject.animalcrossing.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.memberInter.MemberDao;
import com.solproject.animalcrossing.memberInter.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<MemberVo> selectMemberList() throws Exception {
		return memberDao.selectMemberList();
	}

	// 로그인
	@Override
	public MemberVo login(MemberVo vo) {
		return memberDao.login(vo);
	}

	@Override
	public MemberVo viewMember(MemberVo vo) {
		return vo;
	}

	@Override
	public boolean joinMember(MemberVo vo) {
		boolean result = memberDao.joinMember(vo);
		return result;
	}

	@Override
	public int checkId(String id) {
			int result = memberDao.checkId(id);
		return result;
	}

	@Override
	public boolean modifyMember(MemberVo vo) {
		
		boolean result = memberDao.modifyMember(vo);
		return result;
	}

	@Override
	public boolean deleteMember(MemberVo vo) {
			boolean result = memberDao.deleteMember(vo);
		return result;
	}

}
