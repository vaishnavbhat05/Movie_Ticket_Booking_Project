package com.example.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Table(name="shows")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long showId;

	// Many-to-One association with screen
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "screen_id")
	private Screen screen;

	@Column(nullable = false)
	private LocalDateTime startTime;

	@Column(nullable = false)
	private int availableSeats;

	public Long getShowId() {
		return showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
