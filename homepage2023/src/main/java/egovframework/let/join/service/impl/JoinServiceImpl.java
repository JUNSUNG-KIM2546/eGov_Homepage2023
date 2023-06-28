package egovframework.let.join.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.let.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

@Service("joinService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.)
public class JoinServiceImpl extends EgovAbstractServiceImpl implements JoinService {
	
	@Resource(name = "joinMapper")
	private JoinMapper joinMapper;
	
	@Resource(name = "joinIdGnrService")
	private EgovIdGnrService idgenService;
	
	// ID 중복 체크
	public int duplicateCheck(JoinVO vo) throws Exception {
		return joinMapper.duplicateCheck(vo);
	}
		
	// 회원가입
	public String insertJoin(JoinVO vo) throws Exception {
		String esntlId = idgenService.getNextStringId();
		vo.setEsntlId(esntlId);
		
		//입력한 비밀번호를 암호화 한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(enpassword);
		
		//이메일 import EgovStringUtill = egovframework.let.utl.fcc.service.EgovStringUtil
		if(EgovStringUtil.isEmpty(vo.getEmailId()) && EgovStringUtil.isEmpty(vo.getEmailDomain())) {
			vo.setEmailAdres(vo.getEmailId() + "@" + vo.getEmailDomain());
		}
		
		joinMapper.insertJoin(vo);
		return esntlId;
	}
}
