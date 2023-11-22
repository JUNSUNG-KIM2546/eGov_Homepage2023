package egovframework.com.cmm.service;

import java.io.Serializable;

/**
 * <pre>
 * Class Name  : JsonResponse.java
 * Description : Json응답 
 * Copyright (C) 2015 by J2soft All right reserved.
 * </pre>
 */
public class JsonResponse implements Serializable{

	/**
	 * 성공 여부
	 */
	private boolean success =false;
	
	/**
	 * 권한에러
	 */
	private boolean auth = false;

	/**
	 * 에러 코드
	 */
	private String code;

	/**
	 * 에러 메시지
	 */
	private String message;
	
	private Serializable data;
	
	private Serializable files;
	
	private Serializable wireData;
	
	private int count = 0;

	public JsonResponse(){
	}
	
	public JsonResponse(boolean success){
		this.success = success;
	}

	public JsonResponse(boolean success, String message){
		this.success = success;
		this.message = message;
	}

	public JsonResponse(boolean success, String code, String message){
		this.success = success;
		this.code = code;
		this.message = message;
	}
	
	
	
	
	//get/set
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public Serializable getData() {
		return data;
	}

	public void setData(Serializable data) {
		this.data = data;
	}

	
	public boolean isAuth() {
		return auth;
	}

	
	public void setAuth(boolean auth) {
		this.auth = auth;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Serializable getFiles() {
		return files;
	}

	public void setFiles(Serializable files) {
		this.files = files;
	}

	public Serializable getWireData() {
		return wireData;
	}

	public void setWireData(Serializable wireData) {
		this.wireData = wireData;
	}

}
