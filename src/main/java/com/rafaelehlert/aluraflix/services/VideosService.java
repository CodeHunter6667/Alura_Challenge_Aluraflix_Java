package com.rafaelehlert.aluraflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelehlert.aluraflix.dto.VideosDTO;
import com.rafaelehlert.aluraflix.models.Videos;
import com.rafaelehlert.aluraflix.repositories.VideosRepository;
import com.rafaelehlert.aluraflix.services.exceptions.ResourceNotFoundException;


@Service
public class VideosService {

    @Autowired
    private VideosRepository repository;

    @Transactional(readOnly = true)
    public VideosDTO findById(Long id) {
        Videos videos = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new VideosDTO(videos.getId(), videos.getTitulo(), videos.getDescricao(), videos.getUrl());
    }

    @Transactional(readOnly = true)
    public Page<VideosDTO> findAll(Pageable pageable) {
        Page<Videos> result = repository.findAll(pageable);
        return result.map(x -> new VideosDTO(x.getId(), x.getTitulo(), x.getDescricao(),x.getUrl()));
    }

    public VideosDTO insert(VideosDTO dto) {
        Videos entity = new Videos();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new VideosDTO(entity.getId(), entity.getTitulo(), entity.getDescricao(), entity.getUrl());
    }

    private void copyDtoToEntity(VideosDTO dto, Videos entity) {
        entity.setId(dto.id());
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setUrl(dto.url());
    }
}
