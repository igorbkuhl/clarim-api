package com.clarim.api.dto;

import jakarta.validation.constraints.*;

public record UsuarioCadastro(
        @NotBlank @Size(max = 120) String nome,
        @NotBlank @Email String email,

        // retorna erro para o frontend caso a senha seja muito curta
        @NotBlank @Size(min = 8, message = "A senha precisa de no mínimo 8 caracteres")
        String senha) {}
