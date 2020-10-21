/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.moviecatalogservice.resources;

import com.brentam.micro.moviecatalogservice.models.CatalogItem;
import com.brentam.micro.moviecatalogservice.models.Movie;
import com.brentam.micro.moviecatalogservice.models.Rating;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author odair
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogeResource {

	RestTemplate template = new RestTemplate();

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		int count = 4;
		ArrayList<Rating> ratings = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			ratings.add(new Rating("movieId" + i, 3));
		}
	return	ratings.stream().map(rating ->{
		
             Movie movie=  template.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
	       return new CatalogItem(movie.getName(),"desc", rating.getRating());
		}).collect(Collectors.toList());
		
	}

}
