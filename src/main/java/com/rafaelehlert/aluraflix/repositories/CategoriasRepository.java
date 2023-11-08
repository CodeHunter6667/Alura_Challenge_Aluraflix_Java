package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelehlert.aluraflix.models.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    @Query(value = "select obj from Categorias obj " +
            "join fetch obj.videos where obj.id = :id")
    Page<Categorias> searchWithVideos(Long id, Pageable pageable);
}
