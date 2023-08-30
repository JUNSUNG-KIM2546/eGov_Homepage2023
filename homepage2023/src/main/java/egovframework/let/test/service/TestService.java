package egovframework.let.test.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface TestService {

	List<EgovMap> selectTestList(TestVO testVO) throws Exception;

	int selectTestListCnt(TestVO testVO) throws Exception;

	TestVO selectTest(TestVO testVO) throws Exception;

	String insertTest(TestVO testVO) throws Exception;

	void updateTest(TestVO testVO) throws Exception;

	void deleteTest(TestVO testVO) throws Exception;

	
}
