package com.clarim.api.repository;

import com.clarim.api.model.Assinatura;
import com.clarim.api.model.StatusAssinatura;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Optional<Assinatura> findByStripeSubscriptionId(String stripeSubscriptionId);

    List<Assinatura> findByUsuarioIdOrderByCriadoEmDesc(Long usuarioId);

    boolean existsByUsuarioIdAndPlanoAtivoTrue(
            Long usuarioId, java.util.Collection<StatusAssinatura> status, OffsetDateTime agora);
}
