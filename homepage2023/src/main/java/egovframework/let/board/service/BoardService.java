package egovframework.let.board.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;


public interface BoardService {		
	//Board 게시물 목록 가져오기
	public List<EgovMap> selectBoardList (BoardVO vo) throws Exception;
		
	//Board 게시물 목록 수
	public int selectBoardListCnt (BoardVO vo) throws Exception;
	
	//Board 게시물 등록하기
	public String insertBoard (BoardVO vo) throws Exception;
	
	//Board 게시물 상세정보 가져오기
	public BoardVO selectBoard (BoardVO vo) throws Exception;
}
