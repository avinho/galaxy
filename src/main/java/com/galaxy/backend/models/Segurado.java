package com.galaxy.backend.models;

import com.galaxy.backend.enums.Status;
import com.galaxy.backend.enums.converters.StatusConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

@Entity
@SQLDelete(sql = "UPDATE Segurado SET status = 'Inactive' WHERE id = ?")
@SQLRestriction("status = 'Active'")
public class Segurado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 11, max = 14)
    @Column(length = 14, nullable = false)
    private String cpf_cnpj;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    public Segurado() {
    }

    public Segurado(String name, String cpf_cnpj, Status status) {
        this.name = name;
        this.cpf_cnpj = cpf_cnpj;
        this.status = status;
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

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Segurado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf_cnpj='" + cpf_cnpj + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
