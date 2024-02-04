package com.galaxy.backend.dtos.mapper;

import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.models.Segurado;
import org.springframework.stereotype.Component;

@Component
public class SeguradoMapper {
    public SeguradoDTO toDTO(Segurado segurado) {
        if(segurado == null) {
            return null;
        }
        return new SeguradoDTO(segurado.getId(), segurado.getName(), segurado.getCpf_cnpj());
    }

    public Segurado toEntity(SeguradoDTO dto) {
        if(dto == null) {
            return null;
        }
        Segurado segurado = new Segurado();
        if(dto.id() != null) {
            segurado.setId(dto.id());
        }
        segurado.setName(dto.name());
        segurado.setCpf_cnpj(dto.cpf_cnpj());
        return segurado;
    }
}
