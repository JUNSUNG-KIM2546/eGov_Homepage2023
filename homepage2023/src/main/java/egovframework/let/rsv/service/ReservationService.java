package egovframework.let.rsv.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface ReservationService {
	
	// 예약 목록 가져오기
	public List<EgovMap> selectReservationList(ReservationVO vo) throws Exception;
	
	// 예약 목록 수
	public int selectReservationListCnt(ReservationVO vo) throws Exception;
	
	// 예약 상세정보
	public ReservationVO selectReservation(ReservationVO vo) throws Exception;
	
	// 예약 등록
	public String insertReservation(ReservationVO vo) throws Exception;
	
	// 예약 수정하기
	public void updateReservation(ReservationVO vo) throws Exception;
	
	// 예약 삭제하기
	public void deleteReservation(ReservationVO vo) throws Exception;
		
}
