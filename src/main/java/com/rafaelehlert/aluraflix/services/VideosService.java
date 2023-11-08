package com.rafaelehlert.aluraflix.services;

import com.rafaelehlert.aluraflix.dto.VideosMinDTO;
import com.rafaelehlert.aluraflix.models.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelehlert.aluraflix.dto.VideosDTO;
import com.rafaelehlert.aluraflix.models.Videos;
import com.rafaelehlert.aluraflix.repositories.CategoriasRepository;
import com.rafaelehlert.aluraflix.repositories.VideosRepository;
import com.rafaelehlert.aluraflix.services.exceptions.DatabaseException;
import com.rafaelehlert.aluraflix.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class VideosService {

    @Autowired
    private VideosRepository repository;

    @Transactional(readOnly = true)
    public VideosDTO findById(Long id) {
        Videos videos = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new VideosDTO(videos);
    }

    @Transactional(readOnly = true)
    public Page<VideosDTO> searchAll(Pageable pageable) {
        Page<Videos> result = repository.searchAll(pageable);
        return result.map(x -> new VideosDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<VideosMinDTO> serachByName(String titulo, Pageable pageable){
        Page<Videos> result = repository.searchByName(titulo, pageable);
        return result.map(x -> new VideosMinDTO(x));
    }

    @Transactional
    public VideosDTO insert(VideosDTO dto) {
        Videos entity = new Videos();
        copyDtoToEntity(dto, entity);
        Categorias cat = new Categorias();
        cat.setId(dto.getCategoriaId());
        if (cat.getId() == null){
            cat.setId(1L);
        }
        entity.setCategoria(cat);
        repository.save(entity);
        return new VideosDTO(entity);
    }

    @Transactional
    public VideosDTO update(Long id, VideosDTO dto) {
        try {
            Videos entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new VideosDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade relacional");
        }
    }
    
    private void copyDtoToEntity(VideosDTO dto, Videos entity) {
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setUrl(dto.getUrl());
        }
}