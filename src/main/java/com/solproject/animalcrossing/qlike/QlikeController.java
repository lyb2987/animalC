package com.solproject.animalcrossing.qlike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qlike/**")
public class QlikeController {
	
	@Autowired
	QlikeServiceImpl qlikeService;
	

}
