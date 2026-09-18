package com.clarim.api.security;

import com.clarim.api.model.Usuario;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioAutenticado implements UserDetails {
    private final Usuario usuario;

    public UsuarioAutenticado(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public String getPassword() {
        return usuario.getSenhaHash();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String papel = "ROLE_" + usuario.getPapel().name();
        return List.of(new SimpleGrantedAuthority(papel));
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
}
