package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelehlert.aluraflix.models.Videos;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {
    
}
