package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelehlert.aluraflix.models.Videos;

public interface VideosRepository extends JpaRepository<Videos, Long> {
    
}
