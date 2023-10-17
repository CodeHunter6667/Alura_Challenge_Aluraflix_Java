package com.rafaelehlert.aluraflix.services;

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

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Transactional(readOnly = true)
    public VideosDTO findById(Long id) {
        Videos videos = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new VideosDTO(videos);
    }

    @Transactional(readOnly = true)
    public Page<VideosDTO> findAll(Pageable pageable) {
        Page<Videos> result = repository.findAll(pageable);
        return result.map(x -> new VideosDTO(x));
    }

    @Transactional
    public VideosDTO insert(VideosDTO dto) {
        Videos entity = new Videos();
        if (dto.getCategoriaId() == null) {
            categoriasRepository.findById(1L).map(categoria -> {
            dto.setCategorias(categoria);
            copyDtoToEntity(dto, entity);
            return repository.save(entity);
        }).orElseThrow(() ->  new ResourceNotFoundException("Recusro não encontrado"));
        } else {
            categoriasRepository.findById(dto.getCategoriaId()).map(categoria -> {
                dto.setCategorias(categoria);
                copyDtoToEntity(dto, entity);
                return repository.save(entity);
            }).orElseThrow(() -> new ResourceNotFoundException("Recusro não encontrado"));
        }
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
        entity.setCategoria(dto.getCategorias());
        }
}