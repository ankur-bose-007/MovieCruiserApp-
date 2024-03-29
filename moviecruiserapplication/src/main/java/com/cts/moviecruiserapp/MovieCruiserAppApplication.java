package com.cts.moviecruiserapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cts.moviecruiserapp.filter.JwtFilter;

@SpringBootApplication
public class MovieCruiserAppApplication {

	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean registrationBean=new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserAppApplication.class, args);
	}

}
