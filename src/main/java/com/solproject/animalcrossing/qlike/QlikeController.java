package com.solproject.animalcrossing.qlike;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solproject.animalcrossing.qboard.QBoardServiceImpl;

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
	public int qlikeUp(String qbno, String userId, HttpServletRequest request) {
		QlikeVo vo = null;
		int likeStatus = 0;
		int createLike = 0;
		
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
		int check = qlikeService.checkAllike(vo);
		System.out.println("check : " + check);
		if(check == 0) {
			likeStatus = qBoardService.likeUp(Integer.parseInt(qbno));
		}
		else {
			check = -1;
			return check;
		}
		
		if(likeStatus == 1) {
			createLike = qlikeService.createLike(vo);
			System.out.println("createLike :" + createLike);
		}
		
		return createLike;
	}
	
	@PostMapping("qlikeDown")
	@ResponseBody
	public int qlikeDown(String qbno, String userId, HttpServletRequest request) {
		QlikeVo vo = null;
		int likeStatus = 0;
		int deleteLike = 0;
		
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
		likeStatus = qBoardService.likeDown(Integer.parseInt(qbno));
		if(likeStatus == 1) {
			deleteLike = qlikeService.deleteLike(vo);
			System.out.println("deleteLike :" + deleteLike);
		}
		else {
			likeStatus = -1;
		}
		return likeStatus;
		
	}

}
