package com.spring.FirebaseSpringBoot.controller;

import com.google.gson.Gson;
import com.spring.FirebaseSpringBoot.model.Movie;
import com.spring.FirebaseSpringBoot.service.MovieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Api(tags = "Movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/getAllMovies")
    public List<Movie> getAllMovies() throws ExecutionException, InterruptedException {
        return movieService.getAllMovies();
    }

    @GetMapping("/getMovieDetails")
    public Movie getMovie(@RequestParam String name ) throws InterruptedException, ExecutionException {
        return movieService.getMovieDetails(name);
    }

    @PostMapping(value = "/createMovie")
    public void createMovie(@RequestBody String movie ) throws InterruptedException, ExecutionException {
        Gson gson = new Gson();
        movieService.saveMovieDetails(gson.fromJson(movie, Movie.class));
    }

    @PutMapping("/updateMovie")
    public String updateMovie(@RequestBody String movie  ) throws InterruptedException, ExecutionException {
        Gson gson = new Gson();
        return movieService.updateMovieDetails(gson.fromJson(movie, Movie.class));
    }

    @DeleteMapping("/deleteMovie")
    public boolean deleteMovie(@RequestParam String title){
        return movieService.deleteMovie(title);
    }

}
