package com.solproject.animalcrossing.comment;

public class CommentVo {
	private int bno;
	private String cwriter;
	private String ccontent;
	private String cregdate;
	private int cno;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getCwriter() {
		return cwriter;
	}
	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getCregdate() {
		return cregdate;
	}
	public void setCregdate(String cregdate) {
		this.cregdate = cregdate;
	}
}
