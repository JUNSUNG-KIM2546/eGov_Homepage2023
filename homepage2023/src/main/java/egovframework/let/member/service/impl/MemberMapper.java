package egovframework.let.member.service.impl;

import egovframework.let.member.service.MemberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("memberMapper")
public interface MemberMapper {
	
	// 회원ID찾기
	MemberVO findId(MemberVO vo) throws Exception;
	
	// 회원PW찾기
	MemberVO findPassword(MemberVO vo) throws Exception;
	
	// 회원PW업데이트
	void passwordUpdate(MemberVO vo) throws Exception;
}
