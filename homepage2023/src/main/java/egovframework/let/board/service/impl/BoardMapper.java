package egovframework.let.board.service.impl;

import java.util.List;

import egovframework.let.board.service.BoardVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("boardMapper")
public interface BoardMapper {
	
	//게시물 목록 가져오기
	List<EgovMap> selectBoardList(BoardVO vo) throws Exception;
	
	//게시물 목록 수
	int selectBoardListCnt(BoardVO vo) throws Exception;
}
