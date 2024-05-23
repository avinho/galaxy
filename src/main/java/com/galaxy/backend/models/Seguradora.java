package com.galaxy.backend.models;

import java.io.Serializable;
import java.util.Objects;

public class Seguradora implements Serializable {
    private String nome;
    private Integer quantidade = 0;
    private double alicota;
    private boolean retemImposto = false;
    private double saldo = 0;
    private double estornos = 0;
    private double creditos = 0;
    private double premioLiquido = 0;

    public Seguradora() {
    }

    public Seguradora(String nome) {
        this.nome = nome;
    }

    public Seguradora(String nome, Integer quantidade, double alicota, boolean retemImposto, double saldo, double estornos, double creditos, double premioLiquido) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.alicota = alicota;
        this.retemImposto = retemImposto;
        this.saldo = saldo;
        this.estornos = estornos;
        this.creditos = creditos;
        this.premioLiquido = premioLiquido;
    }

    public Seguradora(String nome, Integer quantidade, Double saldo, Double estornos, Double creditos, Double premioLiquido) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.saldo = saldo;
        this.estornos = estornos;
        this.creditos = creditos;
        this.premioLiquido = premioLiquido;
    }

    public double getAlicota() {
        return alicota;
    }

    public void setAlicota(double alicota) {
        this.alicota = alicota;
    }

    public boolean isRetemImposto() {
        return retemImposto;
    }

    public void setRetemImposto(boolean retemImposto) {
        this.retemImposto = retemImposto;
    }

    public void addSaldo(Double valor) {
        this.saldo += valor;
    }

    public double getPremioLiquido() {
        return premioLiquido;
    }

    public void addPremioLiquido(double premioLiquido) {
        this.premioLiquido += premioLiquido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void addQuantidade() {
        this.quantidade++;
    }

    public double getCreditos() {
        return creditos;
    }

    public void setCreditos(double creditos) {
        this.creditos += creditos;
    }

    public double getEstornos() {
        return estornos;
    }

    public void setEstornos(double estornos) {
        this.estornos += estornos;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seguradora that = (Seguradora) o;
        return Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNome());
    }

    @Override
    public String toString() {
        return "Seguradora{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", alicota=" + alicota +
                ", retemImposto=" + retemImposto +
                ", saldo=" + saldo +
                ", estornos=" + estornos +
                ", creditos=" + creditos +
                ", premioLiquido=" + premioLiquido +
                '}';
    }
}

