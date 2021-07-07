package com.example.demo;

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

	//コンストラクタ
	public Account() {

	}

	public Account(Integer code, String name, String accountName, String email, String password) {
		this(name, accountName, email,password);
		this.code = code;
	}

	public Account(String name, String accountName, String email, String password) {
		this.name = name;
		this.accountName = accountName;
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


}
