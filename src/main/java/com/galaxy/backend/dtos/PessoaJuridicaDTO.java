package com.galaxy.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.galaxy.backend.models.Address;
import com.galaxy.backend.models.Corretor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PessoaJuridicaDTO(Long id, String name, @NotBlank @Min(11) String document,  @JsonIgnoreProperties("id") Address address, @JsonIgnoreProperties("id") Corretor corretor) {
}
