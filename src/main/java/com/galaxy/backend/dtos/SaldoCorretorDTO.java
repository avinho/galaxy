package com.galaxy.backend.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.galaxy.backend.models.Corretor;

import java.math.BigDecimal;
import java.util.List;

public record SaldoCorretorDTO(@JsonIgnoreProperties("email") Corretor corretor, int lancamentos, BigDecimal totalPremioLiquido, BigDecimal creditos, BigDecimal estornos, BigDecimal saldo, List<SaldoSeguradoraDTO> seguradoras) {
}
