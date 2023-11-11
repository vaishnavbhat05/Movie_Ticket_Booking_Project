package com.example.springboot.dao;

import com.example.springboot.exception.BookingException;
import com.example.springboot.model.Booking;
import com.example.springboot.model.Customer;
import com.example.springboot.model.Show;
import com.example.springboot.request.BookingRequest;
import com.example.springboot.respository.BookingRepository;
import com.example.springboot.respository.CustomerRepository;
import com.example.springboot.respository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingDAO {

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    // Add any additional methods as per your requirements
    private  ShowRepository showRepository;
    private CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingDAO(
            ShowRepository showRepository,
            BookingRepository bookingRepository,
            CustomerRepository customerRepository
    ) {
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<String> createBooking(BookingRequest bookingRequest) {
        // Retrieve the Show entity using showId
        Show show = showRepository.findById(bookingRequest.getShowId())
                .orElse(null);

        if (show == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Show not found");
        }

        // Check if the requested number of seats is available
        int requestedSeats = bookingRequest.getNumberOfSeats();
        int availableSeats = show.getAvailableSeats();
        if (requestedSeats > availableSeats) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough seats available for the show");
        }

        // Create a new Booking entity
        Booking booking = new Booking();
        booking.setShow(show);
        booking.setCustomer(customerRepository.findById(bookingRequest.getCustomerId())
                .orElse(null));

        if (booking.getCustomer() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }

        booking.setNumberOfSeats(requestedSeats);

        // Update the available seats in the Show entity
        show.setAvailableSeats(availableSeats - requestedSeats);

        // Save the Booking entity and update the Show entity
        bookingRepository.save(booking);
        showRepository.save(show);

        return ResponseEntity.ok("Booking created successfully");
    }


    public List<Booking> getBookingHistoryForCustomer(Customer customer) {
        return bookingRepository.findByCustomer(customer);
    }
}