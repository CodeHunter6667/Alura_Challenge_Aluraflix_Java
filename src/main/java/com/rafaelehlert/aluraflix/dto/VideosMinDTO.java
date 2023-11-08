package com.rafaelehlert.aluraflix.dto;

import com.rafaelehlert.aluraflix.models.Videos;

public class VideosMinDTO   {
    private Long id;
    private String titulo;


    public VideosMinDTO(Videos entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
    }

    public VideosMinDTO(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
