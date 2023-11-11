package com.example.springboot.respository;

import java.util.List;

import com.example.springboot.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.City;
import com.example.springboot.model.Theater;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
