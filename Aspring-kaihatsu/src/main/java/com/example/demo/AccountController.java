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

//	@Autowired
//	CategoryRepository categoryRepository;

	/**
	 * ログイン画面を表示
	 */
	@GetMapping("/")
	public String login() {
		// セッション情報はクリアする
		session.invalidate();
		return "index";
	}

	/**
	 * 新規登録画面を表示
	 */
	@GetMapping(value="/signup")
	public ModelAndView signup (ModelAndView mv) {
		mv.setViewName("signup");
		return mv;
	}


	/**
	 * ログインを実行
	 */
	@PostMapping(value="/login")
	public ModelAndView doLogin(
			@RequestParam("account_name") String account_name,
			@RequestParam("password") String password,
			ModelAndView mv
	) {
//		// アカウント名が空の場合にエラーとする
//		if(account_name == null || account_name.length() == 0) {
//			mv.addObject("message", "アカウント名を入力してください");
//			mv.setViewName("index");
//			return mv;
//		}

		//アカウント名で顧客を検索する
		List<Account> list = AccountRepository.findByAccount_name(account_name);

		//アカウント名が登録されてない場合はエラーとする
		if(list.size() == 0) {
			mv.addObject("message","入力されたアカウント名は登録されていません");
			mv.setViewName("index");
			return mv;
			}
		Account accountInfo =list.get(0);

		//パスワードが空の場合にエラーとする
//		if(password == null || password.length() == 0) {
//		mv.addObject("message", "パスワード名を入力してください");
//		mv.setViewName("index");
//		return mv;
//	}

		// セッションスコープにログイン名とカテゴリ情報を格納する
		session.setAttribute("accountInfo", accountInfo);
//		session.setAttribute("categories", categoryRepository.findAll());
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
