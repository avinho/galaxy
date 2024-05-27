package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.dtos.SeguradoPageDTO;
import com.galaxy.backend.models.Address;
import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.services.SeguradoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/segurados")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SeguradoController {

    private final SeguradoService seguradoService;

    public SeguradoController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<?>> getByTipo(@RequestParam String query) {
        return ResponseEntity.ok(seguradoService.findSeguradoByTipo(query));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeguradoDTO> updateAllAttributes(@PathVariable Long id, @RequestBody SeguradoDTO data) {
        return ResponseEntity.ok(seguradoService.update(id, data));
    }

    @PatchMapping("/{id}/address")
    public ResponseEntity<SeguradoDTO> updateAddress(@PathVariable Long id, @RequestBody Address data) {
        return ResponseEntity.ok(seguradoService.updateAddress(id, data));
    }

    @PatchMapping("/{id}/corretor")
    public ResponseEntity<SeguradoDTO> updateCorretor(@PathVariable Long id, @RequestBody Corretor data) {
        return ResponseEntity.ok(seguradoService.updateCorretor(id, data));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return seguradoService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
