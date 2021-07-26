package com.example.demo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="System")
public class System {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer code;//管理者コード
	private String name;//名前
	@Column(name="system_id")
	private String systemId;//アカウント名
	private String password;//パスワード


	//コンストラクタ
	public System() {

	}

	public System(Integer code, String name, String systemId, String password) {
		this.code = code;
		this.name = name;
		this.systemId = systemId;
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

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
