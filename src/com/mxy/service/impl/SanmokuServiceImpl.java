package com.mxy.service.impl;

import com.mxy.model.SanmokuResult;
import com.mxy.service.SanmokuService;

public class SanmokuServiceImpl implements SanmokuService {

	@Override
	public String[] generateDispMap(String map) {
		String[] dispMap = new String[map.length()];
		for (int i = 0; i < map.length(); i++) {
			String tile = map.substring(i, i+1);
			if ("□".equals(tile)) {
				dispMap[i] = "<a href='/HIMA/dropChess/" + map + "/" + i + "'>□</a>";
			} else {
				dispMap[i] = tile;
			}
		}
		return dispMap;
	}

	@Override
	public String[] generateNoLinkMap(String map) {
		String[] dispMap = new String[map.length()];
		for (int i = 0; i < map.length(); i++) {
			String tile = map.substring(i, i+1);
			dispMap[i] = tile;
		}
		return dispMap;
	}

	@Override
	public SanmokuResult playerDropChess(String map, int index) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public SanmokuResult cpuDropChess(String map) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String generateNextGameLink() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String generateWinMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String generateLoseMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String generateDrawMessage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
