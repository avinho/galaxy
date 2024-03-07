package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Segurado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeguradoRepository extends JpaRepository<Segurado, Long> {

    Page<Segurado> findSeguradoByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Segurado> findSeguradoByTipoIgnoreCase(String tipo);

    long countByTipoIgnoreCase(String tipo);
}
