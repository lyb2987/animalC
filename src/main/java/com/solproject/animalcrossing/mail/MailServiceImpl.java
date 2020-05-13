package com.solproject.animalcrossing.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.mailInter.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendMail(MailVo vo) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			
			// 받는사람 설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getReceiveMail()));
			// 보낸사람 설정
			msg.addFrom(new InternetAddress[] {new InternetAddress(vo.getSenderMail(), vo.getSenderName())});
			// 이메일 제목 및 인코딩 설정
			msg.setSubject(vo.getSubject(), "utf-8");
			// 이메일 내용 및 인코딩 설정
			msg.setText(vo.getMessage(), "utf-8");
			
			mailSender.send(msg);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
