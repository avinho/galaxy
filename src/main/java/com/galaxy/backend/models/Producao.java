package com.galaxy.backend.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Producao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private BigDecimal premioLiquido;
    private BigDecimal creditos;
    private BigDecimal estornos;
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name="corretora_id", nullable=false)
    private Corretora corretora;

    @ManyToOne
    @JoinColumn(name= "corretor_id", nullable = false)
    private Corretor corretor;

    public Producao() {
    }

    public Producao(Long id, LocalDate data, BigDecimal premioLiquido, BigDecimal creditos, BigDecimal estornos, BigDecimal saldo, Corretora corretora, Corretor corretor) {
        this.id = id;
        this.data = data;
        this.premioLiquido = premioLiquido;
        this.creditos = creditos;
        this.estornos = estornos;
        this.saldo = saldo;
        this.corretora = corretora;
        this.corretor = corretor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getPremioLiquido() {
        return premioLiquido;
    }

    public void setPremioLiquido(BigDecimal premioLiquido) {
        this.premioLiquido = premioLiquido;
    }

    public BigDecimal getCreditos() {
        return creditos;
    }

    public void setCreditos(BigDecimal creditos) {
        this.creditos = creditos;
    }

    public BigDecimal getEstornos() {
        return estornos;
    }

    public void setEstornos(BigDecimal estornos) {
        this.estornos = estornos;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    public void setCorretora(Corretora corretora) {
        this.corretora = corretora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producao producao = (Producao) o;
        return Objects.equals(id, producao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Producao{" +
                "id=" + id +
                ", data=" + data +
                ", premioLiquido=" + premioLiquido +
                ", creditos=" + creditos +
                ", estornos=" + estornos +
                ", saldo=" + saldo +
                ", corretor=" + corretor +
                '}';
    }
}
