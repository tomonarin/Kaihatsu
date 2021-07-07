package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="genre")
public class Genre {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer code;

private String name;

//コンストラクタ
private Genre() {
}

private Genre(int code, String name) {
this.code = code;
this.name= name;
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




}
