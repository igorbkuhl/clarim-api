package com.clarim.api.repository;

import com.clarim.api.model.Tag;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findBySlug(String slug);

    Set<Tag> findBySlugIn(Collection<String> slugs);
}
