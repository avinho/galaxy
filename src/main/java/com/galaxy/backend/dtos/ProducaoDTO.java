package com.galaxy.backend.dtos;

import java.time.LocalDate;

public record ProducaoDTO(
        long corretorId,
        long corretoraId,
        LocalDate data,
        double premioLiquido,
        double creditos,
        double estornos,
        double saldo
) {
}
