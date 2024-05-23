package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.ProducaoDTO;
import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.models.Producao;
import com.galaxy.backend.services.CorretorService;
import com.galaxy.backend.services.ProducaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/financeiro/producao")
@CrossOrigin(origins = "*")
public class ProducaoController {

    private final ProducaoService producaoService;
    private final CorretorService corretorService;

    public ProducaoController(ProducaoService producaoService, CorretorService corretorService) {
        this.producaoService = producaoService;
        this.corretorService = corretorService;
    }

    @GetMapping
    public ResponseEntity<List<Producao>> listAll() {
        return ResponseEntity.ok(this.producaoService.getAll());
    }

    @PostMapping
    public ResponseEntity<List<Producao>> save(@RequestBody List<ProducaoDTO> data) {
        List<Producao> prods = new ArrayList<>();

        data.forEach((producao) -> {
            Corretor corretor = corretorService.findById(producao.corretorId());
            Producao prod = new Producao();
            prod.setCorretor(corretor);
            prod.setCorretora(producao.corretora());
            prod.setData(producao.data());
            prod.setPremioLiquido(BigDecimal.valueOf(producao.premioLiquido()));
            prod.setCreditos(BigDecimal.valueOf(producao.creditos()));
            prod.setEstornos(BigDecimal.valueOf(producao.estornos()));
            prod.setSaldo(BigDecimal.valueOf(producao.saldo()));
            prods.add(prod);
        });

        return ResponseEntity.ok(producaoService.saveAll(prods));
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Producao>> findAllByCorretora(@RequestParam String corretora) {
        return ResponseEntity.ok(producaoService.getByCorretora(corretora));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Producao>> search(
            @RequestParam String corretora,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<Producao> producoes = producaoService.searchByCorretoraAndDateRange(corretora, startDate, endDate);
        return ResponseEntity.ok(producoes);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.producaoService.deleteAll();
    }
}
