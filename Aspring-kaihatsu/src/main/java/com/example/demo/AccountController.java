package com.example.demo;

import java.time.LocalDateTime;
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
public class AccountController {
	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository AccountRepository;

	@Autowired
	GenreRepository GenreRepository;

	@Autowired
	ReviewRepository reviewR;

	/**
	 * ログイン画面を表示
	 */

	//http://localhost:8080/
	@GetMapping("/")
	public String login() {
		// セッション情報はクリアする
		session.invalidate();
		return "login";
	}

	/**
	 * 新規登録画面を表示
	 */
	@RequestMapping(value="/signup")
	public ModelAndView signup(ModelAndView mv) {
		mv.setViewName("signup");
		return mv;
	}

	/**
	 * 新規登録を実行
	 */
	@PostMapping(value="/signup")
	public ModelAndView doSignup(
			@RequestParam("name") String name,
			@RequestParam("account_name") String account_name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("photo") String photo,
			ModelAndView mv) {
		//未入力チェック
		if (name.equals("") || account_name.equals("") || email.equals("") || password.equals("")) {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("signup");
			return mv;
		}
		//システム完成したら消します
//		String photo ="css/images/icon_woman1.png";
		Integer login = 1;
		LocalDateTime date = LocalDateTime.now();

		//登録するAccountエンティティのインスタンスを生成
		Account account = new Account(name, account_name, email, password,photo,login,date);

		//AccountエンティティをAccountテーブルに登録
		AccountRepository.saveAndFlush(account);

		mv.addObject("result", "登録が完了しました。");

		mv.setViewName("login");
		return mv;

	}

	/**
	 * ログインを実行
	 */
	@PostMapping(value = "/login")
	public ModelAndView doLogin(
			@RequestParam("account_name") String account_name,
			@RequestParam("password") String password,
			ModelAndView mv) {
		// アカウント名が空の場合にエラーとする
		if (account_name.equals("") || password.equals("")) {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("login");
			return mv;
		}

		//アカウント名で顧客を検索する
		List<Account> list = AccountRepository.findByAccountName(account_name);

		//アカウント名が登録されてない場合はエラーとする
		if (list.size() == 0) {
			mv.addObject("message", "入力されたアカウント名は登録されていません");
			mv.setViewName("login");
			return mv;
		}

		Account accountInfo = list.get(0);
		//アカウント名とパスワードが一致するか確認する
		if (account_name.equals(accountInfo.getAccountName()) && password.equals(accountInfo.getPassword())) {
			//セッションスコープにログイン名とジャンル情報を格納する
			session.setAttribute("accountInfo", accountInfo);
			session.setAttribute("genre", GenreRepository.findAll());

			//ログインしたアカウントのレビュー情報のみ取得
			//アカウントコードを取得
			int aCode = accountInfo.getCode();
			List<Review> reviewList = reviewR.findByAccount(aCode);

			//ジャンル情報取得、リスト生成してhtmlへ
			List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
			List<String> genreNames = new ArrayList<String>();
			genreNames.add("");

			for (Genre genres : genreList) {
				 int gCode = genres.getCode();
				 Optional<Genre> genreRecord =GenreRepository.findById(gCode);
				 Genre record = genreRecord.get();
				 String gName = record.getName();
				 genreNames.add(gName);
			}
			mv.addObject("genres", genreNames);

			mv.addObject("reviews", reviewList);

			mv.addObject("accountInfo",accountInfo);
			mv.setViewName("top");
		} else {
			mv.addObject("message", "アカウント名とパスワードが一致しません");
			mv.setViewName("login");
		}
		return mv;
	}


	/**
	 * アカウント情報更新ページへ
	 */

	//http://localhost:8080/editAccount
		@GetMapping("/editAccount")
		public ModelAndView edit(ModelAndView mv) {
			Account a = (Account)session.getAttribute("accountInfo");
			int code = a.getCode();

			Account account = null;
			//Userテーブルから指定のレコードを取得
			Optional<Account> record = AccountRepository.findById(code);

			if (record.isEmpty() == false) {//レコードがあれば
				account = record.get(); //レコードを取得する

			}

			//Thymeleafで表示する準備
			mv.addObject("code", code);
			mv.addObject("name", account.getName());
			mv.addObject("accountName", account.getAccountName());
			mv.addObject("email", account.getEmail());
			mv.addObject("password", account.getPassword());
			mv.addObject("photo", account.getPhoto());


			mv.setViewName("/editAccount");
			return mv;
		}

	/**
	 * アカウント情報更新を実行
	 */
	@PostMapping("/editAccount")
	public ModelAndView editAccount(
			@RequestParam("code") int code,
			@RequestParam("name") String name,
			@RequestParam("accountName") String accountName,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("photo") String photo,
			ModelAndView mv) {
		//システム完成したら消します
//		String photo ="css/images/icon_woman1.png";
		Account a = (Account)session.getAttribute("accountInfo");
		int login = a.getLogin();
		LocalDateTime date = a.getDate();


		Account account = new Account(code, name,accountName, email, password,photo,login,date);
		AccountRepository.saveAndFlush(account);

		session.setAttribute("accountInfo", account);
		mv.addObject("accountInfo",account);
		mv.setViewName("top");
		return mv;
	}

	/**
	 * マイページへ
	 */
	//http://localhost:8080/top
	@GetMapping("/top")
	public ModelAndView top(ModelAndView mv) {
		Account accountInfo = (Account)session.getAttribute("accountInfo");

		//ログインしたアカウントのレビュー情報のみ取得
		//アカウントコードを取得
		int aCode = accountInfo.getCode();
		List<Review> reviewList = reviewR.findByAccount(aCode);

		//ジャンル情報取得、リスト生成してhtmlへ
		List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> genreNames = new ArrayList<String>();
		genreNames.add("");

		for (Genre genres : genreList) {
			 int gCode = genres.getCode();
			 Optional<Genre> genreRecord =GenreRepository.findById(gCode);
			 Genre record = genreRecord.get();
			 String gName = record.getName();
			 genreNames.add(gName);
		}

		//Thymeleafで表示する準備
		mv.addObject("accountInfo", accountInfo);
		mv.addObject("genres", genreNames);
		mv.addObject("reviews", reviewList);
		mv.setViewName("top");
		return mv;
	}


	/**
	 * ログアウトを実行
	 */
	@RequestMapping("/logout")
	public String logout() {
		// ログイン画面表示処理を実行するだけ
		return login();
	}

}
