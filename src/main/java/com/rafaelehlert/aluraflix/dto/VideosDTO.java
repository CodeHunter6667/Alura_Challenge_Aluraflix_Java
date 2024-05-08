package com.rafaelehlert.aluraflix.dto;

import com.rafaelehlert.aluraflix.models.Videos;

public class VideosDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private Long categoriaId;

    public VideosDTO(Long id, String titulo, String descricao, String url, Long categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categoriaId = categoriaId;
    }

    public VideosDTO(Videos entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        descricao = entity.getDescricao();
        url = entity.getUrl();
        categoriaId = entity.getCategoria().getId();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

}


