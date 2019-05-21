package com.mxy.model;

import com.mxy.enumeration.SanmokuStatus;

public class SanmokuResult {
	private SanmokuStatus status;
	private String map;

	public SanmokuStatus getStatus() {
		return status;
	}
	public void setStatus(SanmokuStatus status) {
		this.status = status;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
}
