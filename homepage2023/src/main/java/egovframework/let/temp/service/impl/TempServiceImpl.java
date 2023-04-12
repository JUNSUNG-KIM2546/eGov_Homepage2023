package egovframework.let.temp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("tempService")	//어노테이션
public class TempServiceImpl extends EgovAbstractServiceImpl implements TempService {
	
	@Resource(name="tempMapper")
	private TempMapper tempMapper;
	
	public TempVO selectTemp(TempVO vo) throws Exception {
		return tempMapper.selectTemp(vo);
	}	
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTempList(TempVO vo) throws Exception {
		return tempMapper.selectTempList(vo);
	}
	
	//임시데이터 등록하기
	public String insertTemp(TempVO vo) throws Exception {
		tempMapper.insertTemp(vo);
		return null;
	}
	
	//임시데이터 수정하기
	public void updateTemp(TempVO vo) throws Exception {
		tempMapper.updateTemp(vo);
	}
	
	//임시데이터 삭제하기
	public void deleteTemp(TempVO vo) throws Exception {
		tempMapper.deleteTemp(vo);
	}
	
//	@Resource(name = "tempDAO")
//	private TempDAO tempDAO;
//	
//	public TempVO selectTemp(TempVO vo) throws Exception {
//		return tempDAO.selectTemp(vo);
//	}
	
	
}
