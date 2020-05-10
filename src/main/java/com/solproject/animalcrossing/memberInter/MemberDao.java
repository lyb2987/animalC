package com.solproject.animalcrossing.memberInter;

import java.util.List;

import com.solproject.animalcrossing.member.MemberVo;

public interface MemberDao {

	public List<MemberVo> selectMemberList() throws Exception;

	public MemberVo login(MemberVo vo);
	
	public MemberVo viewMember(MemberVo vo);
	
	public boolean joinMember(MemberVo vo);
	
	public boolean checkId(MemberVo vo);
	
	public boolean modifyMember(MemberVo vo);
	
	public boolean deleteMember(MemberVo vo);
}
