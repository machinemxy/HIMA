package com.mxy.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.mxy.enumeration.SanmokuStatus;
import com.mxy.model.SanmokuResult;
import com.mxy.service.SanmokuService;
import com.mxy.service.base.ServiceBase;

@Service
public class SanmokuServiceImpl extends ServiceBase implements SanmokuService {
	private Random random = new Random();

	@Override
	public String[] generateDispMap(String map) {
		String[] dispMap = new String[map.length()];
		for (int i = 0; i < map.length(); i++) {
			String tile = map.substring(i, i+1);
			if ("□".equals(tile)) {
				dispMap[i] = "<a href='/HIMA/sanmoku/dropChess/" + encodeMap(map) + "/" + i + "'>□</a>";
			} else {
				dispMap[i] = tile;
			}
		}
		return dispMap;
	}

	@Override
	public SanmokuResult playerDropChess(String map, int index) {
		char[] charMap = map.toCharArray();
		charMap[index] = '●';
		map = String.valueOf(charMap);
		if (isWin(charMap, '●')) {
			SanmokuResult result = new SanmokuResult();
			result.setStatus(SanmokuStatus.WIN);
			result.setDispMap(generateNoLinkMap(map));
			result.setMessage(getMessage("sanmoku.win"));
			return result;
		} else if (getEmptyCount(charMap) == 0) {
			SanmokuResult result = new SanmokuResult();
			result.setStatus(SanmokuStatus.DRAW);
			result.setDispMap(generateNoLinkMap(map));
			result.setMessage(getMessage("sanmoku.draw"));
			return result;
		}

		SanmokuResult result = cpuDropChess(map);
		return result;
	}

	@Override
	public SanmokuResult cpuDropChess(String map) {
		char[] charMap = map.toCharArray();
		int emptyCount = getEmptyCount(charMap);
		int cpuDropPosition = random.nextInt(emptyCount);
		int currentIndex = 0;
		for (int i = 0; i < charMap.length; i++) {
			if (charMap[i] == '□') {
				if (currentIndex == cpuDropPosition) {
					charMap[i] = '▲';
					break;
				} else {
					currentIndex++;
				}
			}
		}
		map = String.valueOf(charMap);
		SanmokuResult result = new SanmokuResult();
		if (isWin(charMap, '▲')) {
			result.setStatus(SanmokuStatus.LOSE);
			result.setDispMap(generateNoLinkMap(map));
			result.setMessage(getMessage("sanmoku.lose"));
		} else if (getEmptyCount(charMap) == 0) {
			result.setStatus(SanmokuStatus.DRAW);
			result.setDispMap(generateNoLinkMap(map));
			result.setMessage(getMessage("sanmoku.draw"));
		} else {
			result.setStatus(SanmokuStatus.STILL_PLAYING);
			result.setDispMap(generateDispMap(map));
		}
		return result;
	}

	@Override
	public String generateNextGameLink() {
		return "<a href='/HIMA/sanmoku/newGame'>もう一局</a>";
	}

	@Override
	public String decodeMap(String map) {
		char[] charMap = map.toCharArray();
		for (int i = 0; i < charMap.length; i++) {
			if (charMap[i] == '0') {
				charMap[i] = '□';
			} else if (charMap[i] == '1') {
				charMap[i] = '●';
			} else if (charMap[i] == '2') {
				charMap[i] = '▲';
			}
		}
		return String.valueOf(charMap);
	}

	private String[] generateNoLinkMap(String map) {
		String[] dispMap = new String[map.length()];
		for (int i = 0; i < map.length(); i++) {
			String tile = map.substring(i, i+1);
			dispMap[i] = tile;
		}
		return dispMap;
	}

	private boolean isWin(char[] charMap, char chess) {
		int[][] winPatterns = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		for (int[] winPattern : winPatterns) {
			if (charMap[winPattern[0]] == chess && charMap[winPattern[1]] == chess && charMap[winPattern[2]] == chess) {
				return true;
			}
		}
		return false;
	}

	private int getEmptyCount(char[] charMap) {
		int count = 0;
		for (int i = 0; i < charMap.length; i++) {
			if (charMap[i] == '□') {
				count++;
			}
		}
		return count;
	}

	private String encodeMap(String map) {
		char[] charMap = map.toCharArray();
		for (int i = 0; i < charMap.length; i++) {
			if (charMap[i] == '□') {
				charMap[i] = '0';
			} else if (charMap[i] == '●') {
				charMap[i] = '1';
			} else if (charMap[i] == '▲') {
				charMap[i] = '2';
			}
		}
		return String.valueOf(charMap);
	}
}
