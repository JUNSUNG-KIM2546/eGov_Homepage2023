package egovframework.let.rsv.service.impl;

import egovframework.let.rsv.service.ReservationApplyVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("reservationApplyMapper")
public interface ReservationApplyMapper {

	// 예약가능여부 확인(기존 신청여부)
	int duplicateApplyCheck(ReservationApplyVO vo) throws Exception;

	// 예약자 등록
	void insertReservationApply(ReservationApplyVO vo) throws Exception;
}
