package com.solproject.animalcrossing.qboard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.member.MemberVo;

@Controller
@RequestMapping("/qnaboard/**")
public class QBoardController {

	@Autowired
	QBoardServiceImpl qBoardService;
	
	// 질문 메인으로 이동
	@RequestMapping("moveQNAMain")
	public ModelAndView moveQNAMain() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("qnaboard/qnaBoardMain");
		
		return mav;
		
	}
	
	// 질문글 작성 페이지 이동
	@RequestMapping("moveQBWrite")
	public ModelAndView moveQBWrite() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("qnaboard/qnaBoardWrite");
		
		return mav;
		
	}
	
	
	// 질문글 작성
	@PostMapping("writeQBoard")
	public ModelAndView writeBoard(QBoardVo vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		QBoardVo vo2 = new QBoardVo();
		MemberVo member = (MemberVo)session.getAttribute("user");
		
		vo2.setQbtitle(vo.getQbtitle());
		vo2.setQbcontent(vo.getQbcontent());
		vo2.setQbwriter(member.getId());
		 
		int result = qBoardService.writeQBoard(vo2);

		if(result == 1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글이 작성되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글 작성 실패!");
		}
		return mav;
	}
	
	/*
	 
	
	// 페이지 메인
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
	 
	*/
	
}
