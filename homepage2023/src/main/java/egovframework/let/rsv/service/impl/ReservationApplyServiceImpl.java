package egovframework.let.rsv.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.eval.forked.ForkedEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.com.cmm.service.FileVO;
import egovframework.let.rsv.service.ReservationApplyService;
import egovframework.let.rsv.service.ReservationApplyVO;
import egovframework.let.rsv.service.ReservationService;
import egovframework.let.rsv.service.ReservationVO;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("reservationApplyService")
public class ReservationApplyServiceImpl extends EgovAbstractServiceImpl implements ReservationApplyService {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name = "reservationApplyMapper")
	protected ReservationApplyMapper reservationApplyMapper;
	
	@Resource(name = "egovReqIdGnrService")
	protected EgovIdGnrService idgenService ;
	
	@Resource(name = "reservationService")
	protected ReservationService reservationService ;
	
	@Resource(name = "egovReqTempIdGnrService")
	private EgovIdGnrService idgenTempService;
	
	// 예약가능여부 확인
	public ReservationApplyVO rsvCheck (ReservationApplyVO vo) throws Exception {
		// 신청 인원 체크
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setResveId(vo.getResveId());
		ReservationVO result = reservationService.selectReservation(reservationVO);
		if(result.getMaxAplyCnt() <= result.getApplyFCnt()) {
			vo.setErrorCode("ERROR-R1");
			vo.setMessage("마감 되었습니다.");
		}
		else if(reservationApplyMapper.duplicateApplyCheck(vo) > 0) {
			vo.setErrorCode("ERROR-R2");
			vo.setMessage("이미 해당 프로그램 예약이 되어져 있습니다.");
		}
		
		return vo;
	}
	
	// 예약자 등록하기
	public ReservationApplyVO insertReservationApply(ReservationApplyVO vo) throws Exception {
		// 신청 인원 체크
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setResveId(vo.getResveId());
		ReservationVO result = reservationService.selectReservation(reservationVO);
		if(result.getMaxAplyCnt() <= result.getApplyCnt()) {
			vo.setErrorCode("ERROR-R1");
			vo.setMessage("마감 되었습니다.");
		}
		else {
			// 기존 신청여부
			System.out.println(reservationApplyMapper.duplicateApplyCheck(vo) +"-----------------------");
			if(reservationApplyMapper.duplicateApplyCheck(vo) > 0) {
				vo.setErrorCode("ERROR-R2");
				vo.setMessage("이미 해당 프로그램 예약이 되어져 있습니다.");
			}
			else {
				String id = idgenService.getNextStringId();
				vo.setReqstId(id);
				reservationApplyMapper.insertReservationApply(vo);
			}
		}
		
		return vo;
	}

	// 예약자 목록 가져오시
	public List<EgovMap> selectReservationApplyList(ReservationApplyVO vo) throws Exception {
		return reservationApplyMapper.selectReservationApplyList(vo);
	}
	
	// 예약자 목록 수
	public int selectReservationApplyListCnt(ReservationApplyVO vo) throws Exception {
		return reservationApplyMapper.selectReservationApplyListCnt(vo);
	}

	// 예약자 상세정보
	public ReservationApplyVO selectReservationApply(ReservationApplyVO vo) throws Exception {
		return reservationApplyMapper.selectReservationApply(vo);
	}

	// 예약자 수정하기
	public void updateReservationApply(ReservationApplyVO vo) throws Exception {
		reservationApplyMapper.updateReservationApply(vo);
	}

	// 예약자 삭제하기
	public void deleteReservationApply(ReservationApplyVO vo) throws Exception {
		reservationApplyMapper.deleteReservationApply(vo);
	}
	
	//
	@Override
	public void updateReservationConfirm(ReservationApplyVO vo) throws Exception {
		reservationApplyMapper.updateReservationConfirm(vo);
	}
	
	//예약자 엑셀 업로드
	@Override
	public Map<String, Object> excelUpload(FileVO fileVO, ReservationApplyVO vo) throws Exception {
		String fileExt = fileVO.getFileExtsn();
		
		FileInputStream stream = new FileInputStream(fileVO.getFileStreCours() + "/" + fileVO.getStreFileNm());
		File file = new File(fileVO.getFileStreCours() + "/" + fileVO.getStreFileNm());
		
		Boolean result = true;
		Boolean totResult = true;
		String resultMsg = "";
		List<EgovMap> resultList = new ArrayList<EgovMap>();
		List<String> duplList = new ArrayList<String>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 기존 예약자
		vo.setFirstIndex(0);
		List<EgovMap> existUserList = reservationApplyMapper.selectReservationApplyList(vo);
		
		// 엑셀Id
		String tempId = idgenTempService.getNextStringId();
		vo.setReqsttempId(tempId);	// 여기 확인(금요일)
		
		try {
			Workbook wb = null;		// org.apache.poi.ss.usermodel.workbook
			if("XLS".equals(fileExt.toUpperCase())) {
				wb = WorkbookFactory.create(stream);
			}
			else if("XLSX".equals(fileExt.toUpperCase())) {
				wb = WorkbookFactory.create(stream);
			}
			FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
			
			// int sheetNum = wb.getNumberOfSheets();	// 시트갯수 가져오기
			if(wb != null) {
				Sheet sheet = wb.getSheetAt(0);	// 첫번째 시트
				int rows = sheet.getPhysicalNumberOfRows();
				for(int r = 1; r < rows; r++) {	//row 루프
					Row row = sheet.getRow(r);	//roe 가져오기
					if(row != null) {
						for(int c = 0; c < 4; c++) {
							Cell cell = row.getCell(c);
							result = true;
							if(cell != null) {
								String value = "";
								// 셀 타입에 맞춰서 값 호출
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_FORMULA:
									if(!EgovStringUtil.isEmpty(cell.toString())) {
										switch (eval.evaluateFormulaCell(cell)) {
										case Cell.CELL_TYPE_NUMERIC:
											if(HSSFDateUtil.isCellDateFormatted(cell)) {
												SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // java.text.SimpleDateFormat
												value = formatter.format(cell.getDateCellValue());
											} else {
												value = "" + (long)cell.getNumericCellValue();
											}
											break;
											
										case Cell.CELL_TYPE_STRING:
											value = "" + cell.getRichStringCellValue();
											break;
											
										case Cell.CELL_TYPE_BLANK:
											value = "";
											break;
											
										case Cell.CELL_TYPE_ERROR:
											value = "" + cell.getErrorCellValue();
											break;
											
										case Cell.CELL_TYPE_BOOLEAN:
											value = "" + cell.getBooleanCellValue();
											break;
											
										default:
											break;
										}
									}
									break;
									
								case Cell.CELL_TYPE_NUMERIC:
									if(HSSFDateUtil.isCellDateFormatted(cell)) {
										SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
										value = formatter.format(cell.getDateCellValue());
									} else {
										value = "" + (long)cell.getNumericCellValue();
									}
									break;
									
								case Cell.CELL_TYPE_STRING:
									value = "" + cell.getRichStringCellValue();
									break;
									
								case Cell.CELL_TYPE_BLANK:
									value = "";
									break;
									
								case Cell.CELL_TYPE_ERROR:
									value = "" + cell.getErrorCellValue();
									break;
									
								case Cell.CELL_TYPE_BOOLEAN:
									value = "" + cell.getBooleanCellValue();
									break;
									
								default:
									break;
								}
								
								if(!EgovStringUtil.isEmpty(value)) {
									value = value.trim();
								}
								
								switch (c) {
								case 0:
									vo.setUserId(value);	// 신청자ID
									break;
								case 1:
									vo.setChargerNm(value);	// 신청자명
									break;
								case 2:
									vo.setTelno(value);	// 연락처
									break;
								case 3:
									vo.setEmail(value);	// 이메일
									break;

								default:
									break;
								}
							}
						}
						
						// 빈 행은 제외
						if(!EgovStringUtil.isEmpty(vo.getUserId())) {
							List<String> existIdList = new ArrayList<>();
							// 기존유저 중복 체크
							if(existUserList != null) {
								for(EgovMap cu : existUserList) {
									existIdList.add(cu.get("frstRegisterId").toString());
								}
								
								if(existIdList.contains(vo.getUserId())) {
									EgovMap userMap = new EgovMap();
									userMap.put("userId", vo.getUserId());
									userMap.put("message", "이미 등록된 ID입니다.");
									resultList.add(userMap);
									
									result = false;
									totResult = false;
								}
							}
							
							// 엑셀 중복 체크
							if(result && duplList.contains(vo.getUserId())) {
								EgovMap userMap = new EgovMap();
								userMap.put("userId",vo.getUserId());
								userMap.put("message", "엑셀에 중복으로 입력되었습니다.");
								resultList.add(userMap);
								
								result = false;
								totResult = false;
							}
							
							if(result && !EgovStringUtil.isEmpty(vo.getUserId())) {
								String id = idgenService.getNextStringId();
								vo.setReqstId(id);
								reservationApplyMapper.insertReservationApplyTemp(vo);
								
								duplList.add(vo.getUserId());
							}
						}
					}
				}
				
				List<EgovMap> tempList = reservationApplyMapper.selectReservationApplyTemp(vo);
				if(tempList.size() > 0) {
					// 일괄 등록
					reservationApplyMapper.insertReservationApplyTempAll(vo);
				}
			}
		} catch (FileNotFoundException e) {
			result = false;
			resultMsg = "문제가 발생하여 완료하지 못하였습니다.";
			e.printStackTrace();	// 자기 로컬에서만 사용 (취약점)
		} catch (InvalidFormatException e) {
			result = false;
			resultMsg = "문제가 발생하여 완료하지 못하였습니다.";
			e.printStackTrace();
		} catch (Exception e) {
			result = false;
			resultMsg = "문제가 발생하여 완료하지 못하였습니다.";
			e.printStackTrace();
		} finally {
			// 임시 데이터 삭제
			reservationApplyMapper.deleteReservationApplyTemp(vo);
			file.delete();
		}
		
		map.put("success", totResult);
		map.put("msg", resultMsg);
		map.put("resultList", resultList);
		
		return map;
	}
	
}
