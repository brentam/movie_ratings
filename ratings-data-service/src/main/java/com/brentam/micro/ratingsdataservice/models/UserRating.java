/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.ratingsdataservice.models;

import java.util.List;

/**
 *
 * @author odair
 */
public class UserRating {
	private List<Rating> ratings;

	public UserRating() {
	}

	public UserRating(List<Rating> ratings) {
		this.ratings = ratings;
	}

	/**
	 * @return the ratings
	 */
	public List<Rating> getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
}