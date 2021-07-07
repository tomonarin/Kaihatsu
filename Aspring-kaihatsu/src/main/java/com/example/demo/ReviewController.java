package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	GenreRepository genreR;

	//全レビュー表示（ログイン時、自分の過去投稿）
    @GetMapping(value="/review")
	public ModelAndView reviews(ModelAndView mv) {
    	List<Review> reviewList = reviewR.findAll();
    	mv.addObject("reviews", reviewList);

    	mv.setViewName("list");
    	return mv;
    }

	//映画のレビュー表示
    @GetMapping(value="/review/movie")
	public ModelAndView movieReviews(ModelAndView mv) {
    	List<Review> reviewList = reviewR.findByCategoryCode(1);
    	mv.addObject("reviews", reviewList);

		mv.setViewName("list");

    	return mv;
    }

    //本のレビュー表示
    @GetMapping(value="/review/book")
	public ModelAndView bookReviews(ModelAndView mv) {
    	List<Review> reviewList = reviewR.findByCategoryCode(2);
    	mv.addObject("reviews", reviewList);

		mv.setViewName("list");
    	return mv;
    }
}
