package egovframework.let.crud.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class CrudVO extends ComDefaultVO implements Serializable {

	//CRUD ID
	private String crudId;
	
	//제목
	private String crudSj;
	
	//내용
	private String crudCn;
		
	//작성자
	private String userNm;
		
	//작성일
	private java.util.Date frstRegistPnttm;

	
	//겟터스&셋터스
	public String getCrudId() {
		return crudId;
	}

	public void setCrudId(String crudId) {
		this.crudId = crudId;
	}

	public String getCrudSj() {
		return crudSj;
	}

	public void setCrudSj(String crudSj) {
		this.crudSj = crudSj;
	}

	public String getCrudCn() {
		return crudCn;
	}

	public void setCrudCn(String crudCn) {
		this.crudCn = crudCn;
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
	
}
