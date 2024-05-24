package com.galaxy.backend.controllers;

import com.galaxy.backend.models.Corretora;
import com.galaxy.backend.services.CorretoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/corretoras")
@CrossOrigin("*")
public class CorretoraController {

    private final CorretoraService corretoraService;

    public CorretoraController(CorretoraService corretoraService) {
        this.corretoraService = corretoraService;
    }

    @PostMapping
    public ResponseEntity<Corretora> cadastrar(@RequestBody Corretora corretora) {
        return ResponseEntity.status(HttpStatus.CREATED).body(corretoraService.save(corretora));
    }

    @GetMapping
    public ResponseEntity<List<Corretora>> listar() {
        return ResponseEntity.ok(corretoraService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Corretora> findById(@PathVariable Long id) {
        return ResponseEntity.ok(corretoraService.searchById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Corretora> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(corretoraService.searchCorretoraByName(name));
    }
}
