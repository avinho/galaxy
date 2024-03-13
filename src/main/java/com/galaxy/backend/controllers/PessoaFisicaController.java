package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.PessoaFisicaDTO;
import com.galaxy.backend.services.SeguradoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/segurados/pf")
@CrossOrigin(origins = "*")
public class PessoaFisicaController {

    private final SeguradoService seguradoService;

    public PessoaFisicaController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @PostMapping
    public ResponseEntity<PessoaFisicaDTO> createPF(@RequestBody PessoaFisicaDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguradoService.savePF(data));
    }
}
