package com.cts.moviecruiserapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.moviecruiserapp.domain.Movie;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Optional<Movie> findByMovieId(int movieId);
	
	List<Movie> findByUserId(String userId);
}
