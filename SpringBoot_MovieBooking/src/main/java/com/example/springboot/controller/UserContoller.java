package com.example.springboot.controller;

import java.util.List;

import com.example.springboot.dao.*;
import com.example.springboot.exception.BookingException;
import com.example.springboot.model.*;
import com.example.springboot.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class UserContoller {

	@Autowired
	CityDAO theCityDAO;
	
	@Autowired
	MovieDAO theMovieDAO;
	@Autowired
	private CustomerDAO CustomerDAO;

// Customer Registration before Login.
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
		Customer newCustomer = CustomerDAO.registerCustomer(customer);
		return ResponseEntity.ok(newCustomer);
	}

// Customer Login endpoint
	@PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestParam String phoneNumber) {
		Customer customer = CustomerDAO.loginCustomer(phoneNumber);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}

// Get Customer Details endpoint
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable Long customerId) {
		Customer customer = CustomerDAO.getCustomerDetails(customerId);
		customer.setEmailAddress("example@example.com");
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}
//	Get the list of City where you can book the movie
	@GetMapping("/city")
	public ResponseEntity<List<City>> getAllCities() {
		List<City> cities = theCityDAO.getCities();
		return ResponseEntity.ok(cities);
	}
// Get all movies
	@GetMapping("/movie")
	public ResponseEntity<List<Movie>> getAllMovies() {
		List<Movie> movies = theMovieDAO.getAllMovies();
		return ResponseEntity.ok(movies);
	}

// Get a movie by ID
	@GetMapping("/movie/{movieID}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("movieID") long movieID) {
		Movie movie = theMovieDAO.findOne(movieID);
		if (movie != null) {
			return ResponseEntity.ok(movie);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	private BookingDAO bookingDAO;
	@Autowired
	public void AdminController(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}

	@PostMapping("/booking/save")
	public ResponseEntity<String> createBooking(@RequestBody BookingRequest bookingRequest) {
		return bookingDAO.createBooking(bookingRequest);
	}

	@GetMapping("/booking/history")
	public ResponseEntity<List<Booking>> getBookingHistoryForCustomer(@RequestParam("customerId") Long customerId) {
		Customer customer = CustomerDAO.findById(customerId);

		if (customer == null) {
			// Customer not found, return an appropriate response
			return ResponseEntity.notFound().build();
		}

		List<Booking> bookingHistory = bookingDAO.getBookingHistoryForCustomer(customer);

		return ResponseEntity.ok(bookingHistory);
	}

}






