package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	@Autowired
	HttpSession session;

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	GenreRepository genreR;

	@Autowired
	AccountRepository accountR;

	//検索機能
		@GetMapping(value = "/search")
		public String search() {
			return "search";
		}

		@PostMapping(value = "/search")
		public ModelAndView doSearch(
				@RequestParam("name") String name,
				@RequestParam("category") String category,
				@RequestParam(name = "genre", defaultValue = "0") int genre,
				@RequestParam("director") String director,
				@RequestParam("account") String account,
				@RequestParam(name="withspoil", defaultValue="0") int spoil,
				ModelAndView mv) {

			List<Review> reviewList = new ArrayList<Review>();
			int aCode = 0;
			//アカウントlistの０号室を取り出して、そのコード番号をゲットする
			List<Account> list = accountR.findByAccountName(account);
			if(!list.isEmpty()) {
				Account accountInfo = list.get(0);
				aCode = accountInfo.getCode();
			}

			if (name.equals("") && category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//全件表示
					reviewList = reviewR.findAll();
				} else if (genre == 0 && spoil == 0) {//ネタバレなし
					reviewList = reviewR.findBySpoil(0);
				} else if (genre != 0 && spoil == 1) {//ジャンル指定のみ
					reviewList = reviewR.findByGenre(genre);
				} else if (genre != 0 && spoil == 1) {//ジャンル指定+ネタバレなし
					reviewList = reviewR.findByGenreAndSpoil(genre,1);
				}

			} else if (name.equals("") && category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//アカウント名のみ指定
					reviewList = reviewR.findByAccount(aCode);
				} else if (genre == 0 && spoil == 0) {//アカウント名+ネタバレなし
					reviewList = reviewR.findByAccountAndSpoil(aCode,spoil);
				} else if (genre != 0 && spoil == 1) {//アカウント名+ジャンル指定
					reviewList = reviewR.findByAccountAndGenre(aCode,genre);
				} else if (genre != 0 && spoil == 0) {//アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByAccountAndGenreAndSpoil(aCode,genre,spoil);
				}

			} else if (name.equals("") && category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//作者名のみ
					reviewList = reviewR.findByDirectorAndSpoil(director, spoil);
				} else if (genre == 0 && spoil == 0) {//作者名+ネタバレなし
					reviewList = reviewR.findByDirector(director);
				} else if (genre != 0 && spoil == 1) {//作者名+ジャンル指定
					reviewList = reviewR.findByDirectorAndGenre(director, genre);
				} else if (genre != 0 && spoil == 0) {////作者名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByDirectorAndGenreAndSpoil(director, genre, spoil);
				}

			} else if (name.equals("") && category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//作者+アカウント名
					reviewList = reviewR.findByDirectorAndAccount(director, aCode);
				} else if (genre == 0 && spoil == 0) {//作者+アカウント名+ネタバレなし
					reviewList = reviewR.findByDirectorAndAccountAndSpoil(director, aCode,spoil);
				} else if (genre != 0 && spoil == 0) {//作者+アカウント名+ジャンル指定
					reviewList = reviewR.findByDirectorAndAccountAndGenre(director, aCode,genre);
				} else if (genre != 0 && spoil == 1) {//作者+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByDirectorAndAccountAndGenreAndSpoil(director, aCode,genre,spoil);
				}

			} else if (name.equals("") && !category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//カテゴリーのみ
					reviewList = reviewR.findByCategory(category);
				} else if (genre == 0 && spoil == 0) {//カテゴリー+ネタバレなし
					reviewList = reviewR.findByCategoryAndSpoil(category,spoil);
				} else if (genre != 0 && spoil == 1) {//カテゴリー+ジャンル指定
					reviewList = reviewR.findByCategoryAndGenre(category,genre);
				} else if (genre != 0 && spoil == 0) {//カテゴリー+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByCategoryAndGenreAndSpoil(category,genre,spoil);
				}

			} else if (name.equals("") && !category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//カテゴリー+アカウント名
					reviewList = reviewR.findByCategoryAndAccount(category, aCode);
				} else if (genre == 0 && spoil == 0) {//カテゴリー+アカウント名+ネタバレなし
					//reviewList = reviewR.findByCategoryAndAccounAndSpoil(category, aCode, spoil);
				} else if (genre != 0 && spoil == 0) {//カテゴリー+アカウント名+ジャンル指定
					//reviewList = reviewR.findByCategoryAndAccounAndGenre(category, aCode, genre);
				} else if (genre != 0 && spoil != 0) {//カテゴリー+アカウント名+ジャンル指定+ネタバレなし
					//reviewList = reviewR.findByCategoryAndAccounAndGenreAndSpoil(category, aCode, genre, spoil);
				}

			} else if (name.equals("") && !category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && !category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			}

			mv.addObject("reviews", reviewList);
			mv.setViewName("search");


			return mv;

		}

}
