/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brentam.micro.movieinfoservice.newpackage.resources;

import com.brentam.micro.movieinfoservice.models.Movie;
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
@RequestMapping("/movies")
public class MovieResource {

        @RequestMapping("/{movieId}")
	public Movie getMovies(@PathVariable("movieId")String movieId){
	 return new Movie(movieId, movieId+" - name");
	}
	
}
