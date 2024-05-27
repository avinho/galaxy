package com.galaxy.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public record AddProducaoDTO(
        Long corretoraId,
        LocalDate data,
        List<SaldoCorretorDTO> producoes
) {
}
