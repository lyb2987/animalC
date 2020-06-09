package com.solproject.animalcrossing.qboard;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.solproject.animalcrossing.answer.AnswerServiceImpl;
import com.solproject.animalcrossing.board.Paging;
import com.solproject.animalcrossing.member.MemberVo;

@Controller
@RequestMapping("/qnaboard/**")
public class QBoardController {

	@Autowired
	QBoardServiceImpl qBoardService;
	
	@Autowired
	AnswerServiceImpl answerService;
	
	// 질문 메인으로 이동
	@RequestMapping("moveQNAMain")
	public ModelAndView moveQNAMain() {
		ModelAndView mav = new ModelAndView();
		
		int qnaboardCtn = qBoardService.getQBoardCount();

		Paging paging = new Paging(qnaboardCtn, 1);

		mav.addObject("paging", paging);
		mav.setViewName("qnaboard/qnaBoardMain");
		
		return mav;
		
	}
	
	// 페이지에 맞는 게시글 목록 가져오기
	@ResponseBody
	@PostMapping("getQnABoardPageList")
	public List<QBoardVo> getBoardPageList(int currentP){
		
		int boardCtn = qBoardService.getQBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		
		List<QBoardVo> list = qBoardService.getQBoardPageList(p);
		
		
		return list;
	}

	// 페이지에 맞는 버튼 가져오기
	@ResponseBody
	@PostMapping("getPageBtn")
	public Paging getPageBtn(int currentP) {
		
		int boardCtn = qBoardService.getQBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		
		return p;
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
	

	// 다음 버튼 클릭시 페이지 이동
	@ResponseBody
	@PostMapping("moveNextPage")
	public Paging moveNextPage(int currentP){
		int boardCtn = qBoardService.getQBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		p.nextPage();
		return p;
	}
	
	
	// 이전 버튼 클릭시 페이지 이동
	@ResponseBody
	@PostMapping("movePreviousPage")
	public Paging movePreviousPage(int currentP){
		int boardCtn = qBoardService.getQBoardCount();
		Paging p = new Paging(boardCtn, currentP);
		p.previousPage();
		return p;
	}
	
	// 게시글 조회 viewQBoard
	@RequestMapping("viewQBoard")
	public ModelAndView viewQBoard(String qbno){
		ModelAndView mav = new ModelAndView();
		

		int incCnt = qBoardService.increaseViewCnt(Integer.parseInt(qbno));
		if(incCnt==1) {
			
		}
		QBoardVo vo = qBoardService.getQBoard(Integer.parseInt(qbno));

		vo.setAlist(answerService.getAlist(Integer.parseInt(qbno)));
		
		mav.addObject("qboard", vo);
		mav.setViewName("qnaboard/qnaBoardView");
		
		return mav;
	}
	
	// 게시글 수정페이지 이동
	@RequestMapping("moveModifyQBoard")
	public ModelAndView moveModifyQBoard(String qbno) {
		
		ModelAndView mav = new ModelAndView();
		
		QBoardVo vo = qBoardService.getQBoard(Integer.parseInt(qbno));
		
		mav.addObject("qboard", vo);
		mav.setViewName("qnaboard/qnaBoardModify");
		
		return mav;
	}
	
	// 게시글 수정 modifyQBoard 
	@RequestMapping("modifyQBoard")
	public ModelAndView modifyQBoard(String qbno, String qbtitle, String qbcontent) {
		
		ModelAndView mav = new ModelAndView();
		
		QBoardVo vo = new QBoardVo(Integer.parseInt(qbno), qbtitle, qbcontent);
		
		int result = qBoardService.modifyQBoard(vo);
		
		if(result == 1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글이 수정되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글 수정 실패!");
		}
		
		return mav;
	}
	
	
	// 질문 게시글 삭제
	@RequestMapping("deleteQBoard")
	public ModelAndView deleteQBoard(String qbno) {
		
		ModelAndView mav = new ModelAndView();
		
		int result = qBoardService.deleteQBoard(Integer.parseInt(qbno));
		
		if(result == 1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글이 삭제되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/qnaboard/moveQNAMain");
			mav.addObject("msg", "게시글 삭제 실패!");
		}
		
		return mav;
	}
	
	
	
		 /*
	// 질문 게시글 데이터 생성
	@RequestMapping("writeQboardloop")
	public ModelAndView writeBoardloop(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		QBoardVo vo2 = new QBoardVo();
		MemberVo member = (MemberVo)session.getAttribute("user");

		int result=0;
		
		try {
			for(int i=0; i<249; i++) {
				Thread.sleep(1000);
				vo2.setQbtitle("title "+Integer.toString(i));
				vo2.setQbcontent("content "+Integer.toString(i));
				vo2.setQbwriter(member.getId());

				result = qBoardService.writeQBoard(vo2);
				
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
		*/
	

}
