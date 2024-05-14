package com.rafaelehlert.aluraflix.controllers;

import com.rafaelehlert.aluraflix.dto.CategoriaMinDTO;
import com.rafaelehlert.aluraflix.dto.CategoriasDTO;
import com.rafaelehlert.aluraflix.services.CategoriasServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
@SecurityRequirement(name = "bearer-key")
public class CategoriasController {
    @Autowired
    private CategoriasServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriasDTO> findById(@PathVariable Long id) {
        CategoriasDTO dto = services.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/videos")
    public ResponseEntity<Page<CategoriaMinDTO>> findWithVideos(@PathVariable Long id,
                                                                @PageableDefault(size = 5) Pageable pageable){
        Page<CategoriaMinDTO> dto = services.searchWithVideos(id, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CategoriasDTO>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<CategoriasDTO> dto = services.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CategoriasDTO> insert(@Valid @RequestBody CategoriasDTO dto) {
        dto = services.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<CategoriasDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriasDTO dto) {
        dto = services.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }
}
