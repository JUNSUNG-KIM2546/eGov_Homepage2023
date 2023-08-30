package egovframework.let.member.service.impl;

import java.util.List;

import egovframework.let.member.service.MemberVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Mapper("memberMapper")
public interface MemberMapper {
	
	// 회원ID찾기
	MemberVO findId(MemberVO vo) throws Exception;
	
	// 회원PW찾기
	MemberVO findPassword(MemberVO vo) throws Exception;
	
	// 회원PW업데이트
	void passwordUpdate(MemberVO vo) throws Exception;
	
	// 회원목록
	List<EgovMap> selectMberList(MemberVO vo) throws Exception;

	// 회원목록 수
	int selectMberListCnt(MemberVO vo) throws Exception;
	
	// 회원 상세(관리자가 회원 수정)
	EgovMap selectMber(MemberVO vo) throws Exception;
}
