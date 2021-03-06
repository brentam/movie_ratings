/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.moviecatalogservice.resources;

import com.brentam.micro.moviecatalogservice.models.CatalogItem;
import com.brentam.micro.moviecatalogservice.models.Movie;
import com.brentam.micro.moviecatalogservice.models.Rating;
import com.brentam.micro.moviecatalogservice.models.UserRating;
import com.brentam.micro.moviecatalogservice.services.MovieInfo;
import com.brentam.micro.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/catalog")
public class MovieCatalogeResource {

//	@Autowired
//	WebClient.Builder webClientBuilder ;
	@Autowired
	RestTemplate template;

	@Autowired
	MovieInfo movieInfo;
	@Autowired
	UserRatingInfo userRatingInfo;

//	@Autowired
//	private  org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratingForUser = userRatingInfo.getUserRating(userId);

		List<Rating> ratings = ratingForUser.getRatings();

		    return ratings.stream().map(rating -> movieInfo.getCatalogItem(rating))

//               Movie movie=webClientBuilder.build()
//		   .get().
//		   uri("http://localhost:8082/movies/"+rating.getMovieId())
//		   .retrieve().
//		   bodyToMono(Movie.class).
//		   block();
		    .collect(Collectors.toList());

	}

}
