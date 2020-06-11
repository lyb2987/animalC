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

import com.solproject.animalcrossing.qboard.QBoardServiceImpl;
import com.solproject.animalcrossing.qboard.QBoardVo;

@Controller
@RequestMapping("/answer/**")
public class AnswerController {

	@Autowired
	AnswerServiceImpl answerService;
	
	@Autowired
	QBoardServiceImpl qBoardService;
	
	
	@PostMapping("answerWrite")
	@ResponseBody
	public int answerWrite(String qbno, String userId, String acontent){
		
		AnswerVo vo = new AnswerVo(Integer.parseInt(qbno), userId, acontent); 

		
		int writeResult = answerService.writeAnswer(vo);
		int incAcntResult = 0;
		
		if(writeResult == 1) {
			incAcntResult = qBoardService.increaseAcnt(Integer.parseInt(qbno));
			System.out.println("incAcntResult : " + incAcntResult);
		}
		
		return writeResult;
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
	
	
	@PostMapping("adoptionAnswer")
	@ResponseBody
	public int adoptionAnswer(String abno, String qbno) throws Exception{
		
		int qbnonum = Integer.parseInt(qbno);
		int abnonum = Integer.parseInt(abno);
		int result = 0;
		
		QBoardVo vo = new QBoardVo(qbnonum, abnonum);
		Integer adoption = qBoardService.getAdoption(qbnonum);
		
		// 1 : 채택완료,  2 : 채택취소 완료,  3 : 이미 채택했기 때문에 취소하고 다시시도하라고 안내
		if(adoption == null) {
			result = qBoardService.adoptionAnswer(vo);
		}
		else if(adoption == abnonum) {
			result = qBoardService.cancleAnswer(qbnonum);
			result = 2;
		}
		else if(adoption != abnonum) {
			result = 3;
		}
			
		
		return result;
	}
	
	@PostMapping("cancleAnswer")
	@ResponseBody
	public int cancleAnswer(String qbno) {
		int qbnonum = Integer.parseInt(qbno);
				
		int result = qBoardService.cancleAnswer(qbnonum);
		
		return result;
	}
	
	
}
