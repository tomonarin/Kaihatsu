package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository AccountRepository;

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

		if(commentList.isEmpty() == false) {
		mv.addObject("comments", commentList);
		}else {
		String message ="このレビューへのコメントはありません";
		mv.addObject("message",message);
		}

		mv.addObject("review",review);
		mv.setViewName("comment");
		return mv;
	}

	/**
	 * コメントを登録
	 */
	@PostMapping(value="/review/comment/{reviewCode}")
	public ModelAndView registCommnet(
			@PathVariable(name="reviewCode") int reviewCode,
			ModelAndView mv) {
		Account a = (Account)session.getAttribute("accountInfo");
		int accountCode = a.getCode();

//		List<Comment> replyList = commentRepository.findByReviewCode(reviewCode);
//		mv.addObject("replys", replyList);
//		Review review = null;
//		Optional<Review> record = reviewRepository.findById(reviewCode);
//		if (record.isEmpty() == false) {//レコードがあれば
//			review = record.get(); //レコードを取得する
//		}
//
//		mv.addObject("review",review);


		return comment(reviewCode, mv);
	}

}
