package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelehlert.aluraflix.models.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

    @Query("SELECT obj FROM Videos obj " +
            "WHERE UPPER(obj.titulo) LIKE UPPER(CONCAT('%', :titulo, '%'))")
    Page<Videos> searchByName(String titulo, Pageable pageable);
    @Query(value = "select obj from Videos obj join fetch obj.categoria",
            countQuery = "select count(obj)from Videos obj join obj.categoria")
    Page<Videos> searchAll(Pageable pageable);
}
