package egovframework.let.member.service.impl;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("loginMapper")
public interface MemberMapper {
	
	// 로그인 처리
	public LoginVO actionLogin(LoginVO vo) throws Exception;
}
