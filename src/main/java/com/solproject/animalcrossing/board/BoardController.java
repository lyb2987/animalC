package com.solproject.animalcrossing.board;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.solproject.animalcrossing.member.MemberVo;

@Controller
@RequestMapping("/board/**")
public class BoardController {

	@Autowired
	private BoardServiceImpl boardService;
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private BlikeServiceImpl blikeService;
	
	@Autowired
	private ClikeServiceImpl clikeService;
	
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
		
		/*
		System.out.println("현재 페이지 : " +p.getCurrentPage());
		System.out.println("시작 페이지 : " +p.getStartPage());
		System.out.println("끝 페이지 : " +p.getEndPage());
		System.out.println("db 데이터 시작값 : " + p.getStartRnum());
		System.out.println("db 데이터 끝값 : " + p.getEndRnum());
		*/
		
		List<BoardVo> list = boardService.getBoardPageList(p);
		
		return list;
	}
	
	// 게시글 목록 댓글, 추천수 가져오기 getLikeAndCommentCnt
	@ResponseBody
	@PostMapping("getLikeAndCommentCnt")
	public List<BoardLAndCcnt> getLikeAndCommentCnt(String bnoString){

		List<BoardLAndCcnt> list = new ArrayList<BoardLAndCcnt>();
		
		String likeCString = "";
		String commentCString = "";
		
		String bnol[] =  null;
		String likeCnt[] = null;
		String commentCnt[] = null;
		
		bnol = bnoString.split(" ");
		
		for (int i = 0; i < bnol.length; i++) {
			likeCString += Integer.toString(blikeService.getLike(Integer.parseInt(bnol[i]))) + " ";
			commentCString += Integer.toString(commentService.getCommentCnt(Integer.parseInt(bnol[i])))+ " ";
		}
		
		likeCnt = likeCString.split(" ");
		commentCnt = commentCString.split(" ");
		
		for(int i = 0; i < bnol.length; i++) {
			BoardLAndCcnt lacCnt = new BoardLAndCcnt(Integer.parseInt(bnol[i]), Integer.parseInt(likeCnt[i]), Integer.parseInt(commentCnt[i]));
			list.add(lacCnt);
			//System.out.println("bno : " + list.get(i).getBno() + " likeCnt : " + list.get(i).getLikeCnt() + " commentCnt : " + list.get(i).getCommentCnt());
		}
		
		
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
	public ModelAndView boardWritePage(HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("user") == null) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/moveBoardMain");
			mav.addObject("msg", "게시글 작성을 위해 로그인 해주세요!");
		}
		else {
			mav.setViewName("board/boardWrite");	
		}
		
		return mav;
	}
	
	
	// 섬머노트 테스트 페이지 moveSummerNoteWrite
	@RequestMapping("moveSummerNoteWrite")
	public ModelAndView moveSummerNoteWrite() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/boardWriteSummerNote");
		
		return mav;
	}
	
	
	// 이미지 업로드 fileInsert
	@ResponseBody
	@PostMapping("fileInsert")
	public String fileInsert(MultipartFile files, HttpServletRequest request) throws Exception{

		// 저장경로 명시
		//C:\Users\soldesk\Desktop\filelocation
		String fileRoot = "C:\\Users\\soldesk\\Desktop\\filelocation\\";
		
		String originalFileName = files.getOriginalFilename();	// 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자
		
		//저장될 파일이름 설정
		String saveFileName = UUID.randomUUID() + extension;
		
		// 경로와 파일이름을 담은 파일 생성
		File targetFile = new File(fileRoot + saveFileName);

		// 파일저장
		files.transferTo(targetFile);
		
		System.out.println("인서트 완료");
		
		//return saveFileName;
		return saveFileName;
	}
	
	// 게시글 추천수 가져오기
	@ResponseBody
	@PostMapping("getBlike")
	public int getBlike(String bno) {
		int likeCnt = blikeService.getLike(Integer.parseInt(bno));
		System.out.println("추천 수 : " + likeCnt);
		return likeCnt;
	}
	
	// 댓글 추천수 가져오기 getClike
	@ResponseBody
	@PostMapping("getClike")
	public List<CommentCnoALCnt> getClike(String clikeString) {
		List<CommentCnoALCnt> list = new ArrayList<CommentCnoALCnt>();
		
		
		int likeCnt=0;
		if(clikeString.equals(""))
			return list;
		
		String cnoli[] = clikeString.split(" ");
		
		
		for (int i = 0; i < cnoli.length; i++) {
			likeCnt = clikeService.getLike(Integer.parseInt(cnoli[i]));
			// 여기서 객체 생성안해주고 밖에서 해둔거 가져다 쓰면 마지막 데이터로 중복 도배됨
			CommentCnoALCnt vo = new CommentCnoALCnt(Integer.parseInt(cnoli[i]), likeCnt);
			list.add(vo);
		}
		
		
		return list;
	}
	
	
	// 게시글 추천
	@ResponseBody
	@PostMapping("blikeUp")
	public int blikeUp(String bno, String userId, HttpServletRequest request) {
		BlikeVo vo = null;
		int likeStatus = 0;
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new BlikeVo(Integer.parseInt(bno), userId);
			System.out.println("비회원 id : " + userId);
		}
		else {
			vo = new BlikeVo(Integer.parseInt(bno), userId);
			System.out.println("회원 id :" + userId);
		}
		
		// 중복여부 체크
		int check = blikeService.checkAllike(vo);
		if(check == 0) {
			likeStatus = blikeService.likeUp(vo);
		}
		else {
			likeStatus = -1;
		}
		
		return likeStatus;
	}
	
	// 추천 취소 blikeDown
	@ResponseBody
	@PostMapping("blikeDown")
	public int blikeDown(String bno, String userId, HttpServletRequest request) {
		BlikeVo vo = null;
		int likeStatus = 0;
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new BlikeVo(Integer.parseInt(bno), userId);
			System.out.println("비회원 id : " + userId);
		}
		else {
			vo = new BlikeVo(Integer.parseInt(bno), userId);
			System.out.println("회원 id :" + userId);
		}
		
		// 추천 취소
		likeStatus = blikeService.likeDown(vo);
		
		return likeStatus;
	}
	
	
	// 댓글 추천
	@ResponseBody
	@PostMapping("clikeUp")
	public int clikeUp(String cno, String userId, HttpServletRequest request) {
		ClikeVo vo = null;
		int likeStatus = 0;
		
		//System.out.println("cno : " + cno);
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new ClikeVo(Integer.parseInt(cno), userId);
			//System.out.println("비회원 id : " + userId);
		}
		else {
			vo = new ClikeVo(Integer.parseInt(cno), userId);
			//System.out.println("회원 id :" + userId);
		}
		
		// 중복여부 체크
		int check = clikeService.checkAllike(vo);
		if(check == 0) {
			likeStatus = clikeService.likeUp(vo);
		}
		else {
			likeStatus = -1;
		}
		
		return likeStatus;
	}
	
	// 댓글 추천 취소
	@ResponseBody
	@PostMapping("clikeDown")
	public int clikeDown(String cno, String userId, HttpServletRequest request) {
		ClikeVo vo = null;
		int likeStatus = 0;
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new ClikeVo(Integer.parseInt(cno), userId);
			System.out.println("비회원 id : " + userId);
		}
		else {
			vo = new ClikeVo(Integer.parseInt(cno), userId);
			System.out.println("회원 id :" + userId);
		}
		
		// 추천 취소
		likeStatus = clikeService.likeDown(vo);
		
		return likeStatus;
	} 
	 
	 
	
	// 게시글 페이지로 이동
	@RequestMapping("viewBoard")
	public ModelAndView viewBoard(String bno){
		ModelAndView mav = new ModelAndView();
		

		int incCnt = boardService.increaseViewCnt(Integer.parseInt(bno));
		if(incCnt==1) {
		}
		BoardVo vo = boardService.getBoard(Integer.parseInt(bno));

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
	
	// 수정페이지로 이동
	@RequestMapping("moveModifyBoard")
	public  ModelAndView moveModifyBoard(String bno){
		ModelAndView mav = new ModelAndView();
		
		
		BoardVo vo = boardService.getBoard(Integer.parseInt(bno));

		mav.addObject("board", vo);
		mav.setViewName("board/boardModify");
		
		return mav;
	}
	
	// 글 삭제 deleteBoard
	@RequestMapping("deleteBoard")
	public  ModelAndView deleteBoard(String bno) {
		ModelAndView mav = new ModelAndView();
		
		int result = boardService.deleteBoard(Integer.parseInt(bno));
		
		if(result==1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/moveBoardMain");
			mav.addObject("msg", "게시글이 삭제되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/viewBoard?bno=" + bno);
			mav.addObject("msg", "게시글 삭제 실패!");
		}
		return mav;
	}
	
	// 글 수정 modifyBoard
	@PostMapping("modifyBoard")
	public ModelAndView modifyBoard(BoardVo vo, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(vo.getBno());
		
		BoardVo vo2 = new BoardVo();
		MemberVo member = (MemberVo)session.getAttribute("user");
		
		vo2.setBno(vo.getBno());
		vo2.setBtitle(vo.getBtitle());
		vo2.setBcontent(vo.getBcontent());
		vo2.setBkind(vo.getBkind());
		vo2.setBwriter(member.getId());
	
		int result = boardService.modifyBoard(vo2);

		//http://localhost:8080/animalcrossing/board/viewBoard?bno=402
		if(result==1) {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/viewBoard?bno="+Integer.toString(vo.getBno()));
			mav.addObject("msg", "게시글이 수정되었습니다.");
			
		}
		else {
			mav.setViewName("common/msg");
			mav.addObject("path", "/animalcrossing/board/moveBoardMain");
			mav.addObject("msg", "게시글 수정 실패!");
		}
		return mav;
	}
	
	// 댓글작성 commentWrite
	@ResponseBody
	@PostMapping("commentWrite")
	public int commentWrite(String bno, String ccontent, HttpSession session, HttpServletRequest request){
		
		CommentVo commentVo = new CommentVo();
		MemberVo memberVo = (MemberVo)session.getAttribute("user");
		
		// 왜 bno가 null인지 검사하는지 까먹음 살펴봐야됨ㅋㅋ
		if(bno==null) {
			commentVo.setCwriter(memberVo.getId());
		}else {
			String ipv4 =  request.getRemoteAddr();
			commentVo.setCwriter("비회원 : " + ipv4);
			//commentVo.setCwriter("비회원"); 댓글 테이블의 cwriter가 무결성 제약조건때문에 ip 사용 안되길래 제약조건 수정함
		}
		
		commentVo.setBno(Integer.parseInt(bno));
		commentVo.setCcontent(ccontent);
		
		
		int result = commentService.commentWrite(commentVo); 
		
		return result;
	}
	
	@ResponseBody
	@PostMapping("getCommentList")
	public List<CommentVo> getCommentList(String bno){
		
		List<CommentVo> clist = commentService.getCommentList(Integer.parseInt(bno));
		
		return clist;
	}
	
	@ResponseBody
	@PostMapping("deleteComment")
	public int deleteComment(String cno) {
		
		int result = commentService.deleteComment(Integer.parseInt(cno));
		
		return result;
	}
	
	// 댓글수정
	@ResponseBody
	@PostMapping("modifyComment")
	public int modifyComment(String cno, String mcomment){
		
		CommentVo vo = new CommentVo();
		
		vo.setCno(Integer.parseInt(cno));
		vo.setCcontent(mcomment);
		
		int result = commentService.modifyComment(vo);
		
		return result;
	}
	/*
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
	*/
}
