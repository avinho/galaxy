package com.galaxy.backend.controllers;

import com.galaxy.backend.models.Corretor;
import com.galaxy.backend.services.CorretorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/corretores")
public class CorretorController {

    private final CorretorService corretorService;

    public CorretorController(CorretorService corretorService) {
        this.corretorService = corretorService;
    }

    @PostMapping
    public ResponseEntity<Corretor> addCorretor(@RequestBody Corretor corretor) {
        return ResponseEntity.ok(corretorService.save(corretor));
    }

    @GetMapping
    public ResponseEntity<List<Corretor>> loadAll() {
        return ResponseEntity.ok(corretorService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Corretor> findByName(@RequestParam String name) {
        return ResponseEntity.ok(corretorService.findByName(name));
    }
}
