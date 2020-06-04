package com.solproject.animalcrossing.boardInter;


import java.util.List;

import com.solproject.animalcrossing.board.BoardVo;
import com.solproject.animalcrossing.board.Paging;

public interface BoardService {
	public int writeboard(BoardVo vo);
	
	public List<BoardVo> getBoardList();

	public List<String> getBoardDateList();

	public List<BoardVo> getBoardFirstList();

	public List<BoardVo> getBoardPageList(Paging paging);
	
	public int getBoardCount();

	public BoardVo getBoard(int bno);
	
	public int increaseViewCnt(int bno);

	public int modifyBoard(BoardVo vo);

	public int deleteBoard(int bno);
}
