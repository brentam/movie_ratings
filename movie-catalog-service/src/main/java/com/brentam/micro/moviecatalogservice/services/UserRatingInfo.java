/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.moviecatalogservice.services;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author odair
 */
@Service
public class UserRatingInfo {
	@Autowired
	RestTemplate template;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		UserRating ratingForUser = template.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
		ratingForUser.setUserId(userId);
		return ratingForUser;
	}

	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating= new UserRating(Arrays.asList(new Rating("0", 0)));
		userRating.setUserId(userId);
		return userRating;
	}
}
