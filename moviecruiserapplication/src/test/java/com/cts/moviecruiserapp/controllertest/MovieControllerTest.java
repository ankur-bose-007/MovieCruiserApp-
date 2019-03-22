package com.cts.moviecruiserapp.controllertest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import com.cts.moviecruiserapp.domain.Movie;
import com.cts.moviecruiserapp.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private MovieService movieService;
	
	private Movie movie;
	
	static List<Movie> movies;
	
	ObjectMapper mapper;
	@Before
	public void setUp(){
		mapper=new ObjectMapper();
		movies=new ArrayList<Movie>();
		
		movie=new Movie(1,90,"Superman","good movie","www.superman.com","2015-08-09","John123");
		movies.add(movie);
		movies.add(new Movie(1,91,"Batman","good movie","www.superman.com","2015-08-09","John123"));
	}
	
	@Test
	public void testSaveNewMovieSuccess() throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3NlLmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MjczNzEzMX0.Ot71N3SktDOXDcg5eIbpwfcvt-f0HDmadkr93ullQM0";
		when(movieService.saveMovie(movie)).thenReturn(true);
		mockMvc.perform(post("/api/v1/movieservice/movie").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(movie)))
				.andExpect(status().isCreated());
		verify(movieService,times(1)).saveMovie(Mockito.any(Movie.class));
	}
	@Test
	public void testUpdateMovie() throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3NlLmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MjczNzEzMX0.Ot71N3SktDOXDcg5eIbpwfcvt-f0HDmadkr93ullQM0";
		when(movieService.updateMovie(movie)).thenReturn(movie);
		mockMvc.perform(put("/api/v1/movieservice/movie").header("authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(movie)))
				.andExpect(status().isOk());
		verify(movieService,times(1)).updateMovie(Mockito.any(Movie.class));
	}
	@Test
	public void testDeleteMovie() throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3NlLmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MjczNzEzMX0.Ot71N3SktDOXDcg5eIbpwfcvt-f0HDmadkr93ullQM0";
		when(movieService.deleteMovieById(movie.getId())).thenReturn(true);
		mockMvc.perform(delete("/api/v1/movieservice/movie/"+movie.getId()).header("authorization","Bearer "+token))
				.andExpect(status().isOk());
		verify(movieService,times(1)).deleteMovieById(movie.getId());
	}
	@Test
	public void testGetMovie() throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3NlLmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MjczNzEzMX0.Ot71N3SktDOXDcg5eIbpwfcvt-f0HDmadkr93ullQM0";
		when(movieService.getMovieById(movie.getId())).thenReturn(movie);
		mockMvc.perform(get("/api/v1/movieservice/movie/"+movie.getId()).header("authorization","Bearer "+token))
				.andExpect(status().isOk());
		verify(movieService,times(1)).getMovieById(movie.getId());
	}
	@Test
	public void testGetMyMovies() throws Exception{
		String token="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib3NlLmFua3VyMDA3QGdtYWlsLmNvbSIsImlhdCI6MTU1MjczNzEzMX0.Ot71N3SktDOXDcg5eIbpwfcvt-f0HDmadkr93ullQM0";
		when(movieService.getMyMovies("bose.ankur007@gmail.com")).thenReturn(movies);
		mockMvc.perform(get("/api/v1/movieservice/movie").header("authorization", "Bearer "+token))
				.andExpect(status().isOk());
		verify(movieService,times(1)).getMyMovies("bose.ankur007@gmail.com");
	}
}
