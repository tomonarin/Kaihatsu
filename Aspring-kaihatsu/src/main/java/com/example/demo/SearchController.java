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
public class SearchController {
	@Autowired
	HttpSession session;

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	GenreRepository genreR;

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

			List<Review> reviewList;


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

			} else if (name.equals("") && category.equals("") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {//アカウント名のみ指定
					//listの０号室を取り出して、そのコード番号をゲットする

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && category.equals("") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && category.equals("") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && !category.equals("") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && !category.equals("") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && !category.equals("") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (name.equals("") && !category.equals("") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && category.equals("") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			} else if (!name.equals("") && !category.equals("") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 0) {

				} else if (genre == 0 && spoil != 0) {

				} else if (genre != 0 && spoil == 0) {

				} else if (genre != 0 && spoil != 0) {

				}

			}






			return mv;

		}

}
