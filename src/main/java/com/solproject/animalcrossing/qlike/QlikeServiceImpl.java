package com.solproject.animalcrossing.qlike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solproject.animalcrossing.qlikeInter.QlikeDao;
import com.solproject.animalcrossing.qlikeInter.QlikeService;

@Service
public class QlikeServiceImpl implements QlikeService{

	@Autowired
	QlikeDao qlikeDao;
	
}
