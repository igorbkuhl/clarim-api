// Decide o que mostrar, independente do que o banco guarda.

package com.clarim.api.dto;

import java.time.OffsetDateTime;

public record NoticiaResumo(
        Long id,
        String titulo,
        String slug,
        String resumo,
        String categoria,
        String texto,
        boolean premium,
        OffsetDateTime publicadaEm) {}
