package com.solproject.animalcrossing.util;

public class SearchHelper {
	String sb;		//Search Boundary	검색 범위 (제목, 내용, 제목+내용)
	String kind;	//Search Kind 	 	검색 종류(게시글 종류에따라다름) 사실상 sb2
	String st;		//Search Term	 	검색어
	
	public SearchHelper() {
		
	}
	
	public SearchHelper(String sb, String st) {
		if(sb.equals("제목"))
			this.sb = "Title";
		else if(sb.equals("내용"))
			this.sb = "Content";
		else 
			this.sb = "TitleAndContent";
		this.st = st;
	}
	
	public SearchHelper(String sb, String kind, String st) {
		if(sb.equals("제목"))
			this.sb = "Title";
		else if(sb.equals("내용"))
			this.sb = "Content";
		else 
			this.sb = "TitleAndContent";
		
		if(kind.equals("전체"))
			this.kind = "All";
		else
			this.kind = kind;
			
		this.st = st;
	}
	
	public String getSb() {
		return sb;
	}
	public void setSb(String sb) {
		this.sb = sb;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	
	public String toString() {
		String returns = "sb : " + this.sb + ", kind : " + kind + ", st : " + st;
		return returns;
	}
}
