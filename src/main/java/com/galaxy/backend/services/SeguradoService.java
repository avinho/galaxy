package com.galaxy.backend.services;

import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.dtos.mapper.SeguradoMapper;
import com.galaxy.backend.models.Segurado;
import com.galaxy.backend.repositories.SeguradoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class SeguradoService {

    private final SeguradoRepository seguradoRepository;
    private final SeguradoMapper seguradoMapper;

    public SeguradoService(SeguradoRepository seguradoRepository, SeguradoMapper seguradoMapper) {
        this.seguradoRepository = seguradoRepository;
        this.seguradoMapper = seguradoMapper;
    }

    public SeguradoDTO save(SeguradoDTO data) {
        return seguradoMapper.toDTO(seguradoRepository.save(seguradoMapper.toEntity(data)));
    }

    public SeguradoDTO findById(@NotNull @Positive Long id) {
        return seguradoRepository.findById(id).map(seguradoMapper::toDTO).orElseThrow(null);
    }

    public List<Segurado> findAll() {
        return seguradoRepository.findAll();
    }

    public SeguradoDTO update(@NotNull @Positive Long id, @Valid @NotNull SeguradoDTO data) {
        return seguradoRepository.findById(id).map(segurado -> {
            segurado.setName(data.name());
            segurado.setCpf_cnpj(data.cpf_cnpj());
            return seguradoMapper.toDTO(seguradoRepository.save(segurado));
        }).orElse(null);
    }

    public boolean delete(@NotNull @Positive Long id) {
        seguradoRepository.delete(seguradoRepository.findById(id).orElseThrow(null));
        return true;
    }
}
