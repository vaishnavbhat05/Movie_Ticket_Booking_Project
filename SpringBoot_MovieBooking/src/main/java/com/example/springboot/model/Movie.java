package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String showTimings;
	@NotNull
	public Long getMovie_id() {
		return id;
	}
	public void setMovie_id(@NotNull Long id) {
		this.id = id;
	}
	@NotNull
	public String getTitle() {
		return title;
	}

	public void setTitle(@NotNull String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowTimings() {
		return showTimings;
	}

	public void setShowTimings(String showTimings) {
		this.showTimings = showTimings;
	}

}







