package com.clarim.api.repository;

import com.clarim.api.model.Categoria;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Override
    Optional<Categoria> findById(Long id);
}
