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
		this.endPage = pageCnt;
		
		
	}
	
	public void movePage(int currentPage){
		// 첫페이지와 끝페이지에 위치할 때 이전, 다음 페이지의 값이 0으로 설정되게 했으므로 ajax에서 이를 판단해야됨 
		// 페이지 이동 시 전페이지와 다음페이지, 가지고 올 데이터 범위 세팅
		if(currentPage == 1) {
			this.currentPage = currentPage;
			this.previousPage = 0;
			this.nextPage = currentPage+1;
			this.startRnum = 1;
			this.endRnum = this.perPageBoardCnt;
		}
		// db에서 마지막 페이지 데이터 가져오는 sql 만들어보고 생각해야될듯
		else if(currentPage == endPage){
			this.currentPage = currentPage;
			this.previousPage = currentPage-1;
			this.nextPage= 0;			
			this.startRnum = currentPage*this.perPageBoardCnt;
			this.endRnum = (currentPage+1)*this.perPageBoardCnt;
		}
		else {
			this.currentPage = currentPage;
			this.previousPage = currentPage-1;
			this.nextPage= currentPage+1;
			this.startRnum = currentPage*this.perPageBoardCnt;
			this.endRnum = (currentPage+1)*this.perPageBoardCnt;
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
