package egovframework.let.temp3.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface Temp3Service {

	//임시데이터 가져오기
	public Temp3VO selectTemp(Temp3VO vo) throws Exception;
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTempList(Temp3VO vo) throws Exception;
	
	//임시데이터 목록 수
	public int selectTempListCnt(Temp3VO vo) throws Exception;
	
	//임시데이터 등록하기
	public String insertTemp(Temp3VO vo) throws Exception;
	
	//임시데이터 수정하기
	public void updateTemp(Temp3VO vo) throws Exception;
	
	//임시데이터 삭제하기
	public void deleteTemp(Temp3VO vo) throws Exception;
	
}
