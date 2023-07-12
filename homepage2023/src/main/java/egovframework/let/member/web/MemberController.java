package egovframework.let.member.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.member.service.MemberService;
import egovframework.let.member.service.MemberVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;


@Controller
public class MemberController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	// 회원ID 찾기
	@RequestMapping(value = "/member/findId.do")
	public String findId(@ModelAttribute("searchVO") MemberVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		return "/member/FindId";
	}
	
	// 회원ID찾기완료
	@RequestMapping(value = "/member/findIdComplete.do")
	public String findIdComplete(@ModelAttribute("searchVO") MemberVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		MemberVO result = memberService.findId(vo);
		if (result == null || EgovStringUtil.isEmpty(result.getEmplyrId())) {
			model.addAttribute("message", "가입 된 회원정보가 없습니다.");
			return "forward:/member/findId.do";
		}
		model.addAttribute("result", result);
		
		return "/member/FindIdComplete";
	}
	
	// 회원비밀번호찾기
	@RequestMapping(value = "/member/findPassword.do")
	public String findPassword(@ModelAttribute("searchVO") MemberVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		return  "/member/FindPassword";
	}
	
	// 회원비밀번호수정 (세션으로 보안)
	@RequestMapping(value = "/member/findPasswordRegist.do")
	public String findPasswordRegist(@ModelAttribute("searchVO") MemberVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		MemberVO result = memberService.findPassword(vo);
		if (result == null || EgovStringUtil.isEmpty(result.getEmplyrId())) {
			model.addAttribute("message", "가입 된 회원정보가 없습니다.");
			return "forward:/member/findPassword.do";
		}
		model.addAttribute("result", result);
		
		return  "/member/FindPasswordRegist";
	}
	
	//회원비밀번호업데이트(재발급)
	@RequestMapping(value = "/member/findPasswordComplete.do")
	public String findPasswordComplete(@ModelAttribute("searchVO") MemberVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		memberService.passwordUpdate(vo);
		model.addAttribute("loginMessage", "비밀번호가 업데이트 되었습니다.");
		
		return "forward:/login/login.do";
	}
		
}
