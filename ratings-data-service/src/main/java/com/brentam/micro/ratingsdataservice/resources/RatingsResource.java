/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.ratingsdataservice.resources;

import com.brentam.micro.ratingsdataservice.models.Rating;
import com.brentam.micro.ratingsdataservice.models.UserRating;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author odair
 */
@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

        @RequestMapping("/{movieId}")
	public List<Rating> getRating(@PathVariable("movieId")String movieId){
	 return Collections.singletonList(new Rating(movieId, 4));
	}
	
        @RequestMapping("user/{userId}")
	public UserRating getUserRatings(@PathVariable("userId")String userId){
		int count=2;
		ArrayList<Rating> ratings = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			ratings.add(new Rating(Integer.toString(100+i) , i+1));
		}
	UserRating r=	new UserRating(ratings);
	r.setUserId(userId);
	return r;
	}
	
}
