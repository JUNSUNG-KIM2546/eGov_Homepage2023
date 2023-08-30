package egovframework.let.test.service.impl;

import java.util.List;

import egovframework.let.test.service.TestVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("testMapper")
public interface TestMapper {

	void insertTest(TestVO vo) throws Exception;

	List<EgovMap> selectTestList(TestVO vo) throws Exception;

	int selectTestListCnt(TestVO vo) throws Exception;

	TestVO selectTest(TestVO vo) throws Exception;

	void updateTest(TestVO vo) throws Exception;

	void deleteTest(TestVO vo) throws Exception;
	
	
	
}
