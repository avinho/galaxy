package com.galaxy.backend.services;

import com.galaxy.backend.dtos.AddProducaoDTO;
import com.galaxy.backend.dtos.SaldoCorretorDTO;
import com.galaxy.backend.models.Corretora;
import com.galaxy.backend.models.Producao;
import com.galaxy.backend.models.ProducaoDetail;
import com.galaxy.backend.repositories.ProducaoDetailRepository;
import com.galaxy.backend.repositories.ProducaoRepository;
import com.galaxy.backend.repositories.specifications.ProducaoSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProducaoService {

    private final ProducaoRepository producaoRepository;
    private final ProducaoDetailRepository producaoDetailRepository;
    private final CorretorService corretorService;
    private final CorretoraService corretoraService;

    public ProducaoService(
            ProducaoRepository producaoRepository,
            CorretorService corretorService,
            CorretoraService corretoraService,
            ProducaoDetailRepository producaoDetailRepository) {
        this.producaoRepository = producaoRepository;
        this.producaoDetailRepository = producaoDetailRepository;
        this.corretorService = corretorService;
        this.corretoraService = corretoraService;
    }

    public Producao createProducao(AddProducaoDTO producaoData) {
        Producao producao = new Producao();
        Corretora corretora = corretoraService.searchById(producaoData.corretoraId());

        BigDecimal totalPremioLiquido = producaoData.producoes()
                .stream().map(SaldoCorretorDTO::premioLiquido).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalCreditos = producaoData.producoes()
                .stream().map(SaldoCorretorDTO::creditos).reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer totalLancamentos = producaoData.producoes()
                .stream().map(SaldoCorretorDTO::lancamentos).reduce(0, Integer::sum);

        BigDecimal totalEstornos = producaoData.producoes()
                .stream().map(SaldoCorretorDTO::estornos).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSaldo = producaoData.producoes()
                .stream().map(SaldoCorretorDTO::saldo).reduce(BigDecimal.ZERO, BigDecimal::add);

        producao.setData(producaoData.data());
        producao.setCorretora(corretora);
        producao.setLancamentos(totalLancamentos);
        producao.setPremioLiquido(totalPremioLiquido);
        producao.setCreditos(totalCreditos);
        producao.setEstornos(totalEstornos);
        producao.setSaldo(totalSaldo);

        producaoData.producoes().forEach((x) -> {
            ProducaoDetail producaoDetail = new ProducaoDetail();
            producaoDetail.setProducao(producao);
            producaoDetail.setCorretor(x.corretor());
            producaoDetail.setSaldo(x.premioLiquido());
            producaoDetail.setCreditos(x.creditos());
            producaoDetail.setEstornos(x.estornos());
            producaoDetail.setLancamentos(x.lancamentos());
            producaoDetail.setPremioLiquido(x.premioLiquido());
            producao.getProducoes().add(producaoDetail);
        });

        return producaoRepository.save(producao);
    }

    public Producao getById(Long id) {
        return producaoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Producao> search(Long corretoraId, LocalDate startDate, LocalDate endDate) {
        Specification<Producao> spec = Specification.where(ProducaoSpecification.byCorretoraId(corretoraId))
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
