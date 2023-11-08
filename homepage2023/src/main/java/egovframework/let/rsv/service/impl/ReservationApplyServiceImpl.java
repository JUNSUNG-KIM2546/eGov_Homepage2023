package egovframework.let.rsv.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.rsv.service.ReservationApplyService;
import egovframework.let.rsv.service.ReservationApplyVO;
import egovframework.let.rsv.service.ReservationService;
import egovframework.let.rsv.service.ReservationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

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
	
	//
	
	
}
