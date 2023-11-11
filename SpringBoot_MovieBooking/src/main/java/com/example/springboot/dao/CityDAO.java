package com.example.springboot.dao;

import java.util.List;

import com.example.springboot.respository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.City;

@Service
public class CityDAO {

	@Autowired
	private CityRepository cityRepository;

	// Save city
	public City save(City city) {
		return cityRepository.save(city);
	}

	// Get all cities
	public List<City> getCities() {
		return cityRepository.findAll();
	}

	// Update city
	public City updateCity(City city) {
		return cityRepository.save(city);
	}

	// Get city by ID
	public City findCityById(long cityID) {
		return cityRepository.findById(cityID).orElse(null);
	}

	// Delete city
	public void deleteCity(City city) {
		cityRepository.delete(city);
	}
}
