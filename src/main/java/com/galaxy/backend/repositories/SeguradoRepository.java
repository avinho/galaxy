package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Segurado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeguradoRepository extends JpaRepository<Segurado, Long> {
}
