package com.solproject.animalcrossing.boardInter;

import java.util.List;

import com.solproject.animalcrossing.board.BoardVo;

public interface BoardDao {
	public int writeBoard(BoardVo vo);
	
	public List<BoardVo> getBoardList();
	
	public List<String> getBoardDateList();

	public List<BoardVo> getBoardFirstList();
	
	public int getBoardCount();
}
