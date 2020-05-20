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
	
	@ResponseBody
	@PostMapping("getPageBtn")
	public Paging getPageBtn(int currentP) {
		
		Paging p = new Paging(currentP);
		
		return p;
	}
	
	@ResponseBody
	@PostMapping("getBoardPageList")
	public List<BoardVo> getBoardPageList(int currenP){
		
		
		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn);
		
		p.movePage(currenP);
		
		
		List<BoardVo> list = boardService.getBoardPageList(p);
		
		return list;
	}
	
	//페이징 테스트중2 다음버튼 클릭시 다음 페이지 보여주기
	@ResponseBody
	@PostMapping("moveNextPage")
	public Paging moveNextPage(int currentP, int startP, int endP){
		
		
		System.out.println("현재 페이지 : " + currentP);
		System.out.println("시작 페이지 : " + startP);
		System.out.println("끝 페이지 : " + endP);
		

		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn);
		p.setCurrentPage(currentP);
		p.setStartPage(startP);
		p.setEndPage(endP);
		
		p.nextPage();
		/*
		List<BoardVo> list = boardService.getBoardPageList(p);
		*/
		System.out.println("현재 페이지 : " + p.getCurrentPage());
		System.out.println("시작 페이지 : " + p.getStartPage());
		System.out.println("끝 페이지 : " + p.getEndPage());
		
		
		return p;
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
