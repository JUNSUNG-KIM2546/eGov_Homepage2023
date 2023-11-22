package egovframework.let.rsv.web;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.fabric.xmlrpc.base.Param;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.rsv.service.ReservationApplyService;
import egovframework.let.rsv.service.ReservationApplyVO;
import egovframework.let.rsv.service.ReservationService;
import egovframework.let.rsv.service.ReservationVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import net.sf.json.JSONObject;

@Controller
public class ReservationApplyController {
	@Resource(name = "reservationService")
	private ReservationService reservationService;
	
	@Resource(name = "reservationApplyService")
	private ReservationApplyService reservationApplyService;
	
	// 예약정보 목록 가져오기
	@RequestMapping(value = "/rsv/rsvCheck.json")
	public void rsvCheck(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{
		String successYn = "Y";
		String message = "성공";
		
		/* import net.sf.json.JSONObject; */
		JSONObject jo = new JSONObject();
		response.setContentType("text/javascript; charset=utf-8");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			successYn = "N";
			message = "로그인 후 사용가능합니다.";
		}
		searchVO.setUserId(user.getId());
		
		ReservationApplyVO result = reservationApplyService.rsvCheck(searchVO);
		if(!EgovStringUtil.isEmpty(result.getErrorCode())) {
			successYn = "N";
			message = result.getMessage();
		}
		
		jo.put("successYn", successYn);
		jo.put("message", message);
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println(jo.toString());
		printwriter.flush();
		printwriter.close();
		
	}
	
	// 예약자정보 등록 / 수정
	@RequestMapping(value = "/rsv/rsvApplyRegist.do")
	public String rsvApplyRegist(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		else {
			model.addAttribute("USER_INFO", user);
		}
		
		// 프로그램정보
		ReservationVO reservation = new ReservationVO();
		if(!EgovStringUtil.isEmpty(searchVO.getResveId())) {
			reservation = reservationService.selectReservation(searchVO);
		}
		model.addAttribute("reservation", reservation);
		
		// 예약정보
		ReservationApplyVO rssult = new ReservationApplyVO();
		if(!EgovStringUtil.isEmpty(searchVO.getResveId())) {
			//result = reservationApplyService.selectReservationApply(searchVO);
		}
		model.addAttribute("rssult", rssult);
		request.getSession().removeAttribute("sessionReservationApply");
		
		return "rsv/RsvApplyRegist";
	}
	
	// 예약자정보 등록하기
	@RequestMapping(value = "/rsv/rsvApplyInsert.do")
	public String rsvApplyInsert(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		// 이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionReservationApply") != null) {
			return "forward:/rsv/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		searchVO.setCreatIp(request.getRemoteAddr());
		
		ReservationApplyVO result = reservationApplyService.insertReservationApply(searchVO);
		if(!EgovStringUtil.isEmpty(result.getErrorCode())) {
			model.addAttribute("message", result.getMessage());
		}
		else {
			model.addAttribute("message", "신청완료되었습니다.");
		}
		
		// 이중 서브밋 방지
		request.getSession().setAttribute("sessionReservationApply", searchVO);
		return "forward:/rsv/selectList.do";
	}
	
	//예약자정보 목록 가져오기
	@RequestMapping(value = "/rsv/selectApplyList.do")
	public String selectApplyList(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		else {
			searchVO.setUserId(user.getId());
			model.addAttribute("USER_INFO", user);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getLastRecordIndex());
		
		List<EgovMap> resultList = reservationApplyService.selectReservationApplyList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = reservationApplyService.selectReservationApplyListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "rsv/RsvApplySelectList";
	}
	
	// 예약자정보 상세
	@RequestMapping(value = "/rsv/rsvApplySelect.do")
	public String rsvApplySelect(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		else {
			model.addAttribute("USER_INFO", user);
		}
		
		ReservationApplyVO result = reservationApplyService.selectReservationApply(searchVO);
		model.addAttribute("result", result);
		
		// 이중 서브밋 방지
		request.getSession().removeAttribute("sessionReservationApply");
		
		return "rsv/RsvApplySelect";
	}
	
	// 예약정보 수정하기
	@RequestMapping(value = "/rsv/rsvApplyUpdate.do")
	public String rsvApplyUpdate(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		// 이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionReservationApply") != null) {
			return "forward:/rsv/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationApplyService.updateReservationApply(searchVO);
		
		// 이중 서브밋 방지
		request.getSession().setAttribute("sessionReservationApply", searchVO);
		return "forward:/rsv/selectApplyList.do";
	}
		
	// 예약정보 삭제하기
	@RequestMapping(value = "/rsv/rsvApplyDelete.do")
	public String rsvApplyDelete(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception {
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("massage", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationApplyService.deleteReservationApply(searchVO);
		
		return "forward:/rsv/selectApplyList.do";
	}
}
