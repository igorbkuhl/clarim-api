package com.clarim.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "assinatura")
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Plano plano;

    @Column(name = "stripe_subscription_id", nullable = false, unique = true, length = 60)
    private String stripeSubscriptionId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssinatura status;

    @Column(name = "periodo_fim", nullable = false)
    private OffsetDateTime periodoFim;

    @Column(name = "cancelar_ao_fim", nullable = false)
    private Boolean cancelarAoFim = false;

    @Column(name = "criado_em", nullable = false)
    private OffsetDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private OffsetDateTime atualizadoEm;

    @PreUpdate
    private void aoAtualizar() {
        this.atualizadoEm = OffsetDateTime.now();
    }

    public boolean estaVigente() {
        boolean statusPermiteAcesso =
                this.status == StatusAssinatura.ACTIVE || this.status == StatusAssinatura.TRIALING;
        boolean dentroDoPeriodo = this.periodoFim != null && this.periodoFim.isAfter(OffsetDateTime.now());

        return statusPermiteAcesso && dentroDoPeriodo;
    }
}
