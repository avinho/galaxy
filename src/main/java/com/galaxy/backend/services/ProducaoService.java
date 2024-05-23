package com.galaxy.backend.services;

import com.galaxy.backend.models.Producao;
import com.galaxy.backend.repositories.ProducaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProducaoService {

    private final ProducaoRepository producaoRepository;

    public ProducaoService(ProducaoRepository producaoRepository) {
        this.producaoRepository = producaoRepository;
    }

    public Producao save(Producao producao) {
        return producaoRepository.save(producao);
    }

    public List<Producao> saveAll(List<Producao> producao) {
        return producaoRepository.saveAll(producao);
    }

    public Producao getById(Long id) {
        return producaoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Producao> getByCorretora(String corretora) {
        return producaoRepository.findByCorretoraContainsIgnoreCase(corretora);
    }

    public List<Producao> searchByCorretoraAndDateRange(String corretora, LocalDate startDate, LocalDate endDate) {
        return producaoRepository.findByCorretoraContainsIgnoreCaseAndDataBetween(corretora, startDate, endDate);
    }

    public List<Producao> getByCorretorId(Long id) {
        return producaoRepository.findProducaoByCorretorId(id);
    }

    public List<Producao> getAll() {
        return producaoRepository.findAll();
    }

    public void delete(Long id) {
        producaoRepository.deleteById(id);
    }

    public void deleteAll() {
        producaoRepository.deleteAll();
    }
}
