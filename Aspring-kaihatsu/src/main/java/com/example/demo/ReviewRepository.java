package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Integer>{

	Optional<Review> findById(int code);
	List<Review> findByCategory(String category);
	List<Review> findByCategoryOrderByGenre(String category);
	List<Review> findByCategoryOrderByStar(String category);
	List<Review> findByCategoryOrderByStarDesc(String category);
	List<Review> findByCategoryOrderByDate(String category);
	List<Review> findByCategoryOrderByDateDesc(String category);
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
	List<Review> findByCategoryAndDirector(String category, String director);
	List<Review> findByCategoryAndDirectorAndSpoil(String category, String director, int spoil);
	List<Review> findByCategoryAndDirectorAndGenre(String category, String director, int genre);
	List<Review> findByCategoryAndDirectorAndGenreAndSpoil(String category, String director, int genre, int spoil);
	List<Review> findByCategoryAndDirectorAndAccount(String category, String director, int account);
	List<Review> findByCategoryAndDirectorAndAccountAndSpoil(String category, String director, int account, int spoil);
	List<Review> findByCategoryAndDirectorAndAccountAndGenre(String category, String director, int account, int genre);
	List<Review> findByCategoryAndDirectorAndAccountAndGenreAndSpoil(String category, String director, int account, int genre, int spoil);
	List<Review> findByNameLike(String name);
	List<Review> findByNameLikeAndSpoil(String name, int spoil);
	List<Review> findByNameLikeAndGenre(String name, int genre);
	List<Review> findByNameLikeAndGenreAndSpoil(String name, int genre, int spoil);
	List<Review> findByNameLikeAndAccount(String name, int account);
	List<Review> findByNameLikeAndAccountAndSpoil(String name, int account, int spoil);
	List<Review> findByNameLikeAndAccountAndGenre(String name, int account, int genre);
	List<Review> findByNameLikeAndAccountAndGenreAndSpoil(String name, int account, int genre, int spoil);
	List<Review> findByNameLikeAndDirector(String name, String director);
	List<Review> findByNameLikeAndDirectorAndSpoil(String name, String director, int spoil);
	List<Review> findByNameLikeAndDirectorAndGenre(String name, String director, int genre);
	List<Review> findByNameLikeAndDirectorAndGenreAndSpoil(String name, String director, int genre, int spoil);
	List<Review> findByNameLikeAndDirectorAndAccount(String name, String director, int account);
	List<Review> findByNameLikeAndDirectorAndAccountAndSpoil(String name, String director, int account, int spoil);
	List<Review> findByNameLikeAndDirectorAndAccountAndGenre(String name, String director, int account, int genre);
	List<Review> findByNameLikeAndDirectorAndAccountAndGenreAndSpoil(String name, String director, int account, int genre, int spoil);
	List<Review> findByNameLikeAndCategory(String name, String category);
	List<Review> findByNameLikeAndCategoryAndSpoil(String name, String category, int spoil);
	List<Review> findByNameLikeAndCategoryAndGenre(String name, String category, int genre);
	List<Review> findByNameLikeAndCategoryAndGenreAndSpoil(String name, String category, int genre, int spoil);
	List<Review> findByNameLikeAndCategoryAndAccount(String name, String category, int account);
	List<Review> findByNameLikeAndCategoryAndAccountAndSpoil(String name, String category, int account, int spoil);
	List<Review> findByNameLikeAndCategoryAndAccountAndGenre(String name, String category, int account, int genre);
	List<Review> findByNameLikeAndCategoryAndAccountAndGenreAndSpoil(String name, String category, int account, int genre, int spoil);
	List<Review> findByNameLikeAndCategoryAndDirector(String name, String category, String director);
	List<Review> findByNameLikeAndCategoryAndDirectorAndSpoil(String name, String category, String director, int spoil);
	List<Review> findByNameLikeAndCategoryAndDirectorAndGenre(String name, String category, String director, int genre);
	List<Review> findByNameLikeAndCategoryAndDirectorAndGenreAndSpoil(String name, String category, String director, int genre, int spoil);
	List<Review> findByNameLikeAndCategoryAndDirectorAndAccount(String name, String category, String director, int account);
	List<Review> findByNameLikeAndCategoryAndDirectorAndAccountAndSpoil(String name, String category, String director, int account, int spoil);
	List<Review> findByNameLikeAndCategoryAndDirectorAndAccountAndGenre(String name, String category, String director, int account, int genre);
	List<Review> findByNameLikeAndCategoryAndDirectorAndAccountAndGenreAndSpoil(String name, String category, String director, int account, int genre, int spoil);
}
