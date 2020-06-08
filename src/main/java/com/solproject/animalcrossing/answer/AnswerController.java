package com.solproject.animalcrossing.answer;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/answer/**")
public class AnswerController {

	@Autowired
	AnswerServiceImpl answerService;
	
	
	@PostMapping("answerWrite")
	@ResponseBody
	public int answerWrite(String qbno, String userId, String acontent){
		
		AnswerVo vo = new AnswerVo(Integer.parseInt(qbno), userId, acontent); 

		System.out.println(acontent);
		
		int result = answerService.writeAnswer(vo);
		
		
		return result;
	}
	
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
		
		//return saveFileName;
		return saveFileName;
	}
}
