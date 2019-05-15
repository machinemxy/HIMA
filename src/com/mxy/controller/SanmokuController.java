package com.mxy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mxy.model.SanmokuData;
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
}
