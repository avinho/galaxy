package com.galaxy.backend.services;

import com.galaxy.backend.dtos.ProducaoDTO;
import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.models.Corretora;
import com.galaxy.backend.models.Producao;
import com.galaxy.backend.repositories.ProducaoRepository;
import com.galaxy.backend.repositories.specifications.ProducaoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProducaoService {

    private final ProducaoRepository producaoRepository;
    private final CorretorService corretorService;
    private final CorretoraService corretoraService;

    public ProducaoService(ProducaoRepository producaoRepository, CorretorService corretorService, CorretoraService corretoraService) {
        this.producaoRepository = producaoRepository;
        this.corretorService = corretorService;
        this.corretoraService = corretoraService;
    }

    public Producao save(Producao producao) {
        return producaoRepository.save(producao);
    }

    public List<Producao> saveAll(List<ProducaoDTO> data) {

        List<Producao> producoes = new ArrayList<>();

        data.forEach((prod) -> {
            Corretor corretor = corretorService.findById(prod.corretorId());
            Corretora corretora = corretoraService.searchById(prod.corretoraId());
            Producao _prod = new Producao();
            _prod.setCorretor(corretor);
            _prod.setCorretora(corretora);
            _prod.setData(prod.data());
            _prod.setPremioLiquido(BigDecimal.valueOf(prod.premioLiquido()));
            _prod.setCreditos(BigDecimal.valueOf(prod.creditos()));
            _prod.setEstornos(BigDecimal.valueOf(prod.estornos()));
            _prod.setSaldo(BigDecimal.valueOf(prod.saldo()));
            producoes.add(_prod);
        });
        return producaoRepository.saveAll(producoes);
    }

    public Producao getById(Long id) {
        return producaoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Producao> search(Long corretoraId, Long corretorId, LocalDate startDate, LocalDate endDate) {
        Specification<Producao> spec = Specification.where(ProducaoSpecification.byCorretoraId(corretoraId))
                .and(ProducaoSpecification.byCorretorId(corretorId))
                .and(ProducaoSpecification.byDataBetween(startDate, endDate));
        return producaoRepository.findAll(spec);
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
