package com.example.springboot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
