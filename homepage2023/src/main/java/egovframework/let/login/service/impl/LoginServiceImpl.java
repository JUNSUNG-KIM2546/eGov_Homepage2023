package egovframework.let.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.let.login.service.LoginService;
import egovframework.let.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("egovLoginService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.)
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService {
	
	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;
		
	// 일반 로그인을 처리한다.
	public LoginVO actionLogin(LoginVO vo) throws Exception {
		
		//입력한 비밀번호를 암호화 한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
		vo.setPassword(enpassword);
		
		//아이디와 암호화 비밀번호가 DB와 일치하는지 확인한다.
		LoginVO loginVO = loginMapper.actionLogin(vo);
		
		if (loginVO != null && !loginVO.getId().equals("") && !loginVO.getPassword().equals("")) {
			return loginVO;
		}
		else {
			loginVO = new LoginVO();
		}
		
		return loginVO;
	}
	
}
