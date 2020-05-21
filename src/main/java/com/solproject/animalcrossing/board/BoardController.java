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
	
	// 게시판 메인
	@RequestMapping("moveBoardMain")
	public ModelAndView moveBoardMainPage() {
		ModelAndView mav = new ModelAndView();

		int boardCtn = boardService.getBoardCount();
		Paging paging = new Paging(boardCtn, 1);

		mav.addObject("paging", paging);
		mav.setViewName("board/boardMain");
		
		return mav;
		
	}
	
	// 페이지에 맞는 버튼 가져오기
	@ResponseBody
	@PostMapping("getPageBtn")
	public Paging getPageBtn(int currentP) {
		
		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		
		return p;
	}
	
	
	// 페이지에 맞는 게시글 목록 가져오기
	@ResponseBody
	@PostMapping("getBoardPageList")
	public List<BoardVo> getBoardPageList(int currentP){
		
		System.out.println("현재 페이지 : " + currentP);
		System.out.println("");
		
		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		
		
		System.out.println("현재 페이지 : " +p.getCurrentPage());
		System.out.println("시작 페이지 : " +p.getStartPage());
		System.out.println("끝 페이지 : " +p.getEndPage());
		System.out.println("db 데이터 시작값 : " + p.getStartRnum());
		System.out.println("db 데이터 끝값 : " + p.getEndRnum());
		
		List<BoardVo> list = boardService.getBoardPageList(p);
		
		return list;
	}
	
	// 다음 버튼 클릭시 페이지 이동
	@ResponseBody
	@PostMapping("moveNextPage")
	public Paging moveNextPage(int currentP){
		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		p.nextPage();
		return p;
	}
	
	// 이전 버튼 클릭시 페이지 이동
	@ResponseBody
	@PostMapping("movePreviousPage")
	public Paging movePreviousPage(int currentP){
		int boardCtn = boardService.getBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		p.previousPage();
		return p;
	}
	
	// 글작성 페이지로 이동
	@RequestMapping("writeBoardPage")
	public ModelAndView boardWritePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/boardWrite");
		
		return mav;
	}
	
	// 게시글 페이지로 이동
	@PostMapping("viewBoard")
	public ModelAndView viewBoard(int bno){
		ModelAndView mav = new ModelAndView();
	
		BoardVo vo = boardService.getBoard(bno);


		mav.addObject("board", vo);
		mav.setViewName("board/boardView");
		
		return mav;
	}
	
	// 글 작성
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
	
	
	// db 인서트 필요할때 말고는 헤더에 봉인해둠 + 로그인하고 사용할 것
	@RequestMapping("writeboardloop")
	public ModelAndView writeBoardloop(BoardVo vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		BoardVo vo2 = new BoardVo();
		MemberVo member = (MemberVo)session.getAttribute("user");

		int result=0;
		
		try {
			for(int i=0; i<249; i++) {
				Thread.sleep(1000);
				vo2.setBtitle("title "+Integer.toString(i));
				vo2.setBcontent("content "+Integer.toString(i));
				vo2.setBkind("기타 ");
				vo2.setBwriter(member.getId());

				result = boardService.writeboard(vo2);
				
				if(result==0) {
					break;
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
