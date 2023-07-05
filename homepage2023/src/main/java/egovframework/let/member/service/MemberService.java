package egovframework.let.member.service;

import egovframework.com.cmm.LoginVO;

public interface MemberService {		
	
	// 일반 로그인 처리
	public LoginVO actionLogin(LoginVO vo) throws Exception;
}	
