package egovframework.let.join.web;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import net.sf.json.JSONObject;


@Controller
public class JoinController {
	@Resource(name = "joinService")
	private JoinService joinService;
	
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	// 약관동의
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
	
	// ID 중복 체크	// JSON 비동기 통신: 화면 깜빡임 없이 최소한의 리소스로 화면 구성(ajax)	// void : 리턴값(반환값)이 없다
	@RequestMapping(value = "/join/duplicateCheck.do")
	public void duplicateCheck(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String successYn = "Y";
		String message = "성공";
		
		JSONObject jo = new JSONObject();
		response.setContentType("application/json; charset=utf-8");
		
		int duplicateCnt = joinService.duplicateCheck(vo);
		if(duplicateCnt > 0) {
			successYn = "N";
			message = egovMessageSource.getMessage("fail.duplicate.member"); //이미 사용중인 ID 입니다.
		}
		
		jo.put("successYn", successYn);
		jo.put("message", message);
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println(jo.toString());
		printwriter.flush();
		printwriter.close();
	}
	
	// 회원가입
	@RequestMapping(value = "/join/insertMember.do")
	public String insertMember(@ModelAttribute("searchVO") JoinVO vo, HttpServletRequest request, ModelMap model) throws Exception {
		//1.매크로를 막기 위해 JSP에서 1차로 확인하고 컨트롤러에서 2차 확인한다 	2.중복을 피하기 위해서 한번더 한다
		if(joinService.duplicateCheck(vo) > 0) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.duplicate.member")); //이미 사용중인 ID 입니다.
			return "forward:/join/memberType.do";
		}
		else {
			joinService.insertJoin(vo);
			model.addAttribute("message", egovMessageSource.getMessage("fail.request.msg")); //회원신청이 정상적으로 완료되었습니다. \n로그인 후 이용해 주세요.
		}
		
		return "join/MemberComplete";
	}
}
