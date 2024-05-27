package com.galaxy.backend.dtos;

import com.galaxy.backend.models.Corretor;

import java.math.BigDecimal;
import java.util.List;

public record SaldoCorretorDTO(
        Corretor corretor,
        int lancamentos,
        BigDecimal premioLiquido,
        BigDecimal creditos, BigDecimal estornos,
        BigDecimal saldo,
        List<SaldoSeguradoraDTO> seguradoras) {
}
