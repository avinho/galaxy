package com.galaxy.backend.dtos;

import java.time.LocalDate;

public record ProducaoDTO(
        long corretorId,
        String corretora,
        LocalDate data,
        double premioLiquido,
        double creditos,
        double estornos,
        double saldo
) {
}
