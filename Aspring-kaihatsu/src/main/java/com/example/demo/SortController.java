package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SortController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountR;

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	CommentRepository commentR;

	@Autowired
	GenreRepository genreR;

	/**
	 * コメント画面を表示
	 */
	@PostMapping(value="/sort/all")
	public ModelAndView sortAll(
			@RequestParam(name="sort") String sort,
			ModelAndView mv) {

		List<Review> reviewList = new ArrayList<Review>();

		 if(sort.equals("genre")) {
			reviewList = reviewR.findAll(Sort.by(Sort.Direction.ASC, "genre"));
		}else if(sort.equals("star1")){
			reviewList = reviewR.findAll(Sort.by(Sort.Direction.DESC, "star"));
		}else if(sort.equals("star2")) {
			reviewList = reviewR.findAll(Sort.by(Sort.Direction.ASC, "star"));
		}else if(sort.equals("latest")){
			reviewList = reviewR.findAll(Sort.by(Sort.Direction.DESC, "date"));
		}else if(sort.equals("oldest")){
			reviewList = reviewR.findAll(Sort.by(Sort.Direction.ASC, "date"));
		}
		 List<Genre> genreList = genreR.findAll(Sort.by(Sort.Direction.ASC, "code"));
			List<Account> accountList = accountR.findAll(Sort.by(Sort.Direction.ASC, "code"));
			List<String> genreNames = new ArrayList<String>();
			List<String> accountNames = new ArrayList<String>();

			//ジャンルの名前が格納されたリスト生成
			genreNames.add("");
			for (Genre genres : genreList) {
				String gName = genres.getName();
				genreNames.add(gName);
			}
			mv.addObject("genres", genreNames);

			//アカウント名が格納されたリスト生成
			accountNames.add("");
			for (Account accounts : accountList) {
				String aName = accounts.getAccountName();
				accountNames.add(aName);
			}
			mv.addObject("names", accountNames);

		mv.addObject("reviews", reviewList);
		mv.addObject("sort", sort);
		if(session.getAttribute("accountInfo") != null) {
			mv.setViewName("list");
		}else {
			mv.setViewName("toppage");
		}
		return mv;
	}


		@PostMapping(value="/sort/movie")
		public ModelAndView sortMovie(
				@RequestParam(name="sort") String sort,
				ModelAndView mv) {
			List<Review> reviewList = new ArrayList<Review>();

			 if(sort.equals("genre")) {
					reviewList = reviewR.findByCategoryOrderByGenre("映画");
				}else if(sort.equals("star1")){
					reviewList = reviewR.findByCategoryOrderByStarDesc("映画");
				}else if(sort.equals("star2")) {
					reviewList = reviewR.findByCategoryOrderByStar("映画");
				}else if(sort.equals("latest")){
					reviewList = reviewR. findByCategoryOrderByDateDesc("映画");
				}else if(sort.equals("oldest")){
					reviewList = reviewR.findByCategoryOrderByDate("映画");;
				}
			 List<Genre> genreList = genreR.findAll(Sort.by(Sort.Direction.ASC, "code"));
				List<Account> accountList = accountR.findAll(Sort.by(Sort.Direction.ASC, "code"));
				List<String> genreNames = new ArrayList<String>();
				List<String> accountNames = new ArrayList<String>();

				//ジャンルの名前が格納されたリスト生成
				genreNames.add("");
				for (Genre genres : genreList) {
					String gName = genres.getName();
					genreNames.add(gName);
				}
				mv.addObject("genres", genreNames);

				//アカウント名が格納されたリスト生成
				accountNames.add("");
				for (Account accounts : accountList) {
					String aName = accounts.getAccountName();
					accountNames.add(aName);
				}
				mv.addObject("names", accountNames);

			mv.addObject("reviews", reviewList);
			mv.addObject("sort", sort);

			if(session.getAttribute("accountInfo") != null) {
				mv.setViewName("list");
			}else {
				mv.setViewName("toppage");
			}
			return mv;
		}


			@PostMapping(value="/sort/book")
			public ModelAndView sortBook(
					@RequestParam(name="sort") String sort,
					ModelAndView mv) {
				List<Review> reviewList = new ArrayList<Review>();
				 if(sort.equals("genre")) {
						reviewList = reviewR.findByCategoryOrderByGenre("書籍");
					}else if(sort.equals("star1")){
						reviewList = reviewR.findByCategoryOrderByStarDesc("書籍");
					}else if(sort.equals("star2")) {
						reviewList = reviewR.findByCategoryOrderByStar("書籍");
					}else if(sort.equals("latest")){
						reviewList = reviewR. findByCategoryOrderByDateDesc("書籍");
					}else if(sort.equals("oldest")){
						reviewList = reviewR.findByCategoryOrderByDate("書籍");;
					}

			 List<Genre> genreList = genreR.findAll(Sort.by(Sort.Direction.ASC, "code"));
				List<Account> accountList = accountR.findAll(Sort.by(Sort.Direction.ASC, "code"));
				List<String> genreNames = new ArrayList<String>();
				List<String> accountNames = new ArrayList<String>();

				//ジャンルの名前が格納されたリスト生成
				genreNames.add("");
				for (Genre genres : genreList) {
					String gName = genres.getName();
					genreNames.add(gName);
				}
				mv.addObject("genres", genreNames);

				//アカウント名が格納されたリスト生成
				accountNames.add("");
				for (Account accounts : accountList) {
					String aName = accounts.getAccountName();
					accountNames.add(aName);
				}
				mv.addObject("names", accountNames);

			mv.addObject("reviews", reviewList);
			mv.addObject("sort", sort);

			if(session.getAttribute("accountInfo") != null) {
				mv.setViewName("list");
			}else {
				mv.setViewName("toppage");
			}
				return mv;
			}


}
