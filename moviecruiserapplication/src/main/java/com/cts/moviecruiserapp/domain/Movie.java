package com.cts.moviecruiserapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

	public Movie() { }

	

	public Movie(int id, int movieId, String title, String comments, String poster_path, String release_date,
			String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.userId = userId;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "movie_id")
	private int movieId;

	
	@Column(name = "title")
	private String title;

	
	@Column(name = "comments")
	private String comments;

	
	@Column(name = "poster_path")
	private String poster_path;

	
	@Column(name = "release_date")
	private String release_date;
	
	@Column(name = "user_id")
	private String userId;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
