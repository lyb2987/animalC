package com.solproject.animalcrossing.board;

public class BlikeVo {
	private int bno;
	private String bliker;
	
	BlikeVo(){
		
	}
	
	BlikeVo(int bno, String bliker){
		this.bno = bno;
		this.bliker = bliker;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBliker() {
		return bliker;
	}
	public void setBliker(String bliker) {
		this.bliker = bliker;
	}
	
}
