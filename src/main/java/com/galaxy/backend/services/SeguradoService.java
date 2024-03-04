package com.galaxy.backend.services;

import com.galaxy.backend.dtos.PessoaFisicaDTO;
import com.galaxy.backend.dtos.PessoaJuridicaDTO;
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

    public SeguradoService(SeguradoRepository seguradoRepository) {
        this.seguradoRepository = seguradoRepository;
    }

    public PessoaFisicaDTO savePF(PessoaFisicaDTO data) {
        return SeguradoMapper.INSTANCE.toDTO(seguradoRepository.save(SeguradoMapper.INSTANCE.toEntity(data)));
    }

    public PessoaJuridicaDTO savePJ(PessoaJuridicaDTO data) {
        return SeguradoMapper.INSTANCE.toDTO(seguradoRepository.save(SeguradoMapper.INSTANCE.toEntity(data)));
    }

    public SeguradoDTO findById(@NotNull @Positive Long id) {
        return seguradoRepository.findById(id).map(SeguradoMapper.INSTANCE::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<SeguradoDTO> findAll() {
        return seguradoRepository.findAll().stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
    }

    public List<PessoaFisicaDTO> findSeguradoByTipoPF(String tipo) {
        return seguradoRepository.findSeguradoByTipoIgnoreCase(tipo).stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
    }

    public SeguradoPageDTO listAll(int pageNumber, int pageSize) {
        long totalElements = seguradoRepository.count();

        if (pageNumber > 0 && pageSize > totalElements) {
            Page<Segurado> page = seguradoRepository.findAll(PageRequest.of(0, pageSize));
            List<SeguradoDTO> segurados = page.getContent().stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
            return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
        }

        Page<Segurado> page = seguradoRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<SeguradoDTO> segurados = page.getContent().stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
        return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
    }

    public SeguradoPageDTO searchByName(String name, int pageNumber, int pageSize) {
        long totalElements = seguradoRepository.count();

        if (pageNumber > 0 && pageSize > totalElements) {
            Page<Segurado> page = seguradoRepository.findSeguradoByNameContainingIgnoreCase(name, PageRequest.of(0, pageSize));
            List<SeguradoDTO> segurados = page.getContent().stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
            return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
        }

        Page<Segurado> page = seguradoRepository.findSeguradoByNameContainingIgnoreCase(name, PageRequest.of(pageNumber, pageSize));
        List<SeguradoDTO> segurados = page.getContent().stream().map(SeguradoMapper.INSTANCE::toDTO).toList();
        return new SeguradoPageDTO(segurados, page.getTotalPages(), page.getTotalElements());
    }

    public SeguradoDTO update(@NotNull @Positive Long id, @Valid @NotNull SeguradoDTO data) {
        return seguradoRepository.findById(id).map(segurado -> {
            segurado.setName(data.name());
            //segurado.setCpf_cnpj(data.cpf_cnpj());
            return SeguradoMapper.INSTANCE.toDTO(seguradoRepository.save(segurado));
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
