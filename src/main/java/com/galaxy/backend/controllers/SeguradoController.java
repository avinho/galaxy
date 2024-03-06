package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.PessoaFisicaDTO;
import com.galaxy.backend.dtos.PessoaJuridicaDTO;
import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.dtos.SeguradoPageDTO;
import com.galaxy.backend.services.SeguradoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/segurados")
@CrossOrigin(origins = "*")
public class SeguradoController {

    private final SeguradoService seguradoService;

    public SeguradoController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @PostMapping("/pf")
    public ResponseEntity<PessoaFisicaDTO> createPF(@RequestBody PessoaFisicaDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguradoService.savePF(data));
    }

    @PostMapping("/pj")
    public ResponseEntity<PessoaJuridicaDTO> createPJ(@RequestBody PessoaJuridicaDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguradoService.savePJ(data));
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<PessoaFisicaDTO>> getByTipo(@RequestParam String query) {
        return ResponseEntity.ok(seguradoService.findSeguradoByTipoPF(query));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SeguradoDTO>> getAll() {
        return ResponseEntity.ok(seguradoService.findAll());
    }

    @GetMapping
    public ResponseEntity<SeguradoPageDTO> list(@RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return ResponseEntity.ok(seguradoService.listAll(pageNumber, pageSize));
    }

    @GetMapping("/search")
    public ResponseEntity<SeguradoPageDTO> listByName(@RequestParam String name, @RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber, @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return ResponseEntity.ok(seguradoService.searchByName(name, pageNumber, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeguradoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seguradoService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguradoDTO> update(@PathVariable Long id, @RequestBody SeguradoDTO data) {
        return ResponseEntity.ok().body(seguradoService.update(id, data));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return seguradoService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
