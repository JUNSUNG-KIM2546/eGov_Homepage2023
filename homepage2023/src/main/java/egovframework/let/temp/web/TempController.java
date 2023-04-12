package egovframework.let.temp.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class TempController {
	
	@Resource(name = "tempService")
	private TempService tempService;
	
	//임시데이터 가져오기
	@RequestMapping(value = "/temp/select.do")
	public String select(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception {
		TempVO result = tempService.selectTemp(tempVO);
		model.addAttribute("result", result);
		return "temp/TempSelect";
	}
	
	//임시데이터 목록 가져오기
	@RequestMapping(value = "/temp/selectList.do")
	public String selectList(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception {
		//List : java.util.List
		List<EgovMap> resultList = tempService.selectTempList(tempVO);
		model.addAttribute("resultList",resultList);
		return "temp/TempSelectList";
	}
	
	//임시데이터 등록|수정		-등록과 수정 페이지는 같이 만들수 있다.
	@RequestMapping(value = "/temp/tempRegist.do")
	public String tempRegist(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		TempVO result = new TempVO();
		//egovframework.let.utl.fcc.service.EgovStringUtil;
		if(!EgovStringUtil.isEmpty(tempVO.getTempId())) {	//pk값이 있으면 수정페이지로, 없으면 등록페이지로
			result = tempService.selectTemp(tempVO);
		}
		model.addAttribute("result", result);
		
		return "temp/TempRegist";
	}
	
	//임시데이터 등록하기
	@RequestMapping(value = "/temp/insert.do")
	public String insert(TempVO tempVO, HttpServletRequest request) throws Exception{
		tempService.insertTemp(tempVO);
		return "forward:/temp/selectList.do";
	}
		
	//임시데이터 수정하기	
	@RequestMapping(value = "/temp/update.do")
	public String update(TempVO tempVO, HttpServletRequest request) throws Exception{
		tempService.updateTemp(tempVO);
		return "forward:/temp/selectList.do";	
	}
	
	//임시데이터 삭제하기
	@RequestMapping(value = "/temp/delete.do")
	public String delete(TempVO tempVO, HttpServletRequest request) throws Exception{
		tempService.deleteTemp(tempVO);
		return "forward:/temp/selectList.do";
	}
	
	//JSTL
	@RequestMapping(value = "/temp/jstl.do")
	public String jstl(TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
	
		return "/temp/Jstl";
	}
	
	//JSTL Import용
	@RequestMapping(value = "/temp/jstlImport.do")
	public String jstlImport(TempVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
	
		return "/temp/JstlImport";
	}
	
}
