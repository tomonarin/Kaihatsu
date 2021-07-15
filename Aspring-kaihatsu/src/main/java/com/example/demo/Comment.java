package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	@Column(name="review_code")
	private Integer reviewCode;
	@Column(name="account_code")
	private Integer accountCode;
	private String comment;
	private  LocalDateTime date = LocalDateTime.now();

	public Comment() {
	}

	//コンストラクタ
	public Comment(int code, int reviewCode, int accountCode,String comment ) {
		this.code = code;
		this.reviewCode = reviewCode;
		this.accountCode = accountCode;
		this.comment = comment;
		this.date = LocalDateTime.now();
	}

	//code無しのコンストラクタ
	public Comment(int reviewCode, int accountCode,String comment ) {
		this.reviewCode = reviewCode;
		this.accountCode = accountCode;
		this.comment = comment;
		this.date = LocalDateTime.now();
	}

	//ゲッターセッター
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getReviewCode() {
		return reviewCode;
	}

	public void setCategory(Integer reviewCode) {
		this.reviewCode = reviewCode;
	}

	public Integer getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(Integer accountCode) {
		this.accountCode = accountCode;
	}

	public String getComment() {
		return comment;
	}

	public void setName(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
