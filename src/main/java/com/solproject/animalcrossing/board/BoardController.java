package com.solproject.animalcrossing.board;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.solproject.animalcrossing.member.MemberVo;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;
	
	@RequestMapping("moveBoardMain")
	public ModelAndView moveBoardMainPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardMain");
		
		return mav;
	}
	
	@RequestMapping("writeBoardPage")
	public ModelAndView boardWritePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardWrite");
		
		
		
		
		return mav;
	}
	
	@PostMapping("writeboard")
	public ModelAndView writeBoard(BoardVo vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		BoardVo vo2 = new BoardVo();
		MemberVo member = (MemberVo)session.getAttribute("user");

		vo2.setBtitle(vo.getBtitle());
		vo2.setBcontent(vo.getBcontent());
		vo2.setBkind(vo.getBkind());
		vo2.setBwriter(member.getId());
	
		int result = boardService.writeboard(vo2);

		if(result == 1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/moveBoardMain");
			mav.addObject("msg", "게시글이 작성되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/moveBoardMain");
			mav.addObject("msg", "게시글 작성 실패!");
		}
		
		
		return mav;
	}
	

	
}
