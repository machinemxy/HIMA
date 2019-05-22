package com.mxy.model;

import com.mxy.enumeration.SanmokuStatus;

public class SanmokuResult {
	private SanmokuStatus status;
	private String[] dispMap;
	private String message;

	public SanmokuStatus getStatus() {
		return status;
	}
	public void setStatus(SanmokuStatus status) {
		this.status = status;
	}
	public String[] getDispMap() {
		return dispMap;
	}
	public void setDispMap(String[] dispMap) {
		this.dispMap = dispMap;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
