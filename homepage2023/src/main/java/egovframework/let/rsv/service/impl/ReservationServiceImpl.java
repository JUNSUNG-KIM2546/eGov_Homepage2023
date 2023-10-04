package egovframework.let.rsv.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.let.rsv.service.ReservationService;
import egovframework.let.rsv.service.ReservationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("reservationService")
public class ReservationServiceImpl extends EgovAbstractServiceImpl implements ReservationService {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@Resource(name = "reservationMapper")
	protected ReservationMapper reservationMapper;
	
	@Resource(name = "egovRsvIdGnrService")
	protected EgovIdGnrService idgenService ;
	
	// 예약 목록 가져오기
	public List<EgovMap> selectReservationList(ReservationVO vo) throws Exception {
		return reservationMapper.selectReservationList(vo);
	}
		
	// 예약 목록 수
	public int selectReservationListCnt(ReservationVO vo) throws Exception {
		return reservationMapper.selectReservationListCnt(vo);
	}
		
	// 예약 상세정보
	public ReservationVO selectReservation(ReservationVO vo) throws Exception {
		return reservationMapper.selectReservation(vo);
	}
		
	// 예약 등록
	public String insertReservation(ReservationVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setResveId(id);
		reservationMapper.insertReservation(vo);
		
		return id;
	}
		
	// 예약 수정하기
	public void updateReservation(ReservationVO vo) throws Exception {
		reservationMapper.updateReservation(vo);
	}
		
	// 예약 삭제하기
	public void deleteReservation(ReservationVO vo) throws Exception {
		reservationMapper.deleteReservation(vo);
	}
	
}
