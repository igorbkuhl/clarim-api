package com.clarim.api.service;

import com.clarim.api.model.Papel;
import com.clarim.api.model.Provider;
import com.clarim.api.model.Usuario;
import com.clarim.api.repository.UsuarioRepository;
import com.clarim.api.security.DadosGoogle;
import java.util.Optional;
import javax.security.auth.login.CredentialException;
import org.springframework.stereotype.Service;

@Service
public class GoogleAuthService {
    private final UsuarioRepository usuarioRepository;

    public GoogleAuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario entrarOuCadastrar(DadosGoogle dadosGoogle) throws CredentialException {
        if (!dadosGoogle.emailVerified()) {
            throw new CredentialException("O e-mail desta conta Google não está verificado.");
        }

        return usuarioRepository
                .findByProviderId(dadosGoogle.id())
                .or(() -> vincularPorEmail(dadosGoogle))
                .orElseGet(() -> criarConta(dadosGoogle));
    }

    private Optional<Usuario> vincularPorEmail(DadosGoogle dadosGoogle) {
        return this.usuarioRepository.findByEmail(dadosGoogle.email()).map(usuario -> {
            usuario.setProviderId(dadosGoogle.id());
            return usuario;
        });
    }

    private Usuario criarConta(DadosGoogle dadosGoogle) {
        Usuario usuario = new Usuario();
        usuario.setEmail(dadosGoogle.email());
        usuario.setNome(dadosGoogle.nome());
        usuario.setSenhaHash(null);
        usuario.setProvider(Provider.GOOGLE);
        usuario.setProviderId(dadosGoogle.id());
        usuario.setPapel(Papel.LEITOR);
        return usuario;
    }
}
