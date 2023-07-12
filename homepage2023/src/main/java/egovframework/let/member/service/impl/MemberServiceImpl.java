package egovframework.let.member.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.member.service.MemberService;
import egovframework.let.member.service.MemberVO;
import egovframework.let.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("memberService")	//어노테이션  (서비스에 있는건 임플에 있어야 한다.) 절대 같은 이름 프로젝트를 작업하더라도 어노테이션은 같은면 안된다 *오타주의*
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService {
			
	@Resource(name="memberMapper")
	private MemberMapper memberMapper;
	
	// 회원ID찾기
	public MemberVO findId(MemberVO vo) throws Exception {
		return memberMapper.findId(vo);
	}
	
	// 회원PW찾기
	public MemberVO findPassword(MemberVO vo) throws Exception {
		return memberMapper.findPassword(vo);
	}

	// 회원PW업데이트
	public void passwordUpdate(MemberVO vo) throws Exception {
		// 입력한 비밀번호를 암호화 한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(enpassword);
		
		memberMapper.passwordUpdate(vo);
		
	}
	
}
