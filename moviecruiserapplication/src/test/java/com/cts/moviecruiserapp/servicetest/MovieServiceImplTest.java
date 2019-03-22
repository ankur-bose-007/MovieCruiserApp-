package com.cts.moviecruiserapp.servicetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.moviecruiserapp.domain.Movie;
import com.cts.moviecruiserapp.exception.MovieAlreadyExistsException;
import com.cts.moviecruiserapp.exception.MovieNotFoundException;
import com.cts.moviecruiserapp.repository.MovieRepository;
import com.cts.moviecruiserapp.service.MovieServiceImpl;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MovieServiceImplTest {
	@Mock
	private MovieRepository movieRepository;
	
	@InjectMocks
	private MovieServiceImpl movieServiceImpl;
	
	private Movie movie;
	
	Optional<Movie> optionalMovie;
	
	@Before
	public void setupMock(){
		MockitoAnnotations.initMocks(this);
		movie=new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123");
		optionalMovie=Optional.of(movie);
	}
	
	@Test
	public void testSaveMovieSuccess() throws MovieAlreadyExistsException{
		when(movieRepository.save(movie)).thenReturn(movie);
		boolean flag=movieServiceImpl.saveMovie(movie);
		assertTrue("Movie saved",flag);
		verify(movieRepository, times(1)).save(movie);
	}
	@Test(expected=MovieAlreadyExistsException.class)
	public void testSaveMovieFailure() throws MovieAlreadyExistsException{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		when(movieRepository.save(movie)).thenReturn(movie);
		boolean flag=movieServiceImpl.saveMovie(movie);
		assertFalse("Could not save movie, Movie already exists",flag);
	}
	@Test
	public void testUpdateMovie() throws MovieNotFoundException{
		movie.setComments("very good");
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		
		Movie updatedMovie=movieServiceImpl.updateMovie(movie);
		when(movieRepository.save(movie)).thenReturn(updatedMovie);
		assertEquals("very good",updatedMovie.getComments());
		verify(movieRepository, times(1)).save(movie);
	}
	@Test
	public void testDeleteMovie() throws MovieNotFoundException{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		
		boolean flag=movieServiceImpl.deleteMovieById(movie.getId());
		assertTrue("Movie deleted",flag);
		verify(movieRepository, times(1)).delete(movie);
	}
	
	@Test
	public void testGetMovie() throws MovieNotFoundException{
		when(movieRepository.findById(movie.getId())).thenReturn(optionalMovie);
		
		Movie movieObj=movieServiceImpl.getMovieById(movie.getId());
		assertEquals(movie.getId(),movieObj.getId());
		verify(movieRepository, times(1)).findById(movie.getId());
	}
	
	@Test
	public void testGetAllMovies() throws MovieNotFoundException{
		List<Movie> list=new ArrayList<Movie>();
		list.add(movie);
		when(movieRepository.findByUserId("John123")).thenReturn(list);
		
		List<Movie> movieList=movieServiceImpl.getMyMovies("John123");
		assertEquals(list,movieList);
		verify(movieRepository, times(1)).findByUserId("John123");
	}
}
