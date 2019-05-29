package com.mxy.model;

import java.io.Serializable;

public class SanmokuData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int gamePlayed;
	private int playerScore;
	private int cpuScore;

	public int getGamePlayed() {
		return gamePlayed;
	}
	public void setGamePlayed(int gamePlayed) {
		this.gamePlayed = gamePlayed;
	}
	public int getPlayerScore() {
		return playerScore;
	}
	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}
	public int getCpuScore() {
		return cpuScore;
	}
	public void setCpuScore(int cpuScore) {
		this.cpuScore = cpuScore;
	}
}
