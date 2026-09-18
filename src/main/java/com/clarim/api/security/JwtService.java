package com.clarim.api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiracao-minutos}")
    private Long jwtExpiracaoMinutos;

    private SecretKey chave() {
        byte[] bytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String gerarToken(UsuarioAutenticado usuarioAutenticado) {
        Date agora = new Date();
        Date expiraEm = new Date(agora.getTime() + jwtExpiracaoMinutos * 60 * 1000);

        return Jwts.builder()
                .subject(usuarioAutenticado.getUsername())
                .claim("nome", usuarioAutenticado.getUsuario().getNome())
                .claim("papel", usuarioAutenticado.getUsuario().getPapel().name())
                .issuedAt(agora)
                .expiration(expiraEm)
                .signWith(this.chave())
                .compact();
    }
}
