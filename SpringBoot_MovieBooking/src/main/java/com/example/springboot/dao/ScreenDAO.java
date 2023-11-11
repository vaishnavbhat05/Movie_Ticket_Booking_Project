package com.example.springboot.dao;

import com.example.springboot.model.Screen;
import com.example.springboot.respository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScreenDAO {
    private final ScreenRepository screenRepository;

    @Autowired
    public ScreenDAO(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public Optional<Screen> findById(Long screenId) {
        return screenRepository.findById(screenId);
    }

    public Screen save(Screen screen) {
        return screenRepository.save(screen);
    }
}

