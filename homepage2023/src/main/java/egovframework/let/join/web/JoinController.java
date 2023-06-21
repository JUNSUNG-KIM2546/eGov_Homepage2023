package egovframework.let.join.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.join.service.JoinVO;


@Controller
public class JoinController {
	
	// 회원구분
	@RequestMapping(value = "/join/siteUseAgree.do")
	public String siteUseAgree(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
		
		return "join/SiteUseAgree";
	}
	
	// 회원구분
	@RequestMapping(value = "/join/memberType.do")
	public String memberType(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
			
		return "join/MemberType";
	}
	
	// 회원등록 폼(일반회원)
	@RequestMapping(value = "/join/memberRegist.do")
	public String memberRegist(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
			
		return "join/MemberRegist";
	}
}
