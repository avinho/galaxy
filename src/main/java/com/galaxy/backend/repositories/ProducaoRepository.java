package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProducaoRepository extends JpaRepository<Producao, Long>, JpaSpecificationExecutor<Producao> {
}
