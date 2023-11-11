package com.example.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "screens")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long screenId;

    @Column(nullable = false)
    private String name;

    // Many-to-One association with theater
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    // One-to-Many association with shows
    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Show> shows;
    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
