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
}
