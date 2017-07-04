package com.preston.argiope.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.preston.argiope.app.constant.WebConstants;
import com.preston.argiope.app.constant.WebConstants.RequestMappings.Secured;

@Controller
public class HomeController extends AbstractController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(path= {"/", WebConstants.RequestMappings.APP_ROOT_PATH})
	public ModelAndView rootUrlAnyRequest() {
		return super.redirctToHomePage();
	}
	
	@RequestMapping(Secured.Pages.HOME)
	public ModelAndView homePage() {
		return super.forwardToHomePage();
	}
}
