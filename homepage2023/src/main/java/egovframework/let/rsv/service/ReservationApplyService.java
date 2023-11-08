package egovframework.let.rsv.service;

public interface ReservationApplyService {
	
	// 예약가능여부 확인
	public ReservationApplyVO rsvCheck(ReservationApplyVO vo) throws Exception;
	
	// 예약자 등록하기
	public ReservationApplyVO insertReservationApply(ReservationApplyVO vo) throws Exception;
		
}
