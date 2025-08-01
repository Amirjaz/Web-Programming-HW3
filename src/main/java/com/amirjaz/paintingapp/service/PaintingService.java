package com.amirjaz.paintingapp.service;

import com.amirjaz.paintingapp.model.Painting;
import com.amirjaz.paintingapp.model.User;
import com.amirjaz.paintingapp.repository.PaintingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintingService {

    private final PaintingRepository repository;
    private final AuthService authService;

    public PaintingService(PaintingRepository repository, AuthService authService) {
        this.repository = repository;
        this.authService = authService;
    }

    public List<Painting> findAll() {
        User user = authService.getCurrentUser();
        return repository.findByUserUsername(user.getUsername());
    }

    public Painting findById(Long id) {
        User user = authService.getCurrentUser();
        return repository.findByIdAndUserUsername(id, user.getUsername()).orElseThrow();
    }

    public Painting save(Painting painting) {
        User user = authService.getCurrentUser();
        painting.setUser(user);
        return repository.save(painting);
    }

    public void delete(Long id) {
        User user = authService.getCurrentUser();
        Painting painting = repository.findByIdAndUserUsername(id, user.getUsername()).orElseThrow();
        repository.delete(painting);
    }
}