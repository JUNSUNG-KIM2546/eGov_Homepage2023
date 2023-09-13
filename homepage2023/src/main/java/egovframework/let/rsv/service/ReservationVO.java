package egovframework.let.rsv.service;

import java.io.Serializable;
import java.util.Date;

import egovframework.com.cmm.ComDefaultVO;

public class ReservationVO extends ComDefaultVO implements Serializable {
	// 예약ID
	private String resveId;
	// 예약구분코드
	private String resveSeCode;
	// 예약제목
	private String resveSj;
	// 강사명
	private String recNm;
	// 최대참가인원
	private int maxAplyCnt;
	// 운영시작일
	private String useBeginDt;
	// 운영종료일
	private String useEndDt;
	// 운영시작시간
	private String useBeginTime;
	// 운영종료시간
	private String useEndTime;
	// 내용
	private String resveCn;
	// 신청기간시작일
	private String reqstBgnde;
	// 신청기간종료일
	private String reqstEndde;
	// 사용여부
	private String useAt;
	// 최초등록시점
	private Date frstRegistPnttm;
	// 최초등록자ID
	private String frstRegisterId;
	// 최종수정시점
	private Date lastUpdtPnttm;
	// 최종수정자ID
	private String lastUpdusrId;
	// 사용자ID
	private String userId;
	// 관리자여부
	private String mngAt;
	// 신청자
	private int applyCnt;
	// 예약상태
	private String applyStatus;
	// 승인된 신청자
	private int applyFCnt;
	// 검색일자
	private String searchDate;
	
	public String getResveId() {
		return resveId;
	}
	public void setResveId(String resveId) {
		this.resveId = resveId;
	}
	public String getResveSeCode() {
		return resveSeCode;
	}
	public void setResveSeCode(String resveSeCode) {
		this.resveSeCode = resveSeCode;
	}
	public String getResveSj() {
		return resveSj;
	}
	public void setResveSj(String resveSj) {
		this.resveSj = resveSj;
	}
	public String getRecNm() {
		return recNm;
	}
	public void setRecNm(String recNm) {
		this.recNm = recNm;
	}
	public int getMaxAplyCnt() {
		return maxAplyCnt;
	}
	public void setMaxAplyCnt(int maxAplyCnt) {
		this.maxAplyCnt = maxAplyCnt;
	}
	public String getUseBeginDt() {
		return useBeginDt;
	}
	public void setUseBeginDt(String useBeginDt) {
		this.useBeginDt = useBeginDt;
	}
	public String getUseEndDt() {
		return useEndDt;
	}
	public void setUseEndDt(String useEndDt) {
		this.useEndDt = useEndDt;
	}
	public String getUseBeginTime() {
		return useBeginTime;
	}
	public void setUseBeginTime(String useBeginTime) {
		this.useBeginTime = useBeginTime;
	}
	public String getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(String useEndTime) {
		this.useEndTime = useEndTime;
	}
	public String getResveCn() {
		return resveCn;
	}
	public void setResveCn(String resveCn) {
		this.resveCn = resveCn;
	}
	public String getReqstBgnde() {
		return reqstBgnde;
	}
	public void setReqstBgnde(String reqstBgnde) {
		this.reqstBgnde = reqstBgnde;
	}
	public String getReqstEndde() {
		return reqstEndde;
	}
	public void setReqstEndde(String reqstEndde) {
		this.reqstEndde = reqstEndde;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}
	public String getFrstRegisterId() {
		return frstRegisterId;
	}
	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}
	public Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}
	public void setLastUpdtPnttm(Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}
	public String getLastUpdusrId() {
		return lastUpdusrId;
	}
	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMngAt() {
		return mngAt;
	}
	public void setMngAt(String mngAt) {
		this.mngAt = mngAt;
	}
	public int getApplyCnt() {
		return applyCnt;
	}
	public void setApplyCnt(int applyCnt) {
		this.applyCnt = applyCnt;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public int getApplyFCnt() {
		return applyFCnt;
	}
	public void setApplyFCnt(int applyFCnt) {
		this.applyFCnt = applyFCnt;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
}
