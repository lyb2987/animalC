package com.solproject.animalcrossing.util;

public class LcLs {
	private int likeCnt;
	private int likeStatus;
	
	public LcLs(){
		
	}
	
	public LcLs(int likeCnt, int likeStatus){
		this.likeCnt = likeCnt;
		this.likeStatus = likeStatus;
	}
	
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(int likeStatus) {
		this.likeStatus = likeStatus;
	}
}
