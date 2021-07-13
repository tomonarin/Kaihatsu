package com.example.demo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Account")
public class Account {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;//ユーザーID
	private String name;//名前
	@Column(name="account_name")
	private String accountName;//アカウント名
	private String email;//メールアドレス
	private String password;//パスワード
	private String photo;//プロフィール画像
	private Integer login;//ログインスタンプの個数
	@Column(name="lastlogin")
	private LocalDateTime date;//最終ログイン日

	//コンストラクタ
	public Account() {

	}

	public Account(Integer code, String name, String accountName, String email,String password,String photo,Integer login,LocalDateTime date ) {
		this(name, accountName, email,password,photo,login,date);
		this.code = code;
	}

	public Account(String name, String accountName, String email, String password,String photo,Integer login,LocalDateTime date) {
		this.name = name;
		this.accountName = accountName;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.login = login;
		this.date = date;
	}

	//アクセッサ・メソッド（セッタ＆ゲッタ
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = login;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}



}
