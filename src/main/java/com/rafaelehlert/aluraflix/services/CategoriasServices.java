package com.rafaelehlert.aluraflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelehlert.aluraflix.dto.CategoriasDTO;
import com.rafaelehlert.aluraflix.models.Categorias;
import com.rafaelehlert.aluraflix.repositories.CategoriasRepository;
import com.rafaelehlert.aluraflix.services.exceptions.DatabaseException;
import com.rafaelehlert.aluraflix.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriasServices {
    
    @Autowired
    private CategoriasRepository repository;

    @Transactional(readOnly = true)
    public CategoriasDTO findById(Long id) {
        Categorias categorias = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new CategoriasDTO(categorias.getId(), categorias.getTitulo(), categorias.getCor());
    }

    @Transactional(readOnly = true)
    public Page<CategoriasDTO> findAll(Pageable pageable) {
        Page<Categorias> result = repository.findAll(pageable);
        return result.map(x -> new CategoriasDTO(x.getId(), x.getTitulo(), x.getCor()));
    }

    @Transactional
    public CategoriasDTO insert(CategoriasDTO dto) {
        Categorias entity = new Categorias();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CategoriasDTO(entity.getId(), entity.getTitulo(), entity.getCor());
    }

    @Transactional
    public CategoriasDTO update(Long id, CategoriasDTO dto) {
        try {
            Categorias entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CategoriasDTO(entity.getId(), entity.getTitulo(), entity.getCor());
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

    private void copyDtoToEntity(CategoriasDTO dto, Categorias entity) {
        entity.setId(dto.id());
        entity.setTitulo(dto.titulo());
        entity.setCor(dto.cor());
    }
}
