package com.example.demo;

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
	private String account_name;//アカウント名
	private String email;//メールアドレス
	private String password;//パスワード

	//コンストラクタ
	public Account() {

	}

	public Account(Integer code, String name, String account_name, String email, String password) {
		this(name, account_name, email,password);
		this.code = code;
	}

	public Account(String name, String account_name, String email, String password) {
		this.name = name;
		this.account_name = account_name;
		this.email = email;
		this.password = password;
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

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
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


}
