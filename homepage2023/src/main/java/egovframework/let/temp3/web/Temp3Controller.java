package egovframework.let.temp3.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.temp3.service.Temp3Service;
import egovframework.let.temp3.service.Temp3VO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class Temp3Controller {
	
	@Resource(name = "temp3Service")
	private Temp3Service temp3Service;
	
	//임시데이터 가져오기
	@RequestMapping(value = "/temp3/select.do")
	public String select(Temp3VO tempVO, HttpServletRequest request, ModelMap model) throws Exception {
		Temp3VO result = temp3Service.selectTemp(tempVO);
		model.addAttribute("result", result);
		return "temp3/TempSelect";
	}
	
	//임시데이터 목록 가져오기
	@RequestMapping(value = "/temp3/selectList.do")
	public String selectList(Temp3VO tempVO, HttpServletRequest request, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(tempVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(tempVO.getPageUnit());
		paginationInfo.setPageSize(tempVO.getPageSize());
		
		tempVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tempVO.setLastIndex(paginationInfo.getLastRecordIndex());
		tempVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = temp3Service.selectTempListCnt(tempVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		//List : java.util.List
		List<EgovMap> resultList = temp3Service.selectTempList(tempVO);
		model.addAttribute("resultList",resultList);
		return "temp3/TempSelectList";
	}
	
	//임시데이터 등록|수정		-등록과 수정 페이지는 같이 만들수 있다.
	@RequestMapping(value = "/temp3/tempRegist.do")
	public String tempRegist(Temp3VO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		Temp3VO result = new Temp3VO();
		//egovframework.let.utl.fcc.service.EgovStringUtil;
		if(!EgovStringUtil.isEmpty(tempVO.getTempId())) {	//pk값이 있으면 수정페이지로, 없으면 등록페이지로
			result = temp3Service.selectTemp(tempVO);
		}
		model.addAttribute("result", result);
		return "temp3/TempRegist";
	}
	
	//임시데이터 등록하기
	@RequestMapping(value = "/temp3/insert.do")
	public String insert(Temp3VO tempVO, HttpServletRequest request) throws Exception{
		temp3Service.insertTemp(tempVO);
		return "forward:/temp3/selectList.do";
	}
		
	//임시데이터 수정하기	
	@RequestMapping(value = "/temp3/update.do")
	public String update(Temp3VO tempVO, HttpServletRequest request) throws Exception{
		temp3Service.updateTemp(tempVO);
		return "forward:/temp3/selectList.do";	
	}
	
	//임시데이터 삭제하기
	@RequestMapping(value = "/temp3/delete.do")
	public String delete(Temp3VO tempVO, HttpServletRequest request) throws Exception{
		temp3Service.deleteTemp(tempVO);
		return "forward:/temp3/selectList.do";
	}
	
	//JSTL
//	@RequestMapping(value = "/temp/jstl.do")
//	public String jstl(TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
//	
//		return "/temp/Jstl";
//	}
	
	//JSTL Import용
//	@RequestMapping(value = "/temp/jstlImport.do")
//	public String jstlImport(TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
//	
//		return "/temp/JstlImport";
//	}
	
}
