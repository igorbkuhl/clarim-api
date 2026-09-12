package com.clarim.api.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String slug;

    @ManyToMany(mappedBy = "tags")
    private Set<Noticia> noticias = new HashSet<>();

    public Tag() {}

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public Set<Noticia> getNoticias() {
        return this.noticias;
    }
}
