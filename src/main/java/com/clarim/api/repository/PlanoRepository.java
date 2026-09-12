package com.clarim.api.repository;

import com.clarim.api.model.Plano;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<Plano, Long> {
    Optional<Plano> findByAtivoTrue();

    Optional<Plano> findByStripePriceId(String stripePriceId);
}
