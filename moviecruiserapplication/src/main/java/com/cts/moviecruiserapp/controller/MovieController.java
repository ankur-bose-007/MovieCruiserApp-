package com.cts.moviecruiserapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.moviecruiserapp.domain.Movie;
import com.cts.moviecruiserapp.exception.MovieAlreadyExistsException;
import com.cts.moviecruiserapp.exception.MovieNotFoundException;
import com.cts.moviecruiserapp.service.MovieService;

import io.jsonwebtoken.Jwts;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/movieservice")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	@PostMapping("/movie")
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, final HttpServletRequest request,
			final HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		try {
			movie.setUserId(userId);
			movie.setMovieId(movie.getId());
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping("/movie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {
		ResponseEntity<?> responseEntity;
		try {
			Movie fetchedMovie = movieService.updateMovie(movie);
			responseEntity = new ResponseEntity<Movie>(fetchedMovie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("id") int id) {
		ResponseEntity<?> responseEntity;
		try {
			movieService.deleteMovieById(id);
			responseEntity = new ResponseEntity<String>("Movie deleted successfully", HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			Movie movie = movieService.getMovieById(id);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	@GetMapping("/movie")
	public ResponseEntity<?> fetchMyMovies(final HttpServletRequest req,final HttpServletResponse res){
		final String authHeader=req.getHeader("authorization");
		final String token=authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		ResponseEntity<?> responseEntity;
		try {
			List<Movie> list=movieService.getMyMovies(userId);
			responseEntity = new ResponseEntity<List<Movie>>(list, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}