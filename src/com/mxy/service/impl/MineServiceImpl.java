package com.mxy.service.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mxy.enumeration.MineStatus;
import com.mxy.model.MineDifficulty;
import com.mxy.model.MineResult;
import com.mxy.service.MineService;
import com.mxy.service.base.ServiceBase;

@Service
public class MineServiceImpl extends ServiceBase  implements MineService{

	@Override
	public String[][] initRealMap(int difficulty) {
		MineDifficulty mineDifficulty = new MineDifficulty(difficulty);
		// 地雷生成
		List<Point> potentialMines = new ArrayList<Point>();
		for (int y = 0; y < mineDifficulty.getY(); y++) {
			for (int x = 0; x < mineDifficulty.getX(); x++) {
				potentialMines.add(new Point(x, y));
			}
		}
		Collections.shuffle(potentialMines);
		String[][] realMap = new String[mineDifficulty.getY()][mineDifficulty.getX()];
		for (int i = 0; i < mineDifficulty.getMineCount(); i++) {
			Point mine = potentialMines.get(i);
			realMap[mine.y][mine.x] = "*";
		}

		// 地雷以外のセル作成
		for (int y = 0; y < mineDifficulty.getY(); y++) {
			for (int x = 0; x < mineDifficulty.getX(); x++) {
				if (!isMine(realMap, y, x)) {
					int mineCount = 0;
					mineCount += isMine(realMap, y-1, x-1) ? 1 : 0;
					mineCount += isMine(realMap, y-1, x) ? 1 : 0;
					mineCount += isMine(realMap, y-1, x+1) ? 1 : 0;
					mineCount += isMine(realMap, y, x-1) ? 1 : 0;
					mineCount += isMine(realMap, y, x+1) ? 1 : 0;
					mineCount += isMine(realMap, y+1, x-1) ? 1 : 0;
					mineCount += isMine(realMap, y+1, x) ? 1 : 0;
					mineCount += isMine(realMap, y+1, x+1) ? 1 : 0;
					realMap[y][x] = "" + mineCount;
				}
			}
		}

		return realMap;
	}

	@Override
	public String[][] initUserMap(int difficulty) {
		MineDifficulty mineDifficulty = new MineDifficulty(difficulty);
		String[][] userMap = new String[mineDifficulty.getY()][mineDifficulty.getX()];
		for (int y = 0; y < mineDifficulty.getY(); y++) {
			for (int x = 0; x < mineDifficulty.getX(); x++) {
				userMap[y][x] = "ﾛ";
			}
		}
		return userMap;
	}

	@Override
	public String[][] generateDispMap(String[][] userMap) {
		String[][] dispMap = new String[userMap.length][userMap[0].length];
		for (int y = 0; y < userMap.length; y++) {
			String[] userRow = userMap[y];
			for (int x = 0; x < userRow.length; x++) {
				String userCell = userRow[x];
				if ("ﾛ".equals(userCell)) {
					dispMap[y][x] = "<a href='#' onclick='act(" + y + "," + x + ")'>ﾛ</a>";
				} else if ("F".equals(userCell)) {
					dispMap[y][x] = "<a href='#' onclick='act(" + y + "," + x + ")' style='color:red'>F</a>";
				} else {
					dispMap[y][x] = userCell;
				}
			}
		}
		return dispMap;
	}

	@Override
	public MineResult performAct(String[][] realMap, String[][] userMap, int action, int y, int x) {
		MineResult result = new MineResult();

		if (action == 2) {
			// フラグ立つ
			if ("F".equals(userMap[y][x])) {
				// 既にフラグ立っている、外す
				userMap[y][x] = "ﾛ";
				result.setUserMap(userMap);
				result.setStatus(MineStatus.STILL_PLAYING);
			} else {
				// フラグはまだ立っていない、立つ
				userMap[y][x] = "F";
				result.setUserMap(userMap);
				// 勝利判定
				if (isAllMineMarked(realMap, userMap)) {
					result.setStatus(MineStatus.WIN);
					result.setMessage(getMessage("mine.win"));
				} else {
					result.setStatus(MineStatus.STILL_PLAYING);
				}
			}
		} else {
			// 掘る
			// 生死判定
			if (isMine(realMap, y, x)) {
				userMap[y][x] = "*";
				result.setUserMap(userMap);
				result.setStatus(MineStatus.LOSE);
				result.setMessage(getMessage("mine.lose"));
			} else {
				result.setUserMap(dig(realMap, userMap, y, x));
				result.setStatus(MineStatus.STILL_PLAYING);
			}
		}

		return result;
	}

	private boolean isMine(String[][] realMap, int y, int x) {
		if (y < 0) {
			return false;
		}

		if (x < 0) {
			return false;
		}

		if (y >= realMap.length) {
			return false;
		}

		if (x >= realMap[0].length) {
			return false;
		}

		if ("*".equals(realMap[y][x])) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isAllMineMarked(String[][] realMap, String[][] userMap) {
		for (int y = 0; y < realMap.length; y++) {
			String[] realRow = realMap[y];
			String[] userRow = userMap[y];
			for (int x = 0; x < realRow.length; x++) {
				String realCell = realRow[x];
				String userCell = userRow[x];
				if ("*".equals(realCell) && !"F".equals(userCell)) {
					return false;
				}
				if (!"*".equals(realCell) && "F".equals(userCell)) {
					return false;
				}
			}
		}
		return true;
	}

	private String[][] dig(String[][] realMap, String[][] userMap, int y, int x) {
		if (y < 0) {
			return userMap;
		}

		if (x < 0) {
			return userMap;
		}

		if (y >= userMap.length) {
			return userMap;
		}

		if (x >= userMap[0].length) {
			return userMap;
		}

		if (!("ﾛ".equals(userMap[y][x]) || "F".equals(userMap[y][x]))) {
			return userMap;
		}

		userMap[y][x] = realMap[y][x];
		if ("0".equals(userMap[y][x])) {
			userMap = dig(realMap, userMap, y-1, x-1);
			userMap = dig(realMap, userMap, y-1, x);
			userMap = dig(realMap, userMap, y-1, x+1);
			userMap = dig(realMap, userMap, y, x-1);
			userMap = dig(realMap, userMap, y, x+1);
			userMap = dig(realMap, userMap, y+1, x-1);
			userMap = dig(realMap, userMap, y+1, x);
			userMap = dig(realMap, userMap, y+1, x+1);
		}

		return userMap;
	}
}
