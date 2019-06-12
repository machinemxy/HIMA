package com.mxy.service;

import java.util.Map;

public interface SudokuService {
	public Map<Integer, Integer> getLevelMap();

	public String[][] getStage(int level);
}
