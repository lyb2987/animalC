package com.solproject.animalcrossing.board;

public class Paging {
	private int boardCnt; 		// 게시글 갯수
	private int perPageBoardCnt;// 한 페이지 당 글 갯수
	private int pageCnt;		// 페이지 길이
	private int startPage;		// 맨 앞 페이지
	private int endPage;		// 맨 뒤 페이지
	private int currentPage;	// 현재 페이지
	private int nextPage;		// 다음페이지
	private int previousPage;	// 이전페이지
	private int startRnum;		// rnum(데이터베이스 페이징 번호) 시작값 
	private int endRnum;		// rnum 끝값
	
	/*
	 *  
	 * */
	// 기본 생성자가 없으면 ajax로 데이터를 넘겨줄때 에러가남 생성해주면 잘 됨
	Paging(){
		
	}
	
	Paging(int boardCnt){
		this.perPageBoardCnt = 10;
		this.boardCnt = boardCnt;
		if(boardCnt % perPageBoardCnt == 0)
			this.pageCnt = boardCnt / perPageBoardCnt;
		else
			this.pageCnt = (boardCnt / perPageBoardCnt) + 1;
		this.currentPage = 1;
		this.previousPage = 0;
		this.nextPage = 2;
		this.startRnum = 1;
		this.endRnum = 10;
		this.startPage = 1;
		this.endPage = 10;
		
		
	}
	
	Paging(int boardCnt, int currentPage){
		this.perPageBoardCnt = 10;
		this.boardCnt = boardCnt;
		if(boardCnt % perPageBoardCnt == 0)
			this.pageCnt = boardCnt / perPageBoardCnt;
		else
			this.pageCnt = (boardCnt / perPageBoardCnt) + 1;
		this.currentPage = currentPage;
		this.previousPage = this.currentPage-1;
		this.nextPage = this.currentPage+1;
		this.startRnum = this.currentPage*10+1;
		this.endRnum = this.startRnum+9;
		this.startPage = 1;
		this.endPage = 10;
	}
	
	public void movePage(int currentPage){
		//  
		// 페이지 이동 시 전페이지와 다음페이지, 가지고 올 데이터 범위 세팅
		if(currentPage == 1) {
			this.currentPage = currentPage;
			this.previousPage = 0;
			this.startRnum = 1;
			this.endRnum = this.perPageBoardCnt;
		}
		// db에서 마지막 페이지 데이터 가져오는 sql 만들어보고 생각해야될듯
		else if(currentPage == endPage){
			this.currentPage = currentPage;
			this.nextPage= 0;			
			this.startRnum = currentPage*10+1;
			this.endRnum = (currentPage+1)*10;
		}
		else {
			this.currentPage = currentPage;
			this.startRnum = currentPage*10+1;
			this.endRnum = (currentPage+1)*10;
		}
	}
	
	// 다음버튼 클릭 시 실행될 메소드
	public void nextPage() {
		int current = this.currentPage;
		if(current <= 10) {
			this.currentPage= 11;
			this.setSrnumErnum();
			this.startPage = 11;
			this.endPage = 20;
			if(this.endPage>this.pageCnt) 
				this.endPage = this.pageCnt;
		}
		else if(current > 10) {
			this.currentPage = (current/10)*10+1;
			this.setSrnumErnum();
			this.startPage = (current/10)*10+1;
			this.endPage = this.startPage+9;
			if(this.endPage>this.pageCnt) 
				this.endPage = this.pageCnt;
		}
	}
	
	// 페이지 이동에 따라 StartRnum과 EndRnum을 세팅
	public void setSrnumErnum() {
		if(this.currentPage == 1) {
			this.startRnum = this.currentPage;
			this.endPage = this.currentPage + 9;
		}
		else {
			this.startRnum = this.currentPage * 10 + 1;
			this.endPage = this.startRnum + 9;
		}
	}
	
	
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getPerPageBoardCnt() {
		return perPageBoardCnt;
	}
	public void setPerPageBoardCnt(int perPageBoardCnt) {
		this.perPageBoardCnt = perPageBoardCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartRnum() {
		return startRnum;
	}
	public void setStartRnum(int startRnum) {
		this.startRnum = startRnum;
	}
	public int getEndRnum() {
		return endRnum;
	}
	public void setEndRnum(int endRnum) {
		this.endRnum = endRnum;
	}

	public int getBoardCnt() {
		return boardCnt;
	}

	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	
	
}
