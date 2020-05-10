package com.solproject.animalcrossing.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.solproject.animalcrossing.memberInter.MemberService;

@Controller
@RequestMapping("/member/**")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	

	// 로그인 "완성" postMapping 사용으로 간편하게 메소드 명시없이 사용이 가능
	@PostMapping("loginRequest1.do")
	public ModelAndView loginRequest1(MemberVo vo, HttpSession session) {

		MemberVo vo2 = memberService.login(vo);
		
		ModelAndView mav = new ModelAndView();
		System.out.println("sddbbdfb");
		if(vo2 != null) {
			session.setAttribute("user", vo2);
			mav.setViewName("home");
		}
		else {

			mav.setViewName("home");
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
			System.out.println("둘이 일치함");
		}
		
		if(result) {
			System.out.println("삭제 성공");
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
	
	// ID 규칙 위배 또는 중복 체크 -> 페이지 리다이렉트방법 몰라서 잠깐 스톱
	@RequestMapping("checkId.do")
	public ModelAndView checkId(Model model, MemberVo vo, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		int count=0;
		
		for(int i=0; i<vo.getId().length(); i++) {
			if(Character.isAlphabetic(vo.getId().charAt(i)) || Character.isDigit(vo.getId().charAt(i)))
				count += 1;
			else {
				count = -1;
			}
		}
		if(vo.getId() == null || count==-1) {
			mav.addObject("msg", "아이디 형식이 올바르지 않습니다!");
		}
		
		boolean result = memberService.checkId(vo);

		
		if(result == true) {
			mav.addObject("msg", "중복된 아이디가 있습니다.");
			session.setAttribute("joinpossible", "no");
		}
		else {
			mav.addObject("msg", "사용 가능한 아이디 입니다.");
			session.setAttribute("joinpossible", "ok");
		}
		return mav;
	}
	
	
	@RequestMapping("logoutRequest.do")
	public ModelAndView logoutRequest(HttpSession session) {
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/msg");
		mav.addObject("path", "/animalcrossing");
		mav.addObject("msg", "logout Success");
		
		return mav;
	}
	
}

