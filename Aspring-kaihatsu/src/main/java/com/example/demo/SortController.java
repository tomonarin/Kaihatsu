package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SortController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	CommentRepository commentRepository;

	/**
	 * コメント画面を表示
	 */
	@GetMapping(value="/review/sort/{}")
	public ModelAndView sortAll(
			@PathVariable(name="sort") String sort,
			ModelAndView mv) {

		if(sort.equals("category")) {

		}else if(sort.equals("genre")) {

		}else if(sort.equals("star1")){

		}else if(sort.equals("star2")) {

		}else {

		}
		//mv.addObject("review",);
		mv.setViewName("comment");
		return mv;
	}


		@GetMapping(value="/review/movie/{}")
		public ModelAndView sortMovie(
				@PathVariable(name="sort") String sort,
				ModelAndView mv) {

			if(sort.equals("category")) {

			}else if(sort.equals("genre")) {

			}else if(sort.equals("star1")){

			}else if(sort.equals("star2")) {

			}else {

			}


			//mv.addObject("review",review);
			mv.setViewName("comment");
			return mv;
		}


			@GetMapping(value="/review/book/sort/{}")
			public ModelAndView sortBook(
					@PathVariable(name="sort") String sort,
					ModelAndView mv) {
			 if(sort.equals("category")) {

				}else if(sort.equals("genre")) {

				}else if(sort.equals("star1")){

				}else if(sort.equals("star2")) {

				}else {

				}

				//mv.addObject("review",review);
				mv.setViewName("comment");
				return mv;
			}


}
