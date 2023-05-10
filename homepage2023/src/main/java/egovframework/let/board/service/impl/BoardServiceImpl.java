package egovframework.let.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("boardService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.)
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {
	
	@Resource(name = "boardMapper")
	private BoardMapper boardMapper;
	
	@Resource(name = "egovBoardIdGnrService")
	private EgovIdGnrService idgenService;
	
	//게시물 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception {
		return boardMapper.selectBoardList(vo);
	}
	
	//게시물 목록 수
	public int selectBoardListCnt(BoardVO vo) throws Exception {
		return boardMapper.selectBoardListCnt(vo);
	}
	
}
