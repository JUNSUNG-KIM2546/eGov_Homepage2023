package egovframework.let.member.service;

public interface MemberService {		
	
	// 회원ID찾기
	public MemberVO findId(MemberVO vo) throws Exception;

	// 회원PW찾기
	public MemberVO findPassword(MemberVO vo) throws Exception;
	
	// 회원PW업데이트
	void passwordUpdate(MemberVO vo) throws Exception;
}	
