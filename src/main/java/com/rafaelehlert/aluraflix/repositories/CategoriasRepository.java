package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelehlert.aluraflix.models.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    
}
