package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;

import com.example.springboot.dao.*;
import com.example.springboot.model.*;
import com.example.springboot.respository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	public AdminController() {
		// Initialize any default values or dependencies here if needed
	}
	@Autowired
	CityDAO theCityDAO;
	@Autowired
	private MovieDAO theMovieDAO;
    @Autowired
    private TheaterDAO theaterDAO;
	@Autowired
	private ScreenDAO screenDAO;
	@Autowired
	private ShowDAO showDAO;
// Add cities to DB
	@PostMapping("/city/save")
	public ResponseEntity<City> createCity(@Valid @RequestBody City city) {
		City createdCity = theCityDAO.save(city);
		return ResponseEntity.ok(createdCity);
	}
	@GetMapping("/city")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> cities = theCityDAO.getCities();
		return ResponseEntity.ok(cities);
	}
//Add movies to DB
	@PostMapping("/movie/save")
	public Movie addMovie(@RequestBody Movie movie) {
		return theMovieDAO.addMovie(movie);
	}

// Get all movies
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = theMovieDAO.getAllMovies();
		return ResponseEntity.ok(movies);
	}
// Get a movie by ID
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("movieID") long movieID) {
		Movie movie = theMovieDAO.findOne(movieID);
		if (movie != null) {
			return ResponseEntity.ok(movie);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
//Update Movie
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
		Movie movie =theMovieDAO.updateMovie(id, updatedMovie);
		if (updatedMovie != null) {
			return ResponseEntity.ok(updatedMovie);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
// Delete a movie by ID
	@DeleteMapping("/movie/{movieID}")
	public ResponseEntity<Void> deleteMovie(@PathVariable("movieID") long movieID) {
		Movie movie = theMovieDAO.findOne(movieID);
		if (movie != null) {
			theMovieDAO.deleteMovie(movie);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	public AdminController(TheaterDAO theaterDAO, ScreenDAO screenDAO, ShowDAO showDAO) {
		this.theaterDAO = theaterDAO;
		this.screenDAO = screenDAO;
		this.showDAO = showDAO;
	}
	@PostMapping("/theater/save")
	public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
		Theater savedTheater = theaterDAO.save(theater);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedTheater);
	}
	// Define the method to list theaters for a movie
	@GetMapping("/theater")
	public ResponseEntity<List<Theater>> listTheatersWithMovies() {
		List<Theater> theaters = theaterDAO.listTheatersWithMovies();
		return ResponseEntity.ok(theaters);
	}
	@PostMapping("/screens/save")
	public ResponseEntity<Screen> createScreen(@RequestBody Screen screen) {
		Screen savedScreen = screenDAO.save(screen);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedScreen);
	}

	@GetMapping("/screens/{screenId}")
	public ResponseEntity<Screen> getScreenById(@PathVariable Long screenId) {
		Optional<Screen> optionalScreen = screenDAO.findById(screenId);
		if (optionalScreen.isPresent()) {
			Screen screen = optionalScreen.get();
			return ResponseEntity.ok(screen);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	public AdminController(ShowDAO showDAO) {
		this.showDAO = showDAO;
	}
	@PostMapping("/show/save")
	public ResponseEntity<Show> createShow(@RequestBody Show show) {
		Show createdShow = showDAO.save(show);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdShow);
	}


	@GetMapping("/show")
	public ResponseEntity<List<Show>> getAllShows() {
		List<Show> shows = showDAO.fetchAllShow();
		return ResponseEntity.ok(shows);
	}
}










