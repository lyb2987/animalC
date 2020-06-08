package com.solproject.animalcrossing.qlike;

public class QlikeVo {
	private int qbno;
	private String qbliker;
	
	QlikeVo(){
		
	}
	
	QlikeVo(int qbno, String qbliker){
		this.qbno = qbno;
		this.qbliker = qbliker;
	}
	
	public int getQbno() {
		return qbno;
	}
	public void setQbno(int qbno) {
		this.qbno = qbno;
	}
	public String getQbliker() {
		return qbliker;
	}
	public void setQbliker(String qbliker) {
		this.qbliker = qbliker;
	}
	public String toString() {
		String returns = "qbno : " + Integer.toString(this.qbno) + "  qbliker : " + this.qbliker;
		return returns;
	}
	
}
