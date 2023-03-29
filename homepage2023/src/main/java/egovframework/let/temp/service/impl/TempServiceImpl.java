package egovframework.let.temp.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("tempService")	//어노테이션
public class TempServiceImpl extends EgovAbstractServiceImpl implements TempService {

	@Resource(name = "tempDAO")
	private TempDAO tempDAO;
	
	public TempVO selectTemp(TempVO vo) throws Exception {
		return tempDAO.selectTemp(vo);
	}
	
}
