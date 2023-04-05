package egovframework.let.temp.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.let.temp.service.TempVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("tempDAO")
public class TempDAO extends EgovAbstractDAO {
	
	public TempVO selectTemp(TempVO vo) throws Exception {
		return (TempVO)select("TempDAO.selectTemp", vo);	// DAO 고유 값
	}

}
