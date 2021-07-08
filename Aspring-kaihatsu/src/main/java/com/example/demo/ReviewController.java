package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController {
	@Autowired
	HttpSession session;

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	GenreRepository genreR;

	//全レビュー表示（ログイン時、自分の過去投稿）
	@GetMapping(value = "/review")
	public ModelAndView reviews(ModelAndView mv) {
		List<Review> reviewList = reviewR.findAll();
		mv.addObject("reviews", reviewList);

		mv.setViewName("list");
		return mv;
	}

	//映画のレビュー表示
	@GetMapping(value = "/review/movie")
	public ModelAndView movieReviews(ModelAndView mv) {
		List<Review> reviewList = reviewR.findByCategory("映画");
		mv.addObject("reviews", reviewList);

		mv.setViewName("list");

		return mv;
	}

	//本のレビュー表示
	@GetMapping(value = "/review/book")
	public ModelAndView bookReviews(ModelAndView mv) {
		List<Review> reviewList = reviewR.findByCategory("書籍");
		mv.addObject("reviews", reviewList);

		mv.setViewName("list");
		return mv;
	}

	//新規レビュー投稿
	@GetMapping(value = "/newreview")
	public String newReview() {
		return "review";
	}

	@PostMapping(value = "/newreview")
	public ModelAndView Review(
			@RequestParam("name") String name,
			@RequestParam("category") String category,
			@RequestParam(name = "genre", defaultValue = "0") int genre,
			@RequestParam("director") String director,
			@RequestParam("review") String review,
			@RequestParam(name = "withspoil", defaultValue = "0") int spoil,
			ModelAndView mv) {

		//未入力チェック
		if (name.equals("") || category.equals("") || genre == 0 || review.equals("")) {
			mv.addObject("error", "未入力の項目があります。");
			mv.setViewName("review");
			return mv;
		}


		//アカウント情報の取り出し
		Account account = (Account) session.getAttribute("accountInfo");
		int accountCode = account.getCode();

		//登録するreviewエンティティのインスタンスを生成
		Review record = new Review(category, genre, name, director, spoil, review, accountCode);

		//recordエンティティをreviewテーブルに登録
		reviewR.saveAndFlush(record);

		mv.addObject("message", "レビューのが完了しました。");

		return reviews(mv);
	}

	//検索機能
	@GetMapping(value="/search")
	public String search() {
		return "search";
	}

	@PostMapping(value="/search")
	public ModelAndView doSearch(
			@RequestParam("name") String name,
			@RequestParam("category") String category,
			@RequestParam(name = "genre", defaultValue = "0") int genre,
			@RequestParam("director") String director,
			@RequestParam("account") String account,
			@RequestParam(name = "withspoil", defaultValue = "0") int spoil,
			ModelAndView mv
			) {

		return mv;

	}

}
