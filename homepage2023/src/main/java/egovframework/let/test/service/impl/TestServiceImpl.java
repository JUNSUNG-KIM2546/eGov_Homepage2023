package egovframework.let.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.test.service.TestService;
import egovframework.let.test.service.TestVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("testService")	//어노테이션
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name = "testMapper")
	private TestMapper testMapper;
	
	@Resource(name = "egovTestIdGnrService")
	private EgovIdGnrService idgenService;
	
	//CRUD 등록하기
	public String insertTest(TestVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setTestId(id);
		testMapper.insertTest(vo);
		
		return id;
	}
	
	//CRUD 목록 가져오기
	public List<EgovMap> selectTestList (TestVO vo) throws Exception {
		return testMapper.selectTestList(vo);
	}
	//CRUD 목록 수
	public int selectTestListCnt (TestVO vo) throws Exception {
		return testMapper.selectTestListCnt(vo);
	}
	
	//CRUD 가져오기
	public TestVO selectTest (TestVO vo) throws Exception {
		return testMapper.selectTest(vo);
	}
	
	//CRUD 수정하기
	public void updateTest (TestVO vo) throws Exception {
		testMapper.updateTest(vo);
	}
	
	//CRUD 삭제하기
	public void deleteTest (TestVO vo) throws Exception {
		testMapper.deleteTest(vo);
	}

	

	
}
