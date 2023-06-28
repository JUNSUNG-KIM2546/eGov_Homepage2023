package egovframework.let.join.service;

public interface JoinService {		
	
	// ID 중복체크
	public int duplicateCheck(JoinVO vo) throws Exception;
	
	// 회원가입
	public String insertJoin(JoinVO vo) throws Exception;
}	
