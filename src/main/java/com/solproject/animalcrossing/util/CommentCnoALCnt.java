package com.solproject.animalcrossing.util;

public class CommentCnoALCnt {
	private int cno;
	private int clikeCnt;
	
	public CommentCnoALCnt(){
		
	}
	public CommentCnoALCnt(int cno, int clikeCnt){
		this.cno = cno;
		this.clikeCnt = clikeCnt;
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getClikeCnt() {
		return clikeCnt;
	}
	public void setClikeCnt(int clikeCnt) {
		this.clikeCnt = clikeCnt;
	}
	
	public String toString() {
		return "cno : " + Integer.toString(this.cno) + " clikeCnt : " + Integer.toString(this.clikeCnt);
	}
}
