package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CorretorRepository extends JpaRepository<Corretor, Long> {
    Optional<Corretor> findByNameIsIgnoreCase(String name);
}
