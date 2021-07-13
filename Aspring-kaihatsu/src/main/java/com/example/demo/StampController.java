package com.example.demo;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value="/stamp")
	public ModelAndView signup(ModelAndView mv) {
		Account a = (Account)session.getAttribute("accountInfo");
		int stamp = a.getLogin();
		LocalDateTime lastlogin = a.getDate();
		LocalDateTime date = LocalDateTime.now();

		boolean tf = date.isAfter(lastlogin);

		if(tf == true){
		//スタンプを押す
		stamp = stamp + 1;

		//登録するAccountエンティティのインスタンスを生成
		Account account = new Account(stamp, date);
		AccountR.saveAndFlush(account);
		//+1したスタンプ数をhtmlに送る
		mv.addObject("stamp", stamp);

		mv.setViewName("stamp");
		}else{
		mv.addObject("message", "本日分のスタンプはもう押してあります！");
		mv.setViewName("stamp");
		}

		return mv;

	}
	}
