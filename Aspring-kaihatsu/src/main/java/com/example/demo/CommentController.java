package com.example.demo;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	CommentRepository commentRepository;

	/**
	 * コメント画面を表示
	 */
	@GetMapping(value="/review/comment/{reviewCode}")
	public ModelAndView comment(
			@PathVariable(name="reviewCode") int reviewCode,
			ModelAndView mv) {

		Review review = null;
		Optional<Review> record = reviewRepository.findById(reviewCode);
		if (record.isEmpty() == false) {//レコードがあれば
			review = record.get(); //レコードを取得する
		}

		List<Comment> commentList = commentRepository.findByReviewCode(reviewCode);
		List<Account> accountList = accountRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
		List<String> accountNames = new ArrayList<String>();
		//アカウント名が格納されたリスト生成
		accountNames.add("");
		for (Account accounts : accountList) {
			String aName = accounts.getAccountName();
			accountNames.add(aName);
		}
		mv.addObject("account", accountNames);


		if(commentList.isEmpty() == false) {
		mv.addObject("comments", commentList);
		}else {
		String message ="このレビューへのコメントはありません";
		mv.addObject("message",message);
		}
		mv.addObject("reviewCode",reviewCode);
		mv.addObject("review",review);
		mv.setViewName("comment");
		return mv;
	}

	/**
	 * コメントを登録
	 */
	@PostMapping(value="/review/comment/{reviewCode}")
	public ModelAndView registComment(
			@RequestParam(name="comment") String comment,
			@PathVariable(name="reviewCode") int reviewCode,
			ModelAndView mv) {

		Account a = (Account)session.getAttribute("accountInfo");
		int accountCode = a.getCode();

		//登録するcommentエンティティのインスタンスを生成
		Comment comments = new Comment(reviewCode,accountCode,comment);

		//recordエンティティをreviewテーブルに登録
		commentRepository.saveAndFlush(comments);

		Review review = null;
		Optional<Review> record = reviewRepository.findById(reviewCode);
		if (record.isEmpty() == false) {//レコードがあれば
			review = record.get(); //レコードを取得する
		}
		mv.addObject("reviewCode",reviewCode);
		mv.addObject("review",review);

		return comment(reviewCode, mv);
	}

}
