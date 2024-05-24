package com.galaxy.backend.repositories;

import com.galaxy.backend.models.Producao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProducaoRepository extends JpaRepository<Producao, Long>, JpaSpecificationExecutor<Producao> {

}
