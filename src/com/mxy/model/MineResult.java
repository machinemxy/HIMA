package com.mxy.model;

import com.mxy.enumeration.MineStatus;

public class MineResult {
	private MineStatus status;
	private String message;
	private String[][] userMap;

	public MineStatus getStatus() {
		return status;
	}
	public void setStatus(MineStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[][] getUserMap() {
		return userMap;
	}
	public void setUserMap(String[][] userMap) {
		this.userMap = userMap;
	}
}
