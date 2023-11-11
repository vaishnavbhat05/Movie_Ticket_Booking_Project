package com.example.springboot.dao;

import com.example.springboot.model.Theater;
import com.example.springboot.respository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class TheaterDAO {
	
	@Autowired
	TheaterRepository theTheaterRepository;
	
//Save Theater
	public Theater save(Theater t) {
		return theTheaterRepository.save(t);
	}
	
//	get all theater 
	public List<Theater> getTheater(){
		return theTheaterRepository.findAll();
	}
	
//	get Theater by ID
	public Theater findOne(long id){ return theTheaterRepository.getOne(id); }

//	Delete theater
	public void deleteTheater(Theater t) {
		theTheaterRepository.delete(t);
	}

	private  TheaterRepository theaterRepository;

	MovieDAO movieDAO ;

	@PersistenceContext
	private EntityManager entityManager;

	// ...

	public List<Theater> listTheatersWithMovies() {
		String query = "SELECT t FROM Theater t JOIN FETCH t.screens s JOIN FETCH s.shows";
		TypedQuery<Theater> typedQuery = entityManager.createQuery(query, Theater.class);
		List<Theater> theaters = typedQuery.getResultList();

		return theaters;
	}


}
