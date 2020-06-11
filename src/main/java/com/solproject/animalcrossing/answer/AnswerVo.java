package com.solproject.animalcrossing.answer;

public class AnswerVo {
	private int abno;
	private int qbno;
	private String acontent;
	private String awriter;
	private String aregdate;
	private int likecnt;
	
	AnswerVo(){
		
	}
	
	AnswerVo(int qbno, String awriter, String acontent){
		this.qbno = qbno;
		this.awriter = awriter;
		this.acontent = acontent;
	}
	
	AnswerVo(int abno, String acontent){
		this.abno = abno;
		this.acontent = acontent;
	}
	
	public int getAbno() {
		return abno;
	}
	public void setAbno(int abno) {
		this.abno = abno;
	}
	public int getQbno() {
		return qbno;
	}
	public void setQbno(int qbno) {
		this.qbno = qbno;
	}
	public String getAcontent() {
		return acontent;
	}
	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}
	public String getAwriter() {
		return awriter;
	}
	public void setAwriter(String awriter) {
		this.awriter = awriter;
	}
	public String getAregdate() {
		return aregdate;
	}
	public void setAregdate(String aregdate) {
		this.aregdate = aregdate;
	}
	public int getLikecnt() {
		return likecnt;
	}
	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}
	
}
