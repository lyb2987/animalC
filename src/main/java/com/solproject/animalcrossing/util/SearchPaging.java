package com.solproject.animalcrossing.util;

public class SearchPaging {
	private String sb;		//Search Boundary	검색 범위 (제목, 내용, 제목+내용)
	private String kind;	//Search Kind 	 	검색 종류(게시글 종류에따라다름) 사실상 sb2
	private String st;		//Search Term	 	검색어
	private int boardCnt; 		// 게시글 갯수
	private int perPageBoardCnt;// 한 페이지 당 글 갯수
	private int pageCnt;		// 페이지 길이
	private int startPage;		// 맨 앞 페이지
	private int endPage;		// 맨 뒤 페이지
	private int currentPage;	// 현재 페이지
	private int startRnum;		// rnum(데이터베이스 페이징 번호) 시작값 
	private int endRnum;		// rnum 끝값
	
	public SearchPaging() {
		
	}
	
	public SearchPaging(String sb, String st, int boardCnt) {
		if(sb.equals("제목"))
			this.sb = "Title";
		else if(sb.equals("내용"))
			this.sb = "Content";
		else 
			this.sb = "TitleAndContent";
		
		this.st = st;
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
	
	public SearchPaging(String sb, String st, int boardCnt, int currentPage) {
		if(sb.equals("제목"))
			this.sb = "Title";
		else if(sb.equals("내용"))
			this.sb = "Content";
		else 
			this.sb = "TitleAndContent";
		
		this.st = st;
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
	//"searchB" : searchB, "bKind" : bKind, "searchT" : searchT, "searchBcount" : searchBcount, "searchCP" : searchCP
	public SearchPaging(String sb, String kind, String st, int boardCnt, int currentPage) {
		if(sb.equals("제목"))
			this.sb = "Title";
		else if(sb.equals("내용"))
			this.sb = "Content";
		else if(sb.equals("제목 + 내용"))
			this.sb = "TitleAndContent";
		else 
			this.sb = sb;
		
		if(kind.equals("전체"))
			this.kind = "All";
		else
			this.kind = kind;
		
		this.st = st;
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

	public SearchPaging(String sb, String kind, String st, int boardCnt) {
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
		
		this.kind = kind;
		this.st = st;
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
}
