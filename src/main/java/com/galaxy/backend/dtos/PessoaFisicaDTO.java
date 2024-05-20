package com.galaxy.backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.galaxy.backend.models.Address;
import com.galaxy.backend.models.Corretor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public record PessoaFisicaDTO (Long id, String name, String document, @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate birthDate,  @JsonIgnoreProperties("id") Address address, @JsonIgnoreProperties("id") Corretor corretor) {
}
