package com.galaxy.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ProducaoDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int lancamentos;
    private BigDecimal premioLiquido;
    private BigDecimal creditos;
    private BigDecimal estornos;
    private BigDecimal saldo;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producao_id", nullable = false)
    private Producao producao;

    @ManyToOne
    @JoinColumn(name= "corretor_id", nullable = false)
    private Corretor corretor;

    public ProducaoDetail() {
    }

    public ProducaoDetail(int lancamentos, BigDecimal premioLiquido, BigDecimal creditos, BigDecimal estornos, BigDecimal saldo, Producao producao, Corretor corretor) {
        this.lancamentos = lancamentos;
        this.premioLiquido = premioLiquido;
        this.creditos = creditos;
        this.estornos = estornos;
        this.saldo = saldo;
        this.producao = producao;
        this.corretor = corretor;
    }

    public ProducaoDetail(Long id, Integer lancamentos, BigDecimal premioLiquido, BigDecimal creditos, BigDecimal estornos, BigDecimal saldo, Producao producao, Corretor corretor) {
        this.id = id;
        this.lancamentos = lancamentos;
        this.premioLiquido = premioLiquido;
        this.creditos = creditos;
        this.estornos = estornos;
        this.saldo = saldo;
        this.producao = producao;
        this.corretor = corretor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(int lancamentos) {
        this.lancamentos = lancamentos;
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

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProducaoDetail that = (ProducaoDetail) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProducaoDetail{" +
                "id=" + id +
                ", lancamentos=" + lancamentos +
                ", premioLiquido=" + premioLiquido +
                ", creditos=" + creditos +
                ", estornos=" + estornos +
                ", saldo=" + saldo +
                ", producao=" + producao +
                ", corretor=" + corretor +
                '}';
    }
}
