package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository2 extends  PagingAndSortingRepository<Review, Integer>{

	 List<Review> findAllByCategory(String category, Pageable pageable);

}
