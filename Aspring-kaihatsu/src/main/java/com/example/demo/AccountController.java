package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@Autowired
	ProfileRepository profileR;

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
	@RequestMapping(value = "/signup")
	public ModelAndView signup(ModelAndView mv) {
		mv.setViewName("signup");
		return mv;
	}

	/**
	 * 新規登録を実行
	 */
	@PostMapping(value = "/signup")
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
		LocalDate date = LocalDate.now();
		//登録するAccountエンティティのインスタンスを生成
		Account account = new Account(name, account_name, email, password, photo, login, date);

		//AccountエンティティをAccountテーブルに登録
		AccountRepository.saveAndFlush(account);

		//Profileテーブルに新しいデータ列を登録
		int aCode = account.getCode();
		Profile p = new Profile(aCode, "", "", "");
		profileR.saveAndFlush(p);

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

			//ジャンルの名前が格納されたリスト生成
			List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
			List<String> genreNames = new ArrayList<String>();
			genreNames.add("");
			for (Genre genres : genreList) {
				String gName = genres.getName();
				genreNames.add(gName);
			}

			//アカウントプロフィール取得

			//アカウントプロフィールの取得
			Optional<Profile> p = profileR.findById(aCode);
			Profile profile = p.get();

			mv.addObject("genres", genreNames);
			mv.addObject("reviews", reviewList);
			mv.addObject("accountInfo", accountInfo);
			mv.addObject("profile", profile);
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
		Account a = (Account) session.getAttribute("accountInfo");
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

		//アカウントプロフィールの取得
		Optional<Profile> p = profileR.findById(code);
		Profile profile = p.get();

		mv.addObject("profile", profile);

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
			@RequestParam("comment") String comment,
			@RequestParam("favorite") String favorite,
			@RequestParam("mybest") String mybest,
			ModelAndView mv) {

		Account a = (Account) session.getAttribute("accountInfo");
		int login = a.getLogin();
		LocalDate date = a.getDate();

		Account account = new Account(code, name, accountName, email, password, photo, login, date);
		AccountRepository.saveAndFlush(account);

		Profile p = new Profile(code, comment, favorite, mybest);
		profileR.saveAndFlush(p);

		//ジャンルの名前が格納されたリスト生成
		List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> genreNames = new ArrayList<String>();
		genreNames.add("");
		for (Genre genres : genreList) {
			String gName = genres.getName();
			genreNames.add(gName);
		}
		mv.addObject("genres", genreNames);

		session.setAttribute("accountInfo", account);
		mv.addObject("accountInfo", account);
		mv.addObject("profile", p);

		mv.setViewName("top");
		return mv;
	}

	/**
	 * マイページへ
	 */
	//http://localhost:8080/top
	@GetMapping("/top")
	public ModelAndView top(ModelAndView mv) {
		Account accountInfo = (Account) session.getAttribute("accountInfo");

		//ログインしたアカウントのレビュー情報のみ取得
		//アカウントコードを取得
		int aCode = accountInfo.getCode();
		List<Review> reviewList = reviewR.findByAccount(aCode);

		//ジャンル情報取得、リスト生成してhtmlへ
		List<Genre> genreList = GenreRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> genreNames = new ArrayList<String>();

		//ジャンルの名前が格納されたリスト生成
		genreNames.add("");
		for (Genre genres : genreList) {
			String gName = genres.getName();
			genreNames.add(gName);
		}

		//アカウントプロフィールの取得
		Optional<Profile> p = profileR.findById(aCode);
		Profile profile = p.get();

		//Thymeleafで表示する準備
		mv.addObject("accountInfo", accountInfo);
		mv.addObject("genres", genreNames);
		mv.addObject("reviews", reviewList);
		mv.addObject("profile", profile);
		mv.setViewName("top");
		return mv;
	}

	/**
	 * レビュー投稿者アカウント情報の詳細表示
	 */
	@RequestMapping("/account/{aCode}")
	public ModelAndView accountDetail(
			@PathVariable(name = "aCode") int aCode,
			ModelAndView mv) {
		Optional<Account> accountInfo = AccountRepository.findById(aCode);
		Account a = accountInfo.get();

		Optional<Profile> profile = profileR.findById(aCode);
		Profile p = profile.get();

		mv.addObject("accountInfo", a);
		mv.addObject("profile", p);

		mv.setViewName("accountDetail");
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
