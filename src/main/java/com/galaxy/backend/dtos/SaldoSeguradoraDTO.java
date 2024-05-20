package com.galaxy.backend.dtos;

import java.math.BigDecimal;

public record SaldoSeguradoraDTO(String nome, BigDecimal premioLiquido, BigDecimal saldo, Integer quantidade) {
}
