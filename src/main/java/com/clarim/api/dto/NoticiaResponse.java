package com.clarim.api.dto;

import java.time.OffsetDateTime;

public record NoticiaResponse(
        Long id,
        String titulo,
        String slug,
        String texto,
        String categoria,
        String autor,
        boolean premium,
        OffsetDateTime publicadaEm) {}
