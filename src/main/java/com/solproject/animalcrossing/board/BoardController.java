package com.solproject.animalcrossing.board;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		List<BoardVo> boardList = boardService.getBoardList();
		
		System.out.println(boardList.size());
		
		mav.addObject("list", boardList);
		mav.setViewName("board/boardMain");
		
		
		return mav;
	}
	
	
	// 페이징 테스트 중
	@RequestMapping("moveBoardMain2")
	public ModelAndView moveBoardMainPage2() {
		ModelAndView mav = new ModelAndView();
		
		int boardCtn = boardService.getBoardCount();
		
		Paging paging = new Paging(boardCtn);
		
		List<BoardVo> boardlist = boardService.getBoardFirstList();

		mav.addObject("list", boardlist);
		mav.addObject("paging", paging);
		mav.setViewName("board/boardMain");
		
		
		return mav;
		
	}
	
	//페이징 테스트중2 다음버튼 클릭시 다음 페이지 보여주기
	@ResponseBody
	@PostMapping("moveNextPage")
	public String moveNextPage(String currentP, String startP, String endP){
		
		System.out.println("들어옴");
		System.out.println("현재 페이지  : "+ currentP);
		

		System.out.println(currentP);
		System.out.println(startP);
		System.out.println(endP);
		/*
		int cp = Integer.parseInt(currentP);
		int sp = Integer.parseInt(startP);
		int ep = Integer.parseInt(endP);
		
		System.out.println("이동 후 페이지  : "+ (cp+10));
		System.out.println("이동 후 시작 페이지  : "+ (sp+10));
		System.out.println("이동 후 끝 페이지  : "+ (ep+10));
		*/
		
		
		return "";
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
