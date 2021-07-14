package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class ReplyController {
	/**
	 * 新規登録画面を表示
	 */
	@RequestMapping(value="/signup")
	public ModelAndView signup(ModelAndView mv) {
		mv.setViewName("signup");
		return mv;
	}


}
