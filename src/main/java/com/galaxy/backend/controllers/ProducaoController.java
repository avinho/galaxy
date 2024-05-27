package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.AddProducaoDTO;
import com.galaxy.backend.models.Producao;
import com.galaxy.backend.services.ProducaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/financeiro/producao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProducaoController {

    private final ProducaoService producaoService;

    public ProducaoController(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    @GetMapping
    public ResponseEntity<List<Producao>> listAll() {
        return ResponseEntity.ok(this.producaoService.getAll());
    }

    @PostMapping
    public ResponseEntity<Producao> add(@RequestBody AddProducaoDTO dto) {
        return ResponseEntity.ok(producaoService.createProducao(dto));
    }

    @GetMapping("/searchBy")
    public ResponseEntity<List<Producao>> search(
            @RequestParam(required = false) Long corretoraId,
            @RequestParam(required = false) Long corretorId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        List<Producao> producoes = producaoService.search(corretoraId, startDate, endDate);
        return ResponseEntity.ok(producoes);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.producaoService.deleteAll();
    }
}
