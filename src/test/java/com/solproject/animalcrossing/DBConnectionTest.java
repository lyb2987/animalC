package com.solproject.animalcrossing;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.solproject.animalcrossing.member.MemberDaoImpl;
import com.solproject.animalcrossing.member.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class DBConnectionTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private MemberDaoImpl memberDaoImpl;
	
	@Test
	public void test() throws Exception{
		try {
			assertNotNull(dataSource.getConnection());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void modifyTest() throws Exception{
		
		MemberVo vo = new MemberVo();
		vo.setId("test2");
		vo.setPw("test2");
		vo.setName("test2");
		vo.setNickname("test2");
		vo.setSex("여");
		vo.setEmail("test@email.com");
		vo.setPhone("11111111111");
		vo.setAddr("TESTADDR");
		vo.setBirth("19951214");
		assertEquals(true, memberDaoImpl.modifyMember(vo));
	}

}
