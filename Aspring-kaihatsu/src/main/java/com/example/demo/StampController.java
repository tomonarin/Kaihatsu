package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StampController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository AccountR;

	@Autowired
	GenreRepository GenreR;

	@Autowired
	ReviewRepository reviewR;

}
