package com.rafaelehlert.aluraflix.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelehlert.aluraflix.dto.CategoriasDTO;
import com.rafaelehlert.aluraflix.services.CategoriasServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasController {
    @Autowired
    private CategoriasServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriasDTO> findById(@PathVariable Long id) {
        CategoriasDTO dto = services.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriasDTO>> findAll(Pageable pageable) {
        Page<CategoriasDTO> dto = services.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriasDTO> insert(@Valid @RequestBody CategoriasDTO dto) {
        dto = services.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping
    public ResponseEntity<CategoriasDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriasDTO dto) {
        dto = services.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
