package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Profile {
	@Id
	private Integer code;
	private String comment;
	private String favorite;
	private String mybest;

	public Profile() {
	}

	public Profile(int code, String comment, String favorite, String mybest) {
		this.code = code;
		this.comment = comment;
		this.favorite = favorite;
		this.mybest = mybest;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}

	public String getMybest() {
		return mybest;
	}

	public void setMybest(String mybest) {
		this.mybest = mybest;
	}


}
