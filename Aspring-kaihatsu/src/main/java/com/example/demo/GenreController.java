package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenreController {
	@Autowired
	HttpSession session;

	@Autowired
	GenreRepository genreR;


	//http://localhost:8080/kanri/genre
	@GetMapping("/kanri/genre")
	public ModelAndView kanriGenre(ModelAndView mv) {
		System systemInfo = (System) session.getAttribute("systemInfo");

		//データベースから情報取得
		List<Genre> genreList = genreR.findAll(Sort.by(Sort.Direction.ASC, "code"));

		//Thymeleafで表示する準備
		mv.addObject("systemInfo", systemInfo);
		mv.addObject("genreList", genreList);
		mv.setViewName("kanrigenre");
		return mv;
	}



	//ジャンルを追加

	@PostMapping(value = "/addGenre")
	public ModelAndView addGenre(
			@RequestParam("name") String name,
			ModelAndView mv) {

		//未入力チェック
		if (name.equals("")) {
			mv.addObject("error", "ジャンル名を入力してください");
			mv.setViewName("review");
			return mv;
		}

		//登録するreviewエンティティのインスタンスを生成
		Genre genre = new Genre(name);

		//recordエンティティをreviewテーブルに登録
		genreR.saveAndFlush(genre);

		//ジャンル一覧を再取得
		List<Genre> genreList = genreR.findAll();
		mv.addObject("genreList", genreList);


		mv.addObject("message", "ジャンルを追加しました");

		mv.setViewName("kanrigenre");
		return mv;
	}

	//投稿の削除
	@GetMapping("/genre/delete")
	public ModelAndView deleteCart(
			@RequestParam("codes") int codes,
			ModelAndView mv) {
		//レビューを削除
		genreR.deleteById(codes);

		//ジャンル一覧を再取得
		List<Genre> genreList = genreR.findAll();

		mv.addObject("genreList", genreList);
		mv.addObject("message", "ジャンルを削除しました");

		mv.setViewName("kanrigenre");
		return mv;
	}

}
