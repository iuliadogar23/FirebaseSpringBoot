package com.spring.FirebaseSpringBoot.controller;

import com.spring.FirebaseSpringBoot.model.Movie;
import com.spring.FirebaseSpringBoot.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@Api(tags = "Movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getMovieDetails")
    public Movie getMovie(@RequestParam String name ) throws InterruptedException, ExecutionException {
        return movieService.getMovieDetails(name);
    }

    @PostMapping("/createMovie")
    public String createMovie(@RequestBody Movie movie ) throws InterruptedException, ExecutionException {
        return movieService.saveMovieDetails(movie);
    }

    @PutMapping("/updateMovie")
    public String updateMovie(@RequestBody Movie movie  ) throws InterruptedException, ExecutionException {
        return movieService.updateMovieDetails(movie);
    }

    @DeleteMapping("/deleteMovie")
    public String deleteMovie(@RequestParam String title){
        return movieService.deleteMovie(title);
    }

}
