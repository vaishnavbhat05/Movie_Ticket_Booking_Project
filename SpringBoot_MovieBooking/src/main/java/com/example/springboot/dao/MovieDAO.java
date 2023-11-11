package com.example.springboot.dao;

import java.util.*;

import com.example.springboot.model.Theater;
import com.example.springboot.respository.MovieRepository;
import com.example.springboot.respository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Movie;

@Service
public class MovieDAO {
	@Autowired
	private MovieRepository movieRepository;
	public Movie addMovie(Movie movie) {
		return movieRepository.save(movie);
	}
	public Movie updateMovie(Long id, Movie updatedMovie) {
		Movie movie = movieRepository.findById(id).orElse(null);
		if (movie != null) {
			movie.setMovie_id(updatedMovie.getMovie_id());
			movie.setTitle(updatedMovie.getTitle());
			movie.setDescription(updatedMovie.getDescription());
			movie.setShowTimings(updatedMovie.getShowTimings());
			// Update other attributes if needed
			return movieRepository.save(movie);
		}
		return null; // Movie not found
	}

	// Get all movies
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	// Get a movie by ID
	public Movie findOne(long movieID) {
		return movieRepository.findById(movieID).orElse(null);
	}

	// Delete a movie
	public void deleteMovie(Movie movie) {
		movieRepository.delete(movie);
	}
	public MovieDAO(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}
	private Map<Long, Movie> movies = new HashMap<>();
	// Retrieve a movie by ID
	public Movie getMovieById(Long movieId) {
		return movies.get(movieId);
	}
}
