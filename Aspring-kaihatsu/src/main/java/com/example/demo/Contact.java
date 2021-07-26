package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer code;
private String name;
private String email;
private String title;
private String content;

//コンストラクタ
private Contact() {
}

public Contact(String name, String email, String title, String content) {
this.name = name;
this.email = email;
this.title = title;
this.content = content;
}

//ゲッターセッター

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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}




}
