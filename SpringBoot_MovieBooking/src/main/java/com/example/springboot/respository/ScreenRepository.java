package com.example.springboot.respository;

import com.example.springboot.model.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
   Optional<Screen> findById(Long screenId);
}

