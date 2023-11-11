package com.example.springboot.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.springboot.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
