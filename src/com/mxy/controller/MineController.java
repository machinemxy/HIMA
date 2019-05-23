package com.mxy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mxy.enumeration.MineStatus;
import com.mxy.model.MineResult;
import com.mxy.service.MineService;

@Controller
@RequestMapping(value = "/mine")
public class MineController {
	// TODO add autowired
	private MineService service;

	@RequestMapping(value = "/init/{difficulty}", method = RequestMethod.GET)
	public String init(HttpSession session, Model model, @PathVariable int difficulty) {
		String[][] realMap = service.initRealMap(difficulty);
		String[][] userMap = service.initUserMap(difficulty);
		session.setAttribute("realMap", realMap);
		session.setAttribute("userMap", userMap);

		String[][] dispMap = service.generateDispMap(userMap);
		model.addAttribute("dispMap", dispMap);

		return "mine";
	}

	@RequestMapping(value = "/act/{action}/{y}/{x}", method = RequestMethod.POST)
	public String act(HttpSession session, Model model, @PathVariable int action, @PathVariable int y, @PathVariable int x) {
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
