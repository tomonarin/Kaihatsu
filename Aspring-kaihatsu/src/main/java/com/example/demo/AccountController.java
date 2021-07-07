package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
			ModelAndView mv) {
		//未入力チェック
		if (name.equals("") || account_name.equals("") || email.equals("") || password.equals("")) {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("signup");
			return mv;
		}

		//登録するAccountエンティティのインスタンスを生成
		Account account = new Account(name, account_name, email, password);

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
			mv.setViewName("index");
			return mv;
		}

		Account accountInfo = list.get(0);
		//アカウント名とパスワードが一致するか確認する
		if (account_name.equals(accountInfo.getAccountName()) && password.equals(accountInfo.getPassword())) {
			// セッションスコープにログイン名とカテゴリ情報を格納する
			session.setAttribute("accountInfo", accountInfo);
			session.setAttribute("genre", GenreRepository.findAll());

			mv.setViewName("top");
		} else {
			mv.addObject("message", "アカウント名とパスワードが一致しません");
			mv.setViewName("login");
		}
		return mv;
	}

//	/**
//	 * ログアウトを実行
//	 */
//	@RequestMapping("/logout")
//	public String logout() {
//		// ログイン画面表示処理を実行するだけ
//		return login();
//	}

}
