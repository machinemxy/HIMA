package com.mxy.service;

import com.mxy.model.SanmokuResult;

public interface SanmokuService {
	public String[] generateDispMap(String map);

	public String[] generateNoLinkMap(String map);

	public SanmokuResult playerDropChess(String map, int index);

	public SanmokuResult cpuDropChess(String map);

	public String generateNextGameLink();

	public String generateWinMessage();

	public String generateLoseMessage();

	public String generateDrawMessage();
}
