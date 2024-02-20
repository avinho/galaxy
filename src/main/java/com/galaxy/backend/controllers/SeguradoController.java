package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.models.Segurado;
import com.galaxy.backend.services.SeguradoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/segurados")
public class SeguradoController {

    private final SeguradoService seguradoService;

    public SeguradoController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @PostMapping
    public ResponseEntity<SeguradoDTO> create(@RequestBody SeguradoDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguradoService.save(data));
    }

    @GetMapping
    public ResponseEntity<List<Segurado>> getAll() {
        return ResponseEntity.ok(seguradoService.findAll());
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
