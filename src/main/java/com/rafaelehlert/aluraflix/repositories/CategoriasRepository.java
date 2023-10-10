package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelehlert.aluraflix.models.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    
}
