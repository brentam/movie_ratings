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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	RestTemplate template ;

//	@Autowired
//	private  org.springframework.cloud.client.discovery.DiscoveryClient discoveryClient;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

             UserRating ratingForUser=  template.getForObject("http://ratings-data-service/ratingsdata/user/"+userId, UserRating.class);
		
               List<Rating> ratings=ratingForUser.getRatings();
	     
	return	ratings.stream().map(rating ->{
		
             Movie movie=  template.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
//               Movie movie=webClientBuilder.build()
//		   .get().
//		   uri("http://localhost:8082/movies/"+rating.getMovieId())
//		   .retrieve().
//		   bodyToMono(Movie.class).
//		   block();
	       return new CatalogItem(movie.getName(),"desc", rating.getRating());
		}).collect(Collectors.toList());
		
	}

}
