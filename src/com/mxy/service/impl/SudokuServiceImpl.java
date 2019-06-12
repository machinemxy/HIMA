package com.mxy.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mxy.service.SudokuService;
import com.mxy.service.base.ServiceBase;

@Service
public class SudokuServiceImpl extends ServiceBase implements SudokuService {
	@Value("${sudoku.levelCount}")
	private int levelCount;

	@Override
	public Map<Integer, Integer> getLevelMap() {
		Map<Integer, Integer> levelMap = new LinkedHashMap<Integer, Integer>();
		for (int i = 1; i <= levelCount; i++) {
			levelMap.put(i, i);
		}
		return levelMap;
	}

	@Override
	public String[][] getStage(int level) {
		String strStage = getProperty("sudoku." + level);
		char[] charStage = strStage.toCharArray();
		String[][] stage = new String[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char cell = charStage[i * 9 + j];
				if (cell == '_') {
					stage[i][j] = "<input type='text' name='c" + i + j + "' size='1' maxlength='1'/>";
				} else {
					stage[i][j] = "<input type='text' name='c" + i + j + "' value='" + cell + "' size='1' readonly style='color:blue;font-weight:bold'/>";
				}
			}
		}

		return stage;
	}

}
