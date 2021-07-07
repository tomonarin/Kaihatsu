package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	GenreRepository genreR;

	//全レビュー表示（ログイン時、自分の過去投稿）
    //@RequestMapping(value="/review")
	public ModelAndView reviews(ModelAndView mv) {

    	return mv;
    }
}
