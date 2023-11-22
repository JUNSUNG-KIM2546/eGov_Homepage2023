package egovframework.com.cmm.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.ibm.icu.math.BigDecimal;

public class ExcelDownloadView extends AbstractExcelView {
	@SuppressWarnings({"unchecked", "deprecation"})
	protected void buildExcelDocument (Map model, HSSFWorkbook wb, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Map<String, Object> dataMap = (Map<String, Object>)model.get("dataMap");
		
		String title = (String)dataMap.get("title");
		List<String> columMap = (List<String>)dataMap.get("columMap");
		List<Object> valueMap = (List<Object>)dataMap.get("valueMap");
		
		HSSFCell cell = null;
		
		// 셀 스타일 설정 값
		HSSFCellStyle style = this.getHSSFCellstyleByHeader(wb);
		
		HSSFSheet sheet = wb.createSheet(title);
		
		if(columMap != null && columMap.size() > 0 ) {
			//header 정보 등록
			for(int i = 0; i < columMap.size(); i++) {
				sheet.setColumnWidth(i, 500*8); // 시트
				
				cell = getCell(sheet, 0, i); //getCell(sheet, 0, i);
				cell.setCellStyle(style);
				setText(cell, columMap.get(i));	//setText(cell, columMap.get(i));
			}
			
			// body 정보 등록
			Object obj = null;
			for (int i = 0; i < valueMap.size(); i++) {
				Map<String, Object> data = (Map<String, Object>) valueMap.get(i);
				for(int di=0; di < columMap.size(); di++) {
					obj = data.get(columMap.get(di));
					cell = getCell(sheet, 1 + i, di);	//cell = getCell(sheet, 1 + i, di);
					if(obj instanceof String) {
						setText(cell, (String)obj);	//setText(cell, (String)obj);
					}
					else if(obj instanceof Integer || obj instanceof Long || obj instanceof Double || obj instanceof BigDecimal || obj instanceof Float) {
						cell.setCellValue(Double.valueOf(String.valueOf(obj)));
					}
				}
			}
		}
	}
	
	private HSSFCellStyle getHSSFCellstyleByHeader(HSSFWorkbook wd) {
		HSSFCellStyle style =  wd.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFCellStyle.BORDER_THIN);
		
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		return style;
	}
}
