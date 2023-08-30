package egovframework.let.test.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class TestVO extends ComDefaultVO implements Serializable {

	//CRUD ID
	private String testId;
	
	//제목
	private String sj;
	
	//내용
	private String cn;
		
	//작성자
	private String userNm;
		
	//최초등록일자
	private java.util.Date frstRegistPnttm;
	
	//최초등록자
	private String frstRegisterId;
	
	//최종수정일자
	private java.util.Date lastUpdtPnttm;
	
	//최종수정자
	private String lastUpdusrId;
	

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public java.util.Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(java.util.Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public java.util.Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(java.util.Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	

	
}
