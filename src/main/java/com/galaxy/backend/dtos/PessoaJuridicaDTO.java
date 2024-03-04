package com.galaxy.backend.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PessoaJuridicaDTO(Long id, String name, @NotBlank @Min(11) String document) {
}
