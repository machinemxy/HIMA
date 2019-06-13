package com.mxy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mxy.form.SudokuLevelSelectForm;
import com.mxy.service.SudokuService;

@Controller
@RequestMapping("/sudoku")
public class SudokuController {
	@Autowired
	private SudokuService service;

	@RequestMapping("/levelSelect")
	public String levelSelect(Model model) {
		Map<Integer, Integer> levelMap = service.getLevelMap();
		model.addAttribute("levelMap", levelMap);
		model.addAttribute("sudokuLevelSelectForm", new SudokuLevelSelectForm());
		return "sudoku-levelSelect";
	}

	@RequestMapping("/mainStage")
	public String mainStage(Model model, @ModelAttribute SudokuLevelSelectForm sudokuLevelSelectForm) {
		int level = sudokuLevelSelectForm.getLevel();
		model.addAttribute("level", level);
		String[][] stage = service.getStage(level);
		model.addAttribute("stage", stage);
		return "sudoku";
	}

	@RequestMapping("/finish")
	public String finish(Model model, HttpServletRequest request) {
		String[][] result = new String[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				result[i][j] = request.getParameter("c" + i + j);
			}
		}
		int level = Integer.parseInt(request.getParameter("level"));
		model.addAttribute("level", level);
		String message = service.judgeResult(result);
		model.addAttribute("message", message);
		String[][] stage = service.convertResultToStage(level, result);
		model.addAttribute("stage", stage);
		return "sudoku";
	}
}
