package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@Autowired
	AccountRepository accountR;

	@Autowired
	ProfileRepository profileR;

	//全レビュー表示（ログイン時、自分の過去投稿）
	@GetMapping(value = "/review")
	public ModelAndView reviews(ModelAndView mv) {
		//データベースから情報取得
		List<Review> reviewList = reviewR.findAll();
		mv.addObject("reviews", reviewList);
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
		session.setAttribute("category", "all");

		session.setAttribute("title", "全てのレビュー");

		session.setAttribute("ref", "/review");

		mv.setViewName("list");
		return mv;
	}

	//映画のレビュー表示
	@GetMapping(value = "/review/movie")
	public ModelAndView movieReviews(ModelAndView mv) {
		//データベースから情報取得
		List<Review> reviewList = reviewR.findByCategory("映画");
		mv.addObject("reviews", reviewList);
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

		session.setAttribute("category", "movie");

		session.setAttribute("title", "映画レビュー");

		session.setAttribute("ref", "/review/movie");


		mv.setViewName("list");

		return mv;
	}

	//本のレビュー表示
	@GetMapping(value = "/review/book")
	public ModelAndView bookReviews(ModelAndView mv) {
		List<Review> reviewList = reviewR.findByCategory("書籍");
		mv.addObject("reviews", reviewList);
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

		session.setAttribute("category", "book");

		session.setAttribute("title", "書籍レビュー");

		session.setAttribute("ref", "/review/book");


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
			@RequestParam(name = "star", defaultValue = "0") int star,
			@RequestParam(name = "withspoil", defaultValue = "0") int spoil,
			ModelAndView mv) {

		//未入力チェック
		if (name.equals("") || category.equals("") || genre == 0 || star == 0 || review.equals("")) {
			mv.addObject("error", "未入力の項目があります。");
			mv.setViewName("review");
			return mv;
		}

		//アカウント情報の取り出し
		Account account = (Account) session.getAttribute("accountInfo");
		int accountCode = account.getCode();

		//登録するreviewエンティティのインスタンスを生成
		Review record = new Review(category, genre, name, director, star, spoil, review, accountCode);

		//recordエンティティをreviewテーブルに登録
		reviewR.saveAndFlush(record);

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


		//スタンプ数によってクラス分け
		int stamp = account.getLogin();
		if (stamp < 10) {
			mv.addObject("rank", "rank1");
		} else if (stamp < 20 && stamp >= 10) {
			mv.addObject("rank", "rank2");
		} else {
			mv.addObject("rank", "rank3");
		}

		//ログイン者のレビュー一覧
		List<Review> reviewList = reviewR.findByAccount(accountCode);
		mv.addObject("reviews", reviewList);

		Optional<Profile> p = profileR.findById(accountCode);
		Profile profile = p.get();
		mv.addObject("profile", profile);

		mv.addObject("message", "レビューの投稿が完了しました。");

		mv.setViewName("top");
		return mv;
	}

	//レビューの更新
	@RequestMapping(value = "/review/update")
	public ModelAndView reviewUpdate(
			@RequestParam("code") int code,
			ModelAndView mv) {
		Review review = reviewR.findById(code).get();

		mv.addObject("review", review);
		mv.setViewName("update");

		return mv;
	}

	@PostMapping(value = "/review/update")
	public ModelAndView ReviewUpdate(
			@RequestParam("code") int code,
			@RequestParam("name") String name,
			@RequestParam("category") String category,
			@RequestParam(name = "genre", defaultValue = "0") int genre,
			@RequestParam("director") String director,
			@RequestParam("review") String review,
			@RequestParam(name = "star", defaultValue = "0") int star,
			@RequestParam(name = "withspoil", defaultValue = "0") int spoil,
			ModelAndView mv) {

		//未入力チェック
		if (name.equals("") || category.equals("") || genre == 0 || star == 0 || review.equals("")) {
			mv.addObject("error", "未入力の項目があります。");
			mv.setViewName("review");
			return mv;
		}

		//アカウント情報の取り出し
		Account account = (Account) session.getAttribute("accountInfo");
		int accountCode = account.getCode();

		//登録するreviewエンティティのインスタンスを生成
		Review record = new Review(code, category, genre, name, director, star, spoil, review, accountCode);

		//recordエンティティをreviewテーブルに登録
		reviewR.saveAndFlush(record);

		//ログインしたアカウントのレビュー情報のみ取得
		//アカウントコードを取得
		Account accountInfo = (Account) session.getAttribute("accountInfo");
		int aCode = accountInfo.getCode();
		List<Review> reviewList = reviewR.findByAccount(aCode);
		mv.addObject("reviews", reviewList);

		//ジャンルの名前が格納されたリスト生成
		List<Genre> genreList = genreR.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> genreNames = new ArrayList<String>();
		genreNames.add("");
		for (Genre genres : genreList) {
			String gName = genres.getName();
			genreNames.add(gName);
		}

		//アカウントプロフィールの取得
		Optional<Profile> p = profileR.findById(aCode);
		Profile profile = p.get();

		//スタンプ数によってクラス分け
		int stamp = account.getLogin();
		if (stamp < 10) {
			mv.addObject("rank", "rank1");
		} else if (stamp < 20 && stamp >= 10) {
			mv.addObject("rank", "rank2");
		} else {
			mv.addObject("rank", "rank3");
		}

		mv.addObject("genres", genreNames);
		mv.addObject("message", "レビューの更新が完了しました。");
		mv.addObject("profile", profile);

		mv.setViewName("top");

		return mv;
	}

	//投稿の削除
	@PostMapping("/delete")
	public ModelAndView deleteCart(
			@RequestParam("code") int code,
			ModelAndView mv) {
		//レビューを削除
		reviewR.deleteById(code);

		//ログインしたアカウントのレビュー情報のみ取得
		//アカウントコードを取得
		Account accountInfo = (Account) session.getAttribute("accountInfo");
		int aCode = accountInfo.getCode();

		List<Review> reviewList = reviewR.findByAccount(aCode);

		List<Genre> genreList = genreR.findAll();

		List<String> genreNames = new ArrayList<String>();

		//アカウントプロフィールの取得
		Optional<Profile> p = profileR.findById(aCode);
		Profile profile = p.get();

		//ジャンルの名前が格納されたリスト生成
		genreNames.add("");
		for (Genre genres : genreList) {
			String gName = genres.getName();
			genreNames.add(gName);
		}
		mv.addObject("profile", profile);
		mv.addObject("genres", genreNames);
		mv.addObject("reviews", reviewList);

		mv.setViewName("top");
		return mv;
	}

}
