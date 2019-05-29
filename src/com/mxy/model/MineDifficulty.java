package com.mxy.model;

public class MineDifficulty {
	private int y;
	private int x;
	private int mineCount;

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getMineCount() {
		return mineCount;
	}
	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public MineDifficulty(int difficulty) {
		switch (difficulty) {
		case 0:
			this.y = 9;
			this.x = 9;
			this.mineCount = 10;
			break;
		case 1:
			this.y = 16;
			this.x = 16;
			this.mineCount = 40;
			break;
		default:
			this.y = 16;
			this.x = 30;
			this.mineCount = 99;
		}
	}
}
