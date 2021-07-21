package com.example.demo;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StampController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository AccountR;

	@Autowired
	GenreRepository GenreR;

	@Autowired
	ReviewRepository reviewR;

	@RequestMapping(value = "/stamp")
	public ModelAndView stamp(ModelAndView mv) {
		Account a = (Account) session.getAttribute("accountInfo");
		Integer stamp = a.getLogin();

		mv.addObject("tf", false);

		mv.addObject("stamp", stamp);
		mv.setViewName("stamp");

		return mv;

	}

	@PostMapping(value = "/stamp")
	public ModelAndView stamp2(ModelAndView mv) {
		Account a = (Account) session.getAttribute("accountInfo");
		int stamp = a.getLogin();
		LocalDate lastlogin = a.getDate();
		LocalDate today = LocalDate.now();

		boolean tf = today.isAfter(lastlogin);


		if (tf == true) {
			//スタンプを押す
			stamp = stamp + 1;
			a.setLogin(stamp);
			a.setDate(today);

			//登録するAccountエンティティのインスタンスを生成
			AccountR.saveAndFlush(a);

			//sessionに格納
			session.setAttribute("accountInfo", a);

			if(stamp == 10) {
				mv.addObject("stamptxt", "おめでとうございます！");
				mv.addObject("stamptxt2", "プラチナランクに昇格しました！");
			}else if(stamp == 20) {
				mv.addObject("stamptxt", "おめでとうございます！");
				mv.addObject("stamptxt2", "ゴールドランクに昇格しました！");
			}else {
				mv.addObject("stamptxt", "本日分のスタンプが押されました！");
			}

			//+1したスタンプ数をhtmlに送る
			mv.addObject("stamp", stamp);
			mv.addObject("tf", tf);

			mv.setViewName("stamp");
			return mv;

		} else {

			mv.addObject("message", "本日分のスタンプはもう押してあります！");
			return stamp(mv);
		}

	}
}
