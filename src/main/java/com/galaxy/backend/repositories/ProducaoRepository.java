package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {
    List<Producao> findProducaoByCorretorId(Long id);
}
