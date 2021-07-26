package com.example.demo;

import java.util.ArrayList;
import java.util.List;

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
public class SystemController {
	@Autowired
	HttpSession session;

	@Autowired
	SystemRepository SystemRepository;

	@Autowired
	AccountRepository AccountRepository;

	@Autowired
	GenreRepository GenreRepository;

	@Autowired
	ReviewRepository reviewR;

	@Autowired
	ProfileRepository profileR;

	/**
	 * ログイン画面を表示
	 */

	//http://localhost:8080/system
	@GetMapping("/system")
	public String systemLogin() {
		return "systemLogin";
	}

	/**
	 * ログインを実行
	 */
	@PostMapping(value = "/systemLogin")
	public ModelAndView doSystemLogin(
			@RequestParam("systemId") String systemId,
			@RequestParam("password") String password,
			ModelAndView mv) {
		// アカウント名が空の場合にエラーとする
		if (systemId.equals("") || password.equals("")) {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("/systemLogin");
			return mv;
		}

		//アカウント名で顧客を検索する
		List<System> list = SystemRepository.findBySystemId(systemId);

		//アカウント名が登録されてない場合はエラーとする
		if (list.size() == 0) {
			mv.addObject("message", "入力されたアカウント名は登録されていません");
			mv.setViewName("systemLogin");
			return mv;
		}

		System systemInfo = list.get(0);
		//アカウント名とパスワードが一致するか確認する
		if (systemId.equals(systemInfo.getSystemId()) && password.equals(systemInfo.getPassword())) {
			//セッションスコープにログイン名とジャンル情報を格納する
			session.setAttribute("systemInfo", systemInfo);

			//Thymeleafで表示する準備
			mv.addObject("systemInfo", systemInfo);
			mv.setViewName("kanritop");
			return mv;

		} else {
			mv.addObject("message", "アカウント名とパスワードが一致しません");
			mv.setViewName("systemLogin");
			return mv;
		}

	}

	/**
	 * 管理者ページトップへ
	 */
	@GetMapping("/kanritop")
	public ModelAndView kanriTop(ModelAndView mv) {
		System systemInfo = (System) session.getAttribute("systemInfo");
		mv.addObject("systemInfo", systemInfo);
		return mv;
	}

	//http://localhost:8080/kanri/review
	@GetMapping("/kanri/review")
	public ModelAndView kanriReview(ModelAndView mv) {
		session.setAttribute("genre", GenreRepository.findAll());
		System systemInfo = (System) session.getAttribute("systemInfo");

		//データベースから情報取得
		List<Review> reviewList = reviewR.findAll();
		List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<Account> accountList = AccountRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> genreNames = new ArrayList<String>();
		List<String> accountNames = new ArrayList<String>();

		//ジャンルの名前が格納されたリスト生成
		genreNames.add("");
		for (Genre genres : genreList) {
			String gName = genres.getName();
			genreNames.add(gName);
		}
		accountNames.add("");
		for (Account accounts : accountList) {
			String aName = accounts.getAccountName();
			accountNames.add(aName);
		}

		//Thymeleafで表示する準備
		mv.addObject("systemInfo", systemInfo);
		mv.addObject("genres", genreNames);
		mv.addObject("accounts",accountList);
		mv.addObject("names", accountNames);
		mv.addObject("reviews", reviewList);
		mv.setViewName("kanrireview");
		return mv;
	}

	/**
	 * ログアウトを実行
	 */
	@RequestMapping("/systemLogout")
	public String systemLogout() {
		// トップページ画面表示処理を実行するだけ
		return systemLogin();
	}

}
