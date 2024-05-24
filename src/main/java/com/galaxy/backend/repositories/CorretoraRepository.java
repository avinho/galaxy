package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Corretora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorretoraRepository extends JpaRepository<Corretora, Long> {
    Corretora findByNameIgnoreCase(String name);
}
