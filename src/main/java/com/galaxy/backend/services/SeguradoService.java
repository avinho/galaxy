package com.galaxy.backend.services;

import com.galaxy.backend.dtos.SeguradoDTO;
import com.galaxy.backend.dtos.SeguradoPageDTO;
import com.galaxy.backend.dtos.mapper.SeguradoMapper;
import com.galaxy.backend.models.Segurado;
import com.galaxy.backend.repositories.SeguradoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

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

    public SeguradoPageDTO listAll(int pageNumber, int pageSize) {
        long totalElements = seguradoRepository.count();

        if (pageNumber > 0 && pageSize > totalElements) {
            Page<Segurado> page = seguradoRepository.findAll(PageRequest.of(0, pageSize));
            List<SeguradoDTO> segurados = page.getContent().stream().map(seguradoMapper::toDTO).toList();
            return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
        }

        Page<Segurado> page = seguradoRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<SeguradoDTO> segurados = page.getContent().stream().map(seguradoMapper::toDTO).toList();
        return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
    }

    public SeguradoPageDTO searchByName(String name, int pageNumber, int pageSize) {
        long totalElements = seguradoRepository.count();

        if (pageNumber > 0 && pageSize > totalElements) {
            Page<Segurado> page = seguradoRepository.findSeguradoByNameContainingIgnoreCase(name, PageRequest.of(0, pageSize));
            List<SeguradoDTO> segurados = page.getContent().stream().map(seguradoMapper::toDTO).toList();
            return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
        }

        Page<Segurado> page = seguradoRepository.findSeguradoByNameContainingIgnoreCase(name, PageRequest.of(pageNumber, pageSize));
        List<SeguradoDTO> segurados = page.getContent().stream().map(seguradoMapper::toDTO).toList();
        return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
    }

    public SeguradoDTO update(@NotNull @Positive Long id, @Valid @NotNull SeguradoDTO data) {
        return seguradoRepository.findById(id).map(segurado -> {
            segurado.setName(data.name());
            segurado.setCpf_cnpj(data.cpf_cnpj());
            return seguradoMapper.toDTO(seguradoRepository.save(segurado));
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public boolean delete(@NotNull @Positive Long id) {
        if (seguradoRepository.existsById(id)) {
            seguradoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
