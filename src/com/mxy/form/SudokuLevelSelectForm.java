package com.mxy.form;

import java.io.Serializable;

public class SudokuLevelSelectForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private int level;

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
