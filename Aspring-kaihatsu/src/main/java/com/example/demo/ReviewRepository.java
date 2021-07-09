package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Integer>{
	List<Review> findByCategory(String category);
	List<Review> findBySpoil(int spoil);
	List<Review> findByGenre(int genre);
	List<Review> findByGenreAndSpoil(int genre,int spoil);
	List<Review> findByAccount(int account);
	List<Review> findByAccountAndSpoil(int account,int spoil);
	List<Review> findByAccountAndGenre(int account,int genre);
	List<Review> findByAccountAndGenreAndSpoil(int account,int genre, int spoil);
	List<Review> findByDirector(String director);
	List<Review> findByDirectorAndSpoil(String director, int spoil);
	List<Review> findByDirectorAndGenre(String director, int genre);
	List<Review> findByDirectorAndGenreAndSpoil(String director,int genre, int spoil);
	List<Review> findByDirectorAndAccount(String director, int account);
	List<Review> findByDirectorAndAccountAndSpoil(String director, int account, int spoil);
	List<Review> findByDirectorAndAccountAndGenre(String director, int account, int genre);
	List<Review> findByDirectorAndAccountAndGenreAndSpoil(String director, int account, int genre, int spoil);
	List<Review> findByCategoryAndSpoil(String category, int spoil);
	List<Review> findByCategoryAndGenre(String category, int genre);
	List<Review> findByCategoryAndGenreAndSpoil(String category,int genre, int spoil);
	List<Review> findByCategoryAndAccount(String category, int account);
	List<Review> findByCategoryAndAccountAndSpoil(String category, int account, int spoil);
	List<Review> findByCategoryAndAccountAndGenre(String category, int account, int genre);
	List<Review> findByCategoryAndAccountAndGenreAndSpoil(String category, int account, int genre, int spoil);
}
