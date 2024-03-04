package com.galaxy.backend.dtos.mapper;

import com.galaxy.backend.dtos.PessoaFisicaDTO;
import com.galaxy.backend.dtos.PessoaJuridicaDTO;
import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.models.PessoaFisica;
import com.galaxy.backend.models.PessoaJuridica;
import com.galaxy.backend.models.Segurado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SeguradoMapper {

    SeguradoMapper INSTANCE = Mappers.getMapper(SeguradoMapper.class);

    @Mapping(target = "id", ignore = true)
    Segurado toEntity(SeguradoDTO dto);

    @Mapping(target = "id", ignore = true)
    PessoaFisica toEntity(PessoaFisicaDTO dto);

    @Mapping(target = "id", ignore = true)
    PessoaJuridica toEntity(PessoaJuridicaDTO dto);
    @Mapping(target = "tipo_segurado", source = "tipo")
    SeguradoDTO toDTO(Segurado segurado);

    PessoaFisicaDTO toDTO(PessoaFisica pessoa);

    PessoaJuridicaDTO toDTO(PessoaJuridica pessoa);

}
