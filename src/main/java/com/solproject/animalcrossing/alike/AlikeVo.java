package com.solproject.animalcrossing.alike;

public class AlikeVo {
	private int abno;
	private String abliker;
	
	AlikeVo(){
		
	}
	
	AlikeVo(int abno, String abliker){
		this.abno = abno;
		this.abliker = abliker;
	}
	
	public int getAbno() {
		return abno;
	}
	public void setAbno(int abno) {
		this.abno = abno;
	}
	public String getAliker() {
		return abliker;
	}
	public void setAliker(String abliker) {
		this.abliker = abliker;
	}

}
