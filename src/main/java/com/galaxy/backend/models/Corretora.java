package com.galaxy.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Corretora implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String razaoSocial;
    private String cnpj;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "corretora_corretor",
            joinColumns = @JoinColumn(name = "id_corretora"),
            inverseJoinColumns = @JoinColumn(name = "id_corretor"))
    private List<Corretor> corretores = new ArrayList<>();

    public Corretora() {}

    public Corretora(String name, String razaoSocial, String cnpj) {}

    public Corretora(Long id, String name, String razaoSocial, String cnpj) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Corretor> getCorretores() {
        return corretores;
    }

    public void setCorretores(List<Corretor> corretores) {
        this.corretores = corretores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corretora corretora = (Corretora) o;
        return Objects.equals(id, corretora.id) && Objects.equals(name, corretora.name) && Objects.equals(cnpj, corretora.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj);
    }

    @Override
    public String toString() {
        return "Corretora{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", corretores=" + corretores +
                '}';
    }
}