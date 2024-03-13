package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.PessoaJuridicaDTO;
import com.galaxy.backend.services.SeguradoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/segurados/pj")
public class PessoaJuridicaController {

    private final SeguradoService seguradoService;

    public PessoaJuridicaController(SeguradoService seguradoService) {
        this.seguradoService = seguradoService;
    }

    @PostMapping
    public ResponseEntity<PessoaJuridicaDTO> createPJ(@RequestBody PessoaJuridicaDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(seguradoService.savePJ(data));
    }
}
