package com.galaxy.backend.repositories.specifications;

import com.galaxy.backend.models.Producao;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ProducaoSpecification {
    public static Specification<Producao> byCorretoraId(Long corretoraId) {
        return (root, query, cb) -> corretoraId == null ? cb.conjunction() : cb.equal(root.get("corretora").get("id"), corretoraId);
    }

    public static Specification<Producao> byDataBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null || endDate == null) {
                return cb.conjunction();
            }
            return cb.between(root.get("data"), startDate, endDate);
        };
    }
}
