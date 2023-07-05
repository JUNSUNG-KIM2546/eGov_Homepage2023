package egovframework.let.login.service;

import egovframework.com.cmm.LoginVO;

public interface LoginService {		
	
	// 일반 로그인 처리
	public LoginVO actionLogin(LoginVO vo) throws Exception;
}	
