/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.moviecatalogservice.services;

import com.brentam.micro.moviecatalogservice.models.CatalogItem;
import com.brentam.micro.moviecatalogservice.models.Movie;
import com.brentam.micro.moviecatalogservice.models.Rating;
import com.brentam.micro.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
/**
 *
 * @author odair
 */
@Service
public class MovieInfo {
	
	@Autowired
	RestTemplate template;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = template.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
	       return new CatalogItem(movie.getName(),movie.getDescription(), rating.getRating());
	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found","",rating.getRating());
	}

}
