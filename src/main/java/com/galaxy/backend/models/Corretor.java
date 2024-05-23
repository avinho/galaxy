package com.galaxy.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Corretor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @JsonBackReference
    @OneToMany(mappedBy = "corretor")
    private List<Segurado> segurados = new ArrayList<>();

    public Corretor() {
    }

    public Corretor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Corretor(String name) {
        this.name = name;
    }

    public Corretor(String name, String email, Segurado segurado) {
        this.name = name;
        this.email = email;
        this.segurados.add(segurado);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Segurado> getSegurados() {
        return segurados;
    }

    public void setSegurado(List<Segurado> segurados) {
        this.segurados = segurados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corretor corretor = (Corretor) o;
        return Objects.equals(id, corretor.id) && Objects.equals(name, corretor.name) && Objects.equals(email, corretor.email) && Objects.equals(segurados, corretor.segurados);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, segurados);
    }

    @Override
    public String toString() {
        return "Corretor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", segurados=" + segurados +
                '}';
    }
}
