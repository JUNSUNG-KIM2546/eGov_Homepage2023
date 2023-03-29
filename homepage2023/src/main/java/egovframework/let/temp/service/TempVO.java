package egovframework.let.temp.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class TempVO extends ComDefaultVO implements Serializable {

	//임시데이터ID
	private String tempId;
	
	//임시데이터값
	private String tempVal;

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getTempVal() {
		return tempVal;
	}

	public void setTempVal(String tempVal) {
		this.tempVal = tempVal;
	}
	
	
	
}
