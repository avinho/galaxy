package com.galaxy.backend.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.repositories.CorretorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Service
public class CorretorService {

    private final CorretorRepository corretorRepository;

    public CorretorService(CorretorRepository corretorRepository) {
        this.corretorRepository = corretorRepository;
    }

    public Corretor save(Corretor corretor) {
        return corretorRepository.save(corretor);
    }

    public List<Corretor> findAll() {
        return corretorRepository.findAll();
    }

    /*
    TODO Melhorar retorno de erro caso não localize.
     */
    public Corretor findById(Long id) {
        return corretorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /* TODO
    Colocar tratamento para o valor do parametro
     */
    public Corretor findByName(@NotNull @NotBlank @Valid String corretor) {
        return corretorRepository.findByNameIsIgnoreCase(corretor).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(Long id) {
        if (corretorRepository.existsById(id)) {
            corretorRepository.deleteById(id);
        }
    }
}
