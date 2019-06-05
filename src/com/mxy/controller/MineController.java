package com.mxy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mxy.enumeration.MineStatus;
import com.mxy.model.MineResult;
import com.mxy.service.MineService;

@Controller
@RequestMapping("/mine")
public class MineController {
	@Autowired
	private MineService service;

	@RequestMapping("/init/{difficulty}")
	public String init(HttpSession session, Model model, @PathVariable int difficulty) {
		String[][] realMap = service.initRealMap(difficulty);
		String[][] userMap = service.initUserMap(difficulty);
		session.setAttribute("realMap", realMap);
		session.setAttribute("userMap", userMap);

		String[][] dispMap = service.generateDispMap(userMap);
		model.addAttribute("dispMap", dispMap);

		return "mine";
	}

	@RequestMapping("/act")
	public String act(HttpSession session, Model model,
			@RequestParam int action, @RequestParam int y, @RequestParam int x) {
		String[][] realMap = (String[][])session.getAttribute("realMap");
		String[][] userMap = (String[][])session.getAttribute("userMap");

		MineResult result = service.performAct(realMap, userMap, action, y, x);
		session.setAttribute("userMap", result.getUserMap());
		if (result.getStatus() == MineStatus.STILL_PLAYING) {
			String[][] dispMap = service.generateDispMap(result.getUserMap());
			model.addAttribute("dispMap", dispMap);
		} else {
			model.addAttribute("dispMap", result.getUserMap());
			model.addAttribute("message", result.getMessage());
		}
		return "mine";
	}
}
