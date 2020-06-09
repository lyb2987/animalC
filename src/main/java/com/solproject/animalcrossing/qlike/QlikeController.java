package com.solproject.animalcrossing.qlike;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solproject.animalcrossing.qboard.QBoardServiceImpl;
import com.solproject.animalcrossing.util.LcLs;

@Controller
@RequestMapping("/qlike/**")
public class QlikeController {
	
	@Autowired
	QlikeServiceImpl qlikeService;
	
	@Autowired
	QBoardServiceImpl qBoardService;
	
	// 질문 추천
	@PostMapping("qblikeUp")
	@ResponseBody
	public LcLs qlikeUp(String qbno, String userId, HttpServletRequest request) {
		QlikeVo vo = null;
		LcLs cs = new LcLs(0, 0); 
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new QlikeVo(Integer.parseInt(qbno), userId);
		}
		else {
			vo = new QlikeVo(Integer.parseInt(qbno), userId);
		}
		
		System.out.println(vo.toString());
		
		// 중복여부 체크
		cs.setLikeStatus(qlikeService.checkAllike(vo)); ;
		
		if(cs.getLikeStatus() == 0) {
			cs.setLikeStatus(qBoardService.likeUp(Integer.parseInt(qbno)));
		}
		else {
			cs.setLikeStatus(-1);
			return cs;
		}
		
		if(cs.getLikeStatus() == 1) {
			cs.setLikeStatus(qlikeService.createLike(vo));
			cs.setLikeCnt(qBoardService.getLikeCnt(Integer.parseInt(qbno)));
		}
		
		return cs;
	}
	
	@PostMapping("qblikeDown")
	@ResponseBody
	public LcLs qlikeDown(String qbno, String userId, HttpServletRequest request) {
		QlikeVo vo = null;
		LcLs cs = new LcLs(0, 0);
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new QlikeVo(Integer.parseInt(qbno), userId);
			System.out.println("비회원 id : " + userId);
		}
		else {
			vo = new QlikeVo(Integer.parseInt(qbno), userId);
			System.out.println("회원 id :" + userId);
		}
		
		// 추천 취소
		cs.setLikeStatus(qBoardService.likeDown(Integer.parseInt(qbno))); 
		if(cs.getLikeStatus() == 1) {
			cs.setLikeStatus(qlikeService.deleteLike(vo));
			cs.setLikeCnt(qBoardService.getLikeCnt(Integer.parseInt(qbno)));
		}
		else {
			cs.setLikeStatus(-1);
		}
		return cs;
		
	}

}
