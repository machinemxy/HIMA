package com.mxy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mxy.enumeration.SanmokuStatus;
import com.mxy.model.SanmokuData;
import com.mxy.model.SanmokuResult;
import com.mxy.service.SanmokuService;

@Controller
@RequestMapping(value = "/sanmoku")
public class SanmokuController {
	@Autowired
	private SanmokuService service;

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(HttpSession session, Model model) {
		SanmokuData data = new SanmokuData();
		session.setAttribute("data", data);

		String map = "□□□□□□□□□";
		String[] dispMap = service.generateDispMap(map);
		model.addAttribute("dispMap", dispMap);

		return "sanmoku";
	}

	@RequestMapping(value = "/dropChess/{map}/{index}", method = RequestMethod.GET)
	public String dropChess(HttpSession session, Model model, @PathVariable String map, @PathVariable int index) {
		map = service.decodeMap(map);
		SanmokuResult result = service.playerDropChess(map, index);
		if(result.getStatus() == SanmokuStatus.STILL_PLAYING) {
			model.addAttribute("dispMap", result.getDispMap());
			return "sanmoku";
		} else {
			SanmokuData data = (SanmokuData)session.getAttribute("data");
			if (result.getStatus() == SanmokuStatus.WIN) {
				data.setPlayerScore(data.getPlayerScore() + 1);
			} else if (result.getStatus() == SanmokuStatus.LOSE) {
				data.setCpuScore(data.getCpuScore() + 1);
			}
			data.setGamePlayed(data.getGamePlayed() + 1);
			session.setAttribute("data", data);
			model.addAttribute("message", result.getMessage());
			model.addAttribute("link", service.generateNextGameLink());
			model.addAttribute("dispMap", result.getDispMap());
			return "sanmoku";
		}
	}

	@RequestMapping(value = "/newGame", method = RequestMethod.GET)
	public String newGame(HttpSession session, Model model) {
		String map = "□□□□□□□□□";
		SanmokuData data = (SanmokuData)session.getAttribute("data");
		if (data.getGamePlayed() % 2 == 1) {
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
