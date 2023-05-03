package egovframework.let.crud.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.crud.service.CrudService;
import egovframework.let.crud.service.CrudVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class CrudController {
	
	@Resource(name = "crudService")
	private CrudService crudService;
	
	//CRUD 목록 가져오기
	@RequestMapping(value = "/crud/selectList.do")
	public String selectList(CrudVO crudVO, HttpServletRequest request, ModelMap model) throws Exception {
		PaginationInfo paginationInfo = new PaginationInfo();
			
		paginationInfo.setCurrentPageNo(crudVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(crudVO.getPageUnit());
		paginationInfo.setPageSize(crudVO.getPageSize());
		
		crudVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		crudVO.setLastIndex(paginationInfo.getLastRecordIndex());
		crudVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//List : java.util.List
		List<EgovMap> resultList = crudService.selectCrudList(crudVO);
		model.addAttribute("resultList",resultList);
		
		int totCnt = crudService.selectCrudListCnt(crudVO);
			
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
			
		return "crud/CrudSelectList";
	}
	
}
