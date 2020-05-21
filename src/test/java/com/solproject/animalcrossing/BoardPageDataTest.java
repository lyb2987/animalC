package com.solproject.animalcrossing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.solproject.animalcrossing.board.BoardVo;
import com.solproject.animalcrossing.boardInter.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardPageDataTest {
	
	@Autowired
	private BoardService boardService;
	
	
	@Test
	public void test() throws Exception{
		/*
		BoardVo vo2 = new BoardVo();
		int result = 0;

		try {
			for(int i=5; i<150; i++) {
				vo2.setBtitle("제목" +i);
				vo2.setBcontent("내용" + i);
				vo2.setBkind("기타");
				vo2.setBwriter("bintest12");
				result = boardService.writeboard(vo2);
				if(result ==1) {
					System.out.println(i + "번째 글 쓰기 성공");
				}
				else {
					System.out.println(i + "번째 글 쓰기 실패");
					break;
				}
			}
			assertEquals(1, result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
	}
	
	
}
