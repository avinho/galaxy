package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Producao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long> {
    List<Producao> findProducaoByCorretorId(Long id);
    List<Producao> findByCorretoraContainsIgnoreCase(String corretora);
    List<Producao> findByCorretoraContainsIgnoreCaseAndDataBetween(String corretora, LocalDate startDate, LocalDate endDate);
}
