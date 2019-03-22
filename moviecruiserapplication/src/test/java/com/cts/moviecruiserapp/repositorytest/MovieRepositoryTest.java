package com.cts.moviecruiserapp.repositorytest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import com.cts.moviecruiserapp.domain.Movie;
import com.cts.moviecruiserapp.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MovieRepositoryTest {
	@Autowired
	private MovieRepository movieRepository;
	
	@After
	public void tearDown() {
		movieRepository.deleteAll();
	}
	
	@Test
	public void testSaveMovie() throws Exception{
		Movie movie=movieRepository.save(new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123"));
		assertEquals(true,movieRepository.existsById(movie.getId()));
		movieRepository.delete(movie);
	}
	
	@Test
	public void testUpdateMovie() throws Exception{
		movieRepository.save(new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123"));
		final Movie movie = movieRepository.findByMovieId(90).get();
		assertEquals(movie.getTitle(), "Superman");
		movie.setComments("bad movie");
		movieRepository.save(movie);
		final Movie tempMovie = movieRepository.findByMovieId(90).get();
		assertEquals(tempMovie.getComments(), "bad movie");
		movieRepository.delete(movie);
	}
	
	@Test
	public void testDeleteMovie() throws Exception{
		movieRepository.save(new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123"));
		final Movie movie = movieRepository.findByMovieId(90).get();
		assertEquals(movie.getTitle(), "Superman");
		movieRepository.delete(movie);
		assertEquals(Optional.empty(),movieRepository.findByMovieId(90));
	}
	
	@Test
	public void testGetMovie() throws Exception{
		movieRepository.save(new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123"));
		final Movie movie = movieRepository.findByMovieId(90).get();
		assertEquals(movie.getTitle(), "Superman");
		movieRepository.delete(movie);
	}
	
	@Test
	public void testGetAllMovies() throws Exception{
		Movie movie1=movieRepository.save(new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123"));
		Movie movie2=movieRepository.save(new Movie(1,92,"Batman","good movie","www.superman.com","2015-08-09","John123"));
		final List<Movie> list=movieRepository.findByUserId("John123");
		assertTrue(list.size()>0);
		movieRepository.delete(movie1);
		movieRepository.delete(movie2);
	}
}