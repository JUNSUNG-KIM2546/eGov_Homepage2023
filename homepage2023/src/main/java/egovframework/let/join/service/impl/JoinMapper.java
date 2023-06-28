package egovframework.let.join.service.impl;

import egovframework.let.join.service.JoinVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("joinMapper")
public interface JoinMapper {
	
	// ID 중복 체크(int형으로 카운트로 비교)
	int duplicateCheck(JoinVO vo) throws Exception;
	
	// 회원가입
	void insertJoin(JoinVO vo) throws Exception;
}
