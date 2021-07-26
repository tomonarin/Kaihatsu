package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController {

	@Autowired
	HttpSession session;

	@Autowired
	ContactRepository contactR;

	@GetMapping("/contact")
	public String systemLogin() {
		return "contact";
	}

	@PostMapping(value = "/newcontact")
	public ModelAndView newContact(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			ModelAndView mv) {

		//未入力チェック
		if (name.equals("") || email.equals("") || title.equals("") || content.equals("")) {
			mv.addObject("error", "未入力の項目があります。");
			mv.addObject("name", name);
			mv.addObject("email", email);
			mv.addObject("title", title);
			mv.addObject("content", content);
			mv.setViewName("contact");
			return mv;
		}

		//登録するcontactエンティティのインスタンスを生成
		Contact contact = new Contact(name, email, title, content);

		contactR.saveAndFlush(contact);

		mv.addObject("message", "お問い合わせありがとうございます。お客様のお問い合わせは正常に送信されました。");

		mv.setViewName("contact");
		return mv;

	}

	@GetMapping("/contactlist")
	public ModelAndView contactList(ModelAndView mv) {

		List<Contact> allContacts = contactR.findAll();

		mv.addObject("contacts", allContacts);


		return mv;
	}
}