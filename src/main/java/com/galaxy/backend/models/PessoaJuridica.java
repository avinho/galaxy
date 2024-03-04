package com.galaxy.backend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Segurado {

    public PessoaJuridica() {
    }

    public PessoaJuridica(String name, String cnpj) {
        super(name, cnpj);
    }
}
