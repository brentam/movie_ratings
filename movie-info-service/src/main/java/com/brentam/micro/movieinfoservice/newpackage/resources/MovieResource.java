/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.movieinfoservice.newpackage.resources;

import com.brentam.micro.movieinfoservice.models.Movie;
import com.brentam.micro.movieinfoservice.models.MovieSummary;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author odair
 */
@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	RestTemplate restTemplate;
	
        @RequestMapping("/{movieId}")
	public Movie getMovies(@PathVariable("movieId")String movieId){

		MovieSummary ms= restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
		
		return new Movie(movieId, ms.getTitle(),ms.getOverview());
	}
	
}
