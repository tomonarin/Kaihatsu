package com.example.demo;

import java.time.LocalDateTime;

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
	private String category;
	private Integer genre;
	private String name;
	private String director;
	private Integer star;
	private Integer spoil;
	private String review;
	private Integer account;
	private  LocalDateTime date = LocalDateTime.now();

	public Review() {
	}

	//コンストラクタ
	public Review(int code, String category, int genre, String name, String director,int star,int spoil, String review, int account) {
		this.code = code;
		this.category = category;
		this.genre = genre;
		this.name = name;
		this.director = director;
		this.star = star;
		this.spoil = spoil;
		this.review = review;
		this.account = account;
		this.date = LocalDateTime.now();
	}

	//code無しのコンストラクタ
	public Review(String category, int genre, String name, String director,int star,int spoil,String review, int account) {
		this.category = category;
		this.genre = genre;
		this.name = name;
		this.director = director;
		this.star = star;
		this.spoil = spoil;
		this.review = review;
		this.account = account;
		this.date = LocalDateTime.now();
	}

	//ゲッターセッター
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
