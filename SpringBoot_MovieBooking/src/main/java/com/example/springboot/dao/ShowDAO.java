package com.example.springboot.dao;

import java.util.List;
import java.util.Optional;

import com.example.springboot.model.Customer;
import com.example.springboot.model.Show;
import com.example.springboot.respository.MovieRepository;
import com.example.springboot.respository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Movie;

@Service
public class ShowDAO {
	@Autowired
	private  ShowRepository showRepository;
	
//	Save the show
    public Show save(Show show) {
	// Save the Show entity
	  return showRepository.save(show);
    }

//	Fetch all show
	public List<Show> fetchAllShow(){
		return showRepository.findAll();
		
	}
	
//	Fetch show by ID
	public Show fetchById(long Id) {
		return showRepository.getOne(Id);
	}
	

}
