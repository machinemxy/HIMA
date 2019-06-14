package com.mxy.service.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

	@Override
	public String judgeResult(String[][] result) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ("".contentEquals(result[i][j])) {
					return getMessage("sudoku.notInput");
				} else if (!isInputCorrect(result[i][j])) {
					return getMessage("sudoku.incorrectInput");
				}
			}
		}

		for (int i = 0; i < 9; i++) {
			Set<String> set = new HashSet<String>();
			for (int j = 0; j < 9; j++) {
				set.add(result[i][j]);
			}
			if (set.size() < 9) {
				return getMessage("sudoku.wrong");
			}
		}

		for (int i = 0; i < 9; i++) {
			Set<String> set = new HashSet<String>();
			for (int j = 0; j < 9; j++) {
				set.add(result[j][i]);
			}
			if (set.size() < 9) {
				return getMessage("sudoku.wrong");
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Set<String> set = new HashSet<String>();
				for (int k = 0; k < 3; k++) {
					for (int l = 0; l < 3; l++) {
						set.add(result[i*3+k][j*3+l]);
					}
				}
				if (set.size() < 9) {
					return getMessage("sudoku.wrong");
				}
			}
		}

		return getMessage("sudoku.right");
	}

	@Override
	public String[][] convertResultToStage(int level, String[][] result) {
		String strStage = getProperty("sudoku." + level);
		char[] charStage = strStage.toCharArray();
		String[][] stage = new String[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char cell = charStage[i * 9 + j];
				if (cell == '_') {
					stage[i][j] = "<input type='text' name='c" + i + j + "' value='" + result[i][j] + "' size='1' maxlength='1'/>";
				} else {
					stage[i][j] = "<input type='text' name='c" + i + j + "' value='" + cell + "' size='1' readonly style='color:blue;font-weight:bold'/>";
				}
			}
		}

		return stage;
	}

	private boolean isInputCorrect(String value) {
		return value.matches("[1-9]");
	}
}
