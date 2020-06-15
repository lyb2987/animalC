package com.solproject.animalcrossing.util;

public class BoardLAndCcnt {
	private int bno;
	private int likeCnt;
	private int commentCnt;
	
	public BoardLAndCcnt() {
	}
	
	public BoardLAndCcnt(int bno, int likeCnt, int commentCnt) {
		this.bno = bno;
		this.likeCnt = likeCnt;
		this.commentCnt = commentCnt;
	}
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	
	
}
