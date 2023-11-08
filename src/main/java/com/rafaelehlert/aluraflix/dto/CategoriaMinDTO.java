package com.rafaelehlert.aluraflix.dto;

import com.rafaelehlert.aluraflix.models.Videos;

import java.util.List;

public record CategoriaMinDTO(Long id, List<Videos> videos) {
}
