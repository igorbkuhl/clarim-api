package com.clarim.api.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 180)
    private String email;

    @Column(length = 72)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Papel papel = Papel.LEITOR;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Provider provider = Provider.LOCAL;

    @Column(length = 120)
    private String providerId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String avatarUrl;

    @Column(nullable = false, length = 60)
    private String stripeCustomerId;

    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime criadoEm = OffsetDateTime.now();

    public Usuario() {
    }

    public Long getId() {
        return this.id;
    }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return this.senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Papel getPapel() {
        return this.papel;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getStripeCustomerId() {
        return this.stripeCustomerId;
    }

    // public void setStripeCustomerId(String stripeCustomerId) {
    // this.stripeCustomerId = stripeCustomerId;
    // }

    public OffsetDateTime getCriadoEm() {
        return this.criadoEm;
    }

    public Provider getProvider() {
        return this.provider;
    }

    // public void setProvider(String provider) {
    // this.provider = provider;
    // }
}
