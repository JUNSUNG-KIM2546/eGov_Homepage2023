package egovframework.let.temp3.service.impl;

import java.util.List;

import egovframework.let.temp.service.TempVO;
import egovframework.let.temp3.service.Temp3VO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("temp3Mapper")
public interface Temp3Mapper {
	
	//임시데이터 가져오기
	Temp3VO selectTemp(Temp3VO vo) throws Exception;
	
	//임시데이터 목록 가져오기
	List<EgovMap> selectTempList(Temp3VO vo) throws Exception;
	
	//임시데이터 목록 수
	int selectTempListCnt(Temp3VO vo) throws Exception;
	
	//임시데이터 등록
	void insertTemp(Temp3VO vo) throws Exception;
			
	//임시데이터 수정
	void updateTemp(Temp3VO vo) throws Exception;
	
	//임시데이터 삭제
	void deleteTemp(Temp3VO vo) throws Exception;
		
}
