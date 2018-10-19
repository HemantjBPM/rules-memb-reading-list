package com.liferay.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liferay.services.RulesService;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	RulesService ruleService;

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		logger.info("Application Loaded.");
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("Initializing rules, Please wait....");
				//ruleService.update();
				logger.info("Rules initialzied at the start up.");
			}
		}).start();
		
		return new ModelAndView("home");
	}
}
