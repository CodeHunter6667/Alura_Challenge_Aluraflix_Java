package com.rafaelehlert.aluraflix.controllers;

import com.rafaelehlert.aluraflix.dto.VideosDTO;
import com.rafaelehlert.aluraflix.dto.VideosMinDTO;
import com.rafaelehlert.aluraflix.services.VideosService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {
    @Autowired
    private VideosService service;
    
    @GetMapping(value = "/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<VideosDTO> findById(@PathVariable Long id) {
        VideosDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<VideosDTO>> findAll(@PageableDefault(size = 5) Pageable pageable) {
        Page<VideosDTO> dto = service.searchAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<VideosMinDTO>> findByName(@RequestParam(name = "name", defaultValue = "") String name,
                                                         @PageableDefault(size = 5) Pageable pageable){
        Page<VideosMinDTO> dto = service.serachByName(name, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/free")
    public ResponseEntity<Page<VideosDTO>> findFree(){
        int tamanhoFixo = 5;
        Pageable paginacaoFixa = PageRequest.of(0, tamanhoFixo);

        Page<VideosDTO> dto = service.searchAll(paginacaoFixa);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<VideosDTO> insert(@Valid @RequestBody VideosDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<VideosDTO> update(@PathVariable Long id, @Valid @RequestBody VideosDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
