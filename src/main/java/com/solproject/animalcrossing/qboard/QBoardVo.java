package com.solproject.animalcrossing.qboard;

public class QBoardVo {
	private int qbno;
	private String qbtitle;
	private String qbcontent;
	private String qbwriter;
	private String qregdate;
	private int viewcnt;
	private int acount;				// 답변 갯수
	private int adoption;
	private int likecnt;
	
	public int getQbno() {
		return qbno;
	}
	public void setQbno(int qbno) {
		this.qbno = qbno;
	}
	public String getQbtitle() {
		return qbtitle;
	}
	public void setQbtitle(String qbtitle) {
		this.qbtitle = qbtitle;
	}
	public String getQbcontent() {
		return qbcontent;
	}
	public void setQbcontent(String qbcontent) {
		this.qbcontent = qbcontent;
	}
	public String getQbwriter() {
		return qbwriter;
	}
	public void setQbwriter(String qbwriter) {
		this.qbwriter = qbwriter;
	}
	public String getQregdate() {
		return qregdate;
	}
	public void setQregdate(String qregdate) {
		this.qregdate = qregdate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public int getAcount() {
		return acount;
	}
	public void setAcount(int acount) {
		this.acount = acount;
	}
	public int getAdoption() {
		return adoption;
	}
	public void setAdoption(int adoption) {
		this.adoption = adoption;
	}
	public int getLikecnt() {
		return likecnt;
	}
	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}
	
	
	
}
