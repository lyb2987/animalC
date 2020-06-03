package com.solproject.animalcrossing.board;

public class ClikeVo {
	private int cno;
	private String cliker;
	
	ClikeVo(){
		
	}
	
	ClikeVo(int cno, String cliker){
		this.cno = cno;
		this.cliker = cliker;
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCliker() {
		return cliker;
	}
	public void setCliker(String cliker) {
		this.cliker = cliker;
	}
	
	
}
