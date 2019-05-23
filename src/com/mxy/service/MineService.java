package com.mxy.service;

import com.mxy.model.MineResult;

public interface MineService {
	public String[][] initRealMap(int difficulty);

	public String[][] initUserMap(int difficulty);

	public String[][] generateDispMap(String[][] userMap);

	public MineResult performAct(String[][] realMap, String[][] userMap, int action, int y, int x);
}
