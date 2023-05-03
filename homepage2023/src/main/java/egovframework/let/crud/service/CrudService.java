package egovframework.let.crud.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface CrudService {

	//CRUD 가져오기
	public CrudVO selectCrud (CrudVO vo) throws Exception;
		
	//CRUD 등록
	public String insertCrud (CrudVO vo) throws Exception;
			
	//CRUD 목록 가져오기
	public List<EgovMap> selectCrudList (CrudVO vo) throws Exception;
		
	//CRUD 목록 수
	public int selectCrudListCnt (CrudVO vo) throws Exception;
		
	//CRUD 수정하기
	public void updateCrud (CrudVO vo) throws Exception;
		
	//CRUD 삭제하기
	public void deleteCrud (CrudVO vo) throws Exception;
}
