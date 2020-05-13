package com.solproject.animalcrossing.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solproject.animalcrossing.memberInter.MemberService;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	// 로그인 페이지 이동 "완성"
	@RequestMapping("loginPage.do")
	public ModelAndView moveLoginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");
		return mav;
	}
	

	// 로그인 "완성" postMapping 사용으로 간편하게 메소드 명시없이 사용이 가능
	@PostMapping("loginRequest1.do")
	public ModelAndView loginRequest1(MemberVo vo, HttpSession session) {

		MemberVo vo2 = memberService.login(vo);
		
		ModelAndView mav = new ModelAndView();
		
		if(vo2 != null) {
			session.setAttribute("user", vo2);
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing");
			mav.addObject("msg", vo.getId() +"님 환영합니다.");
		}
		else {
			mav.setViewName("home");
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing");
			mav.addObject("msg", "로그인 실패! 다시 시도해 주세요!");
		}
		return mav;
	}
	
	// 회원가입페이지 이동 "완성"
	@RequestMapping("joinPage.do")
	public ModelAndView moveJoinPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/join");
		return mav;
	}
	
	
	//id 유효성 및 중복 검사.
	@ResponseBody
	@PostMapping("checkId")
	public int checkId(String id) {
		int checkcnt = 0;
		int dupleId = 0;
		
		// id의 길이 규칙 검사 위배되면 0반환
		if(id.length()<5 || id.length()>15)
			return checkcnt;
		else {
			// id의 길이만큼 돌며 숫자나 알파벳이 맞는지 확인 위배되면 0, 위배되지 않으면 1
			for(int i=0; i<=id.length()-1; i++) {
				if(Character.isAlphabetic(id.charAt(i)) || Character.isDigit(id.charAt(i)))
					checkcnt = 1;
				else {
					checkcnt = 0;
					break;
				}
			}
		}
		// id 규칙 검사 위배시 0반환, 알맞은 규칙을 가졌을 경우 중복검사 실시
		if(checkcnt == 0) 
			return checkcnt;
		else {
			dupleId = memberService.checkId(id);
			// 아이디 중복여부를 count로 검사하였으니 1이랑 같거나 크면 중복, 0이면 사용가능
			if(dupleId >= 1) {
				checkcnt = 0;
			}
			else if(dupleId == 0) {
				checkcnt = 1;
			}
		}
		return checkcnt;
	}
	
	
	// 회원가입 "현재로서는 완성"
	@PostMapping("joinMember.do")
	public ModelAndView joinMember(MemberVo vo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		boolean result = memberService.joinMember(vo);
		
		if(result == true) {
			session.setAttribute("user", vo);
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing");
			mav.addObject("msg", vo.getId() +"님 환영합니다.");
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing");
			mav.addObject("msg", "회원가입 실패!");
		}

		return mav;
	}
	
	
	
	// 마이페이지 이동 "완성"
	@RequestMapping("myPage.do")
	public ModelAndView moveMyPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/myPage");
		return mav;
	}
	
	// 회원정보 수정 페이지 이동 "완성"
	@RequestMapping("ModifyMemberPage.do")
	public ModelAndView moveModifyMemberPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/modifyMemberPage");
		return mav;
	}
	
	// 회원탈퇴 페이지 이동 deleteMemberpage.do
	@RequestMapping("deleteMemberPage")
	public void moveDeleteMemberPage() {
	}
	
	// 회원탈퇴 deleteMember.do
	@PostMapping("deleteMember.do")
	public ModelAndView moveDeleteMemberPage(HttpSession session, MemberVo vo) {
		ModelAndView mav = new ModelAndView();
		boolean result=false;
		
		MemberVo vo2 = (MemberVo)session.getAttribute("user");
		
		if((vo2.getId().equals(vo.getId())) && (vo2.getPw().equals(vo.getPw())))
		{
			result = memberService.deleteMember(vo);
		}
		
		if(result) {
			session.invalidate();
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing");
			mav.addObject("msg", "정상적으로 탈퇴 되었습니다.");
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "redirect:./");
			mav.addObject("msg", "탈퇴에 실패하였습니다 다시 시도해주세요!");
		}
		
		return mav;
	}
	
	// 회원정보 수정
	@PostMapping("ModifyMember.do")
	public ModelAndView ModifyMember(MemberVo vo, HttpSession session) {
		
		boolean result = memberService.modifyMember(vo);
		
		ModelAndView mav = new ModelAndView();
		
		if(result) {
			session.invalidate();
			mav.addObject("msg", "수정에 성공했습니다! 다시 로그인해주세요!");
			mav.addObject("path", "/animalcrossing");
			mav.setViewName("common/msg");
		}
		else {
			mav.addObject("msg", "수정에 실패했습니다! 다시 시도해주세요!");
			mav.addObject("path", "ModifyMemberPage.do");
			mav.setViewName("common/msg");
		}
		
		return mav;
	
	}
	
	
	
	@RequestMapping("logoutRequest.do")
	public ModelAndView logoutRequest(HttpSession session) {
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/msg");
		mav.addObject("path", "/animalcrossing");
		mav.addObject("msg", "로그아웃 되셨습니다.");
		
		return mav;
	}
	
}

