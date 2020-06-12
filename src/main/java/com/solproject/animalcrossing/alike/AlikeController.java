package com.solproject.animalcrossing.alike;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solproject.animalcrossing.answer.AnswerServiceImpl;
import com.solproject.animalcrossing.util.LcLs;

@Controller
@RequestMapping("/alike/**")
public class AlikeController {
	
	@Autowired
	AlikeServiceImpl alikeService;
	
	@Autowired
	AnswerServiceImpl answerService;
	
	// 질문 추천
	@PostMapping("alikeUp")
	@ResponseBody
	public LcLs qlikeUp(String abno, String userId, HttpServletRequest request) {
		AlikeVo vo = null;
		LcLs cs = new LcLs(0, 0); 
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new AlikeVo(Integer.parseInt(abno), userId);
		}
		else {
			vo = new AlikeVo(Integer.parseInt(abno), userId);
		}
		
		System.out.println(vo.toString());
		
		// 중복여부 체크
		cs.setLikeStatus(alikeService.checkAllike(vo)); ;
		
		if(cs.getLikeStatus() == 0) {
			cs.setLikeStatus(answerService.likeUp(Integer.parseInt(abno)));
		}
		else {
			cs.setLikeStatus(-1);
			return cs;
		}
		
		if(cs.getLikeStatus() == 1) {
			cs.setLikeStatus(alikeService.createLike(vo));
			cs.setLikeCnt(answerService.getLikeCnt(Integer.parseInt(abno)));
		}
		
		return cs;
	}
	
	// 추천취소
	@PostMapping("alikeDown")
	@ResponseBody
	public LcLs qlikeDown(String abno, String userId, HttpServletRequest request) {
		AlikeVo vo = null;
		LcLs cs = new LcLs(0, 0);
		
		// 로그인 여부 체크
		if(userId.equals("")) {
			userId = request.getRemoteAddr();
			vo = new AlikeVo(Integer.parseInt(abno), userId);
		}
		else {
			vo = new AlikeVo(Integer.parseInt(abno), userId);
		}
		
		// 추천 취소
		cs.setLikeStatus(answerService.likeDown(Integer.parseInt(abno))); 
		if(cs.getLikeStatus() == 1) {
			cs.setLikeStatus(alikeService.deleteLike(vo));
			cs.setLikeCnt(answerService.getLikeCnt(Integer.parseInt(abno)));
		}
		else {
			cs.setLikeStatus(-1);
		}
		return cs;
		
	}

}
