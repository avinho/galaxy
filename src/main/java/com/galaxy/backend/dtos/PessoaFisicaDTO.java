package com.galaxy.backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record PessoaFisicaDTO (Long id, String name, String document, @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate birthDate) {
}
