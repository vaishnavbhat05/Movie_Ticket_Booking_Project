package com.example.springboot.respository;

import java.util.List;

import com.example.springboot.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Movie;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
