package com.solproject.animalcrossing.board;

public class Paging {
	private int boardCnt; 		// 게시글 갯수
	private int perPageBoardCnt;// 한 페이지 당 글 갯수
	private int pageCnt;		// 페이지 길이
	private int startPage;		// 맨 앞 페이지
	private int endPage;		// 맨 뒤 페이지
	private int currentPage;	// 현재 페이지
	private int startRnum;		// rnum(데이터베이스 페이징 번호) 시작값 
	private int endRnum;		// rnum 끝값
	
	/* 
	   
	   
	 */
	// 기본 생성자가 없으면 ajax로 데이터를 넘겨줄때 에러가남 생성해주면 잘 됨
	public Paging(){
		
	}
	
	public Paging(int boardCnt){
		this.perPageBoardCnt = 10;
		this.boardCnt = boardCnt;
		if(boardCnt % perPageBoardCnt == 0)
			this.pageCnt = boardCnt / perPageBoardCnt;
		else
			this.pageCnt = (boardCnt / perPageBoardCnt);
		
		this.currentPage = 1;
		this.setSrnumErnum();
		this.setSpageEpage();
	}
	
	public Paging(int boardCnt, int currentPage){
		this.perPageBoardCnt = 10;
		this.boardCnt = boardCnt;
		if(boardCnt % perPageBoardCnt == 0)
			this.pageCnt = boardCnt / perPageBoardCnt;
		else
			this.pageCnt = (boardCnt / perPageBoardCnt);
		
		this.currentPage = currentPage;
		this.setSrnumErnum();
		this.setSpageEpage();
		
	}
	
	public void movePage(int currentPage){
		this.currentPage = currentPage;
		this.setSrnumErnum();
	}
	
	// 다음버튼 클릭 시 실행될 메소드
	public void nextPage() {
		if(this.currentPage <= 10) {
			if(this.pageCnt <= 10) {
				
			}
			else {
				this.currentPage=11;
			}
		}
		else {
			if(this.currentPage % 10 == 0) {
				if(this.pageCnt < (this.currentPage/10) * 10 + 1) {
					
				}
				else {
					this.currentPage= (this.currentPage/10) * 10 + 1;
				}
			}
			else {
				if(this.pageCnt < (this.currentPage/10) * 10 + 1 + 10) {
				}
				else {
					this.currentPage= (this.currentPage/10) * 10 + 1 + 10;
				}
			}
		}
		this.setSpageEpage();
		this.setSrnumErnum();
	}
	
	public void previousPage() {
		if(this.currentPage <=10) {
		}
		else {
			if(this.currentPage%10==0) {
				this.currentPage = this.currentPage-10;
			}
			else {
				this.currentPage = (this.currentPage/10)*10;
			}
		}
		this.setSpageEpage();
		this.setSrnumErnum();
	}
	
	
	// StartRnum과 EndRnum을 세팅
	public void setSrnumErnum() {
		if(this.currentPage == 1) {
			this.startRnum = 1;
			this.endRnum = 10;
		}
		else {
			this.startRnum = this.currentPage * 10 + 1;
			this.endRnum = this.startRnum + 9;
		}
	}
	
	
	// 시작페이지와 끝페이지 세팅
	public void setSpageEpage() {
		if(this.pageCnt <= 10) {
			this.startPage = 1;
			this.endPage = this.pageCnt;
		}
		else {
			if(this.currentPage % 10 == 0) {
				if(this.currentPage == 10) {
					this.startPage = 1;
					this.endPage = 10;
				}
				else {
					this.startPage = this.currentPage - 9;
					this.endPage = this.currentPage;
				}
			}
			else {
				this.startPage = (this.currentPage / 10) * 10 + 1;
				if(this.startPage+9 > this.pageCnt) {
					this.endPage = this.pageCnt;
				}
				else {
					this.endPage = this.startPage + 9;
				}
			}
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
