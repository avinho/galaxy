package com.galaxy.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SeguradoDTO(Long id, @NotNull @NotBlank String name, @NotNull @NotBlank String cpf_cnpj) {
}