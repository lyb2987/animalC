package com.solproject.animalcrossing.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.solproject.animalcrossing.mailInter.MailService;

@Controller
@RequestMapping("/email/*")
public class MailController {

	@Autowired
	MailService mailService;
	
	// 이메일 전송
	@ResponseBody
	@PostMapping("sendEmail")
	public int sendEmail(String email) {

		// 5자리 랜덤 숫자 생성
		int rand=0;
		
		while(true) {
			rand = (int)Math.floor(Math.random()*100000+1);
			if(rand>9999) 
				break;
		}
		System.out.println("랜덤수 생성  : " + rand);
		
		// 메일 객체 세팅
		MailVo vo = new MailVo();
		vo.setSubject("AnimalC 인증 메일입니다~");
		vo.setSenderMail("lyb2619@gmail.com");
		vo.setSenderName("AnimalC");
		vo.setReceiveMail(email);

		vo.setMessage("인증 번호는 " + Integer.toString(rand) + "입니다.");
		
		try {
			System.out.println("세팅 완료 후 메일 전송 시도");
			mailService.sendMail(vo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return rand;
	}
	
}
