package com.mxy.service;

import java.util.Map;

public interface SudokuService {
	public Map<Integer, Integer> getLevelMap();

	public String[][] getStage(int level);

	public String judgeResult(String[][] result);

	public String[][] convertResultToStage(int level, String[][] result);
}
