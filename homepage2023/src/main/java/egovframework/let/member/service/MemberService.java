package egovframework.let.member.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface MemberService {		
	
	// 회원ID찾기
	public MemberVO findId(MemberVO vo) throws Exception;

	// 회원PW찾기
	public MemberVO findPassword(MemberVO vo) throws Exception;
	
	// 회원PW업데이트
	void passwordUpdate(MemberVO vo) throws Exception;
	
	// 회원목록
	public List<EgovMap> selectMberList (MemberVO vo) throws Exception;
	
	//회원목록 수
	public int selectMberListCnt (MemberVO vo) throws Exception;
	
	//회원 상세(관리자가 회원 수정)
	EgovMap selectMber(MemberVO vo) throws Exception;
}	
