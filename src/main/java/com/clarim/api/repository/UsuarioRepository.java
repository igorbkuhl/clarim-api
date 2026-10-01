package com.clarim.api.repository;

import com.clarim.api.model.*;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Usuario> findByProviderId(String providerId);

    Optional<Usuario> findByStripeCustomerId(String stripeCustomerId);
}
