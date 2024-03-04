package com.galaxy.backend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("PF")
public class PessoaFisica extends Segurado {

    private LocalDate birthDate;

    public PessoaFisica() {
    }

    public PessoaFisica(String name, String cpf, LocalDate birthDate) {
        super(name, cpf);
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
