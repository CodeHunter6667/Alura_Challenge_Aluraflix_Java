package com.rafaelehlert.aluraflix.dto;

import com.rafaelehlert.aluraflix.models.Categorias;
import com.rafaelehlert.aluraflix.models.Videos;

public class VideosDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private Long categoriaId;
    private Categorias categorias;

    public VideosDTO(Long id, String titulo, String descricao, String url, Categorias categorias) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.categorias = categorias;
    }

    public VideosDTO(Videos entity) {
        id = entity.getId();
        titulo = entity.getTitulo();
        descricao = entity.getDescricao();
        url = entity.getUrl();
        categorias = entity.getCategoria();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
}
