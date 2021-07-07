package com.example.demo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;
	@Column(name="category_code")
	private Integer categoryCode;
	private Integer genre;
	private String name;
	private String director;
	private Integer spoil;
	private String review;

	private Integer account;

	private Timestamp date;

	public Review() {
	}

	//コンストラクタ
	public Review(int code, int categoryCode, int genre, String name, String director,int spoil, String review, int account,
			Timestamp date) {
		this.code = code;
		this.categoryCode = categoryCode;
		this.genre = genre;
		this.name = name;
		this.director = director;
		this.spoil = spoil;
		this.review = review;
		this.account = account;
		this.date = date;
	}

	//code無しのコンストラクタ
	public Review(int categoryCode, int genre, String name, String director,int spoil, String review, int account,
			Timestamp date) {
		this.categoryCode = categoryCode;
		this.genre = genre;
		this.name = name;
		this.director = director;
		this.spoil = spoil;
		this.review = review;
		this.account = account;
		this.date = date;
	}

	//ゲッターセッター
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Integer categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}


	public Integer getSpoil() {
		return spoil;
	}

	public void setSpoil(Integer spoil) {
		this.spoil = spoil;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
