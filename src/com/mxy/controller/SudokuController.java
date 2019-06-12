package com.mxy.controller;

import java.util.Map;

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
}
