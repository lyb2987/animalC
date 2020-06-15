package com.solproject.animalcrossing.clike;

public class ClikeVo {
	private int cno;
	private String cliker;
	
	public ClikeVo(){
		
	}
	
	public ClikeVo(int cno, String cliker){
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
