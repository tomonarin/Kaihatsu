package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				} else if (genre != 0 && spoil == 1) {//作者+アカウント名+ジャンル指定
					reviewList = reviewR.findByDirectorAndAccountAndGenre(director, aCode,genre);
				} else if (genre != 0 && spoil == 0) {//作者+アカウント名+ジャンル指定+ネタバレなし
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
					reviewList = reviewR.findByCategoryAndAccountAndSpoil(category, aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//カテゴリー+アカウント名+ジャンル指定
					reviewList = reviewR.findByCategoryAndAccountAndGenre(category, aCode, genre);
				} else if (genre != 0 && spoil == 0) {//カテゴリー+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByCategoryAndAccountAndGenreAndSpoil(category, aCode, genre, spoil);
				}

			} else if (name.equals("") && !category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//カテゴリー+作者名
					reviewList = reviewR.findByCategoryAndDirector(category, director);
				} else if (genre == 0 && spoil == 0) {//カテゴリー+作者名+ネタバレなし
					reviewList = reviewR.findByCategoryAndDirectorAndSpoil(category, director, spoil);
				} else if (genre != 0 && spoil == 1) {//カテゴリー+作者名+ジャンル指定
					reviewList = reviewR.findByCategoryAndDirectorAndGenre(category, director, genre);
				} else if (genre != 0 && spoil == 0) {//カテゴリー+作者名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByCategoryAndDirectorAndGenreAndSpoil(category, director, genre, spoil);
				}

			} else if (name.equals("") && !category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//カテゴリー+作者名+アカウント名
					reviewList = reviewR.findByCategoryAndDirectorAndAccount(category, director, aCode);
				} else if (genre == 0 && spoil == 0) {//カテゴリー+作者名+アカウント名+ネタバレなし
					reviewList = reviewR.findByCategoryAndDirectorAndAccountAndSpoil(category, director, aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//カテゴリー+作者名+アカウント名+ジャンル指定
					reviewList = reviewR.findByCategoryAndDirectorAndAccountAndGenre(category, director, aCode, genre);
				} else if (genre != 0 && spoil == 0) {//カテゴリー+作者名+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByCategoryAndDirectorAndAccountAndGenreAndSpoil(category, director, aCode, genre, spoil);
				}

			} else if (!name.equals("") && category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名のみ
					reviewList = reviewR.findByNameLike(name);
				} else if (genre == 0 && spoil == 0) {//作品名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndSpoil("%" + name + "%", spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndGenre("%" + name + "%", genre);
				} else if (genre != 0 && spoil == 0) {//作品名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndGenreAndSpoil("%" + name + "%", genre, spoil);
				}

			} else if (!name.equals("") && category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+アカウント
					reviewList = reviewR.findByNameLikeAndAccount("%" + name + "%", aCode);
				} else if (genre == 0 && spoil == 0) {//作品名+アカウント+ネタバレなし
					reviewList = reviewR.findByNameLikeAndAccountAndSpoil("%" + name + "%", aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+アカウント+ジャンル指定
					reviewList = reviewR.findByNameLikeAndAccountAndGenre("%" + name + "%", aCode, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+アカウント+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndAccountAndGenreAndSpoil("%" + name + "%", aCode, genre, spoil);
				}

			} else if (!name.equals("") && category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+作者名
					reviewList = reviewR.findByNameLikeAndDirector("%" + name + "%", director);
				} else if (genre == 0 && spoil == 0) {//作品名+作者名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndDirectorAndSpoil("%" + name + "%", director, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+作者名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndDirectorAndGenre("%" + name + "%", director, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+作者名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndDirectorAndGenreAndSpoil("%" + name + "%", director, genre, spoil);
				}

			} else if (!name.equals("") && category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+作者名+アカウント名
					reviewList = reviewR.findByNameLikeAndDirectorAndAccount("%" + name + "%", director, aCode);
				} else if (genre == 0 && spoil == 0) {//作品名+作者名+アカウント名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndDirectorAndAccountAndSpoil("%" + name + "%", director, aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+作者名+アカウント名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndDirectorAndAccountAndGenre("%" + name + "%", director, aCode, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+作者名+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndDirectorAndAccountAndGenreAndSpoil("%" + name + "%", director, aCode, genre, spoil);
				}

			} else if (!name.equals("") && !category.equals("すべて") && director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+カテゴリー指定
					reviewList = reviewR.findByNameLikeAndCategory("%" + name + "%", category);
				} else if (genre == 0 && spoil == 0) {//作品名+カテゴリー指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndSpoil("%" + name + "%", category, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+カテゴリー指定+ジャンル指定
					reviewList = reviewR.findByNameLikeAndCategoryAndGenre("%" + name + "%", category, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+カテゴリー指定+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndGenreAndSpoil("%" + name + "%", category, genre, spoil);
				}

			} else if (!name.equals("") && !category.equals("すべて") && director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+カテゴリー指定+アカウント名
					reviewList = reviewR.findByNameLikeAndCategoryAndAccount("%" + name + "%", category, aCode);
				} else if (genre == 0 && spoil == 0) {//作品名+カテゴリー指定+アカウント名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndAccountAndSpoil("%" + name + "%", category, aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+カテゴリー指定+アカウント名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndCategoryAndAccountAndGenre("%" + name + "%", category, aCode, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+カテゴリー指定+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndAccountAndGenreAndSpoil("%" + name + "%", category, aCode, genre, spoil);
				}

			} else if (!name.equals("") && !category.equals("すべて") && !director.equals("") && account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+カテゴリー指定+作者名
					reviewList = reviewR.findByNameLikeAndCategoryAndDirector("%" + name + "%", category, director);
				} else if (genre == 0 && spoil == 0) {//作品名+カテゴリー指定+作者名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndSpoil("%" + name + "%", category, director, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+カテゴリー指定+作者名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndGenre("%" + name + "%", category, director, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+カテゴリー指定+作者名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndGenreAndSpoil("%" + name + "%", category, director, genre, spoil);
				}

			} else if (!name.equals("") && !category.equals("すべて") && !director.equals("") && !account.equals("")) {
				if (genre == 0 && spoil == 1) {//作品名+カテゴリー指定+作者名+アカウント名
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndAccount("%" + name + "%", category, director, aCode);
				} else if (genre == 0 && spoil == 0) {//作品名+カテゴリー指定+作者名+アカウント名+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndAccountAndSpoil("%" + name + "%", category, director, aCode, spoil);
				} else if (genre != 0 && spoil == 1) {//作品名+カテゴリー指定+作者名+アカウント名+ジャンル指定
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndAccountAndGenre("%" + name + "%", category, director, aCode, genre);
				} else if (genre != 0 && spoil == 0) {//作品名+カテゴリー指定+作者名+アカウント名+ジャンル指定+ネタバレなし
					reviewList = reviewR.findByNameLikeAndCategoryAndDirectorAndAccountAndGenreAndSpoil("%" + name + "%", category, director, aCode, genre, spoil);
				}

			}

			if(reviewList.size() == 0){
				mv.addObject("message", "検索条件に一致するレビューはありません");

			}else {
			mv.addObject("name", name);
			mv.addObject("category", category);
			mv.addObject("director", director);
			mv.addObject("account", account);
			mv.addObject("genre", genre);
			mv.addObject("spoil", spoil);
			mv.addObject("reviews", reviewList);
			List<Genre> genreList = genreR.findAll();
			List<Account> accountList = accountR.findAll();
			List<String> genreNames = new ArrayList<String>();
			List<String> accountNames = new ArrayList<String>();

			//ジャンルの名前が格納されたリスト生成
			genreNames.add("");
			for (Genre genres : genreList) {
				int gCode = genres.getCode();
				Optional<Genre> genreRecord = genreR.findById(gCode);
				Genre record = genreRecord.get();
				String gName = record.getName();
				genreNames.add(gName);
			}
			mv.addObject("allgenres", genreNames);

			//アカウント名が格納されたリスト生成
			accountNames.add("");
			for (Account accounts : accountList) {
				int accountCode = accounts.getCode();
				Optional<Account> accountRecord = accountR.findById(accountCode);
				Account record = accountRecord.get();
				String aName = record.getAccountName();
				accountNames.add(aName);
			}
			mv.addObject("names", accountNames);
			}

			mv.setViewName("search");

			return mv;

		}

}
