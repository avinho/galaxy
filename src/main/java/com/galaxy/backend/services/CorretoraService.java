package com.galaxy.backend.services;

import com.galaxy.backend.models.Corretora;
import com.galaxy.backend.repositories.CorretoraRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CorretoraService {

    private final CorretoraRepository corretoraRepository;

    public CorretoraService(CorretoraRepository corretoraRepository) {
        this.corretoraRepository = corretoraRepository;
    }

    public Corretora save(Corretora corretora) {
        return corretoraRepository.save(corretora);
    }

    public Corretora searchById(Long id) {
        return corretoraRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Corretora searchCorretoraByName(String name) {
        return corretoraRepository.findByNameIgnoreCase(name);
    }

    public List<Corretora> findAll() {
        return corretoraRepository.findAll();
    }

    public void delete(Long id) {
        corretoraRepository.deleteById(id);
    }
}
