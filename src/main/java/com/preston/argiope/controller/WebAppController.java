package com.preston.argiope.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebAppController {
	public static final String VIEW_INDEX = "index";
	
	public static final String ATT_MESSAGE = "message";
	
	@RequestMapping
	public ModelAndView index(ModelMap model) {
		model.addAttribute(ATT_MESSAGE, "Hello Argiope!");
		
		return new ModelAndView(VIEW_INDEX);
	}
}
