package com.galaxy.backend.models;

import com.galaxy.backend.enums.Status;
import com.galaxy.backend.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@SQLDelete(sql = "UPDATE Segurado SET status = 'Inactive' WHERE id = ?")
@SQLRestriction("status = 'Active'")
public class Segurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 11 , max = 14)
    @Column(length = 14, nullable = false, unique = true)
    private String document;

    @Column(insertable = false, updatable = false, nullable = false)
    private String tipo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    public Segurado() {
    }

    public Segurado(String name, String document) {
        this.name = name;
        this.document = document;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipoSegurado) {
        this.tipo = tipoSegurado;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Segurado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", tipo='" + tipo + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segurado segurado = (Segurado) o;
        return Objects.equals(id, segurado.id) && Objects.equals(name, segurado.name) && Objects.equals(document, segurado.document) && Objects.equals(tipo, segurado.tipo) && status == segurado.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, document, tipo, status);
    }
}
