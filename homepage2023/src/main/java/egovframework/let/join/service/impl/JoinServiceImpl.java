package egovframework.let.join.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("joinService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.)
public class JoinServiceImpl extends EgovAbstractServiceImpl implements JoinService {
	
	@Resource(name = "joinMapper")
	private JoinMapper joinMapper;
		
}
