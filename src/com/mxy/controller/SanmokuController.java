package com.mxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mxy.enumeration.SanmokuStatus;
import com.mxy.model.SanmokuData;
import com.mxy.model.SanmokuResult;
import com.mxy.service.SanmokuService;

@Controller
@RequestMapping("/sanmoku")
@SessionAttributes(types = SanmokuData.class)
public class SanmokuController {
	@Autowired
	private SanmokuService service;

	@ModelAttribute
	public SanmokuData sanmokuData() {
		return new SanmokuData();
	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(@ModelAttribute SanmokuData sanmokuData, Model model) {
		sanmokuData.setCpuScore(0);
		sanmokuData.setPlayerScore(0);
		sanmokuData.setGamePlayed(0);

		String map = "□□□□□□□□□";
		String[] dispMap = service.generateDispMap(map);
		model.addAttribute("dispMap", dispMap);

		return "sanmoku";
	}

	@RequestMapping("/dropChess/{map}/{index}")
	public String dropChess(@ModelAttribute SanmokuData sanmokuData, Model model,
			@PathVariable String map, @PathVariable int index) {
		map = service.decodeMap(map);
		SanmokuResult result = service.playerDropChess(map, index);
		if(result.getStatus() == SanmokuStatus.STILL_PLAYING) {
			model.addAttribute("dispMap", result.getDispMap());
			return "sanmoku";
		} else {
			if (result.getStatus() == SanmokuStatus.WIN) {
				sanmokuData.setPlayerScore(sanmokuData.getPlayerScore() + 1);
			} else if (result.getStatus() == SanmokuStatus.LOSE) {
				sanmokuData.setCpuScore(sanmokuData.getCpuScore() + 1);
			}
			sanmokuData.setGamePlayed(sanmokuData.getGamePlayed() + 1);
			model.addAttribute("message", result.getMessage());
			model.addAttribute("link", service.generateNextGameLink());
			model.addAttribute("dispMap", result.getDispMap());
			return "sanmoku";
		}
	}

	@RequestMapping("/newGame")
	public String newGame(@ModelAttribute SanmokuData sanmokuData, Model model) {
		String map = "□□□□□□□□□";
		if (sanmokuData.getGamePlayed() % 2 == 1) {
			SanmokuResult result = service.cpuDropChess(map);
			model.addAttribute("dispMap", result.getDispMap());
			return "sanmoku";
		} else {
			String[] dispMap = service.generateDispMap(map);
			model.addAttribute("dispMap", dispMap);
			return "sanmoku";
		}
	}
}
