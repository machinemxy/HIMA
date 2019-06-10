package com.mxy.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mxy.service.SudokuService;

@Service
public class SudokuServiceImpl implements SudokuService {
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

}
