package com.rafaelehlert.aluraflix.controllers;

import java.net.URI;

import com.rafaelehlert.aluraflix.dto.VideosMinDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelehlert.aluraflix.dto.VideosDTO;
import com.rafaelehlert.aluraflix.services.VideosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/videos")
public class VideosController {
    @Autowired
    private VideosService service;
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<VideosDTO> findById(@PathVariable Long id) {
        VideosDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<VideosDTO>> findAll(Pageable pageable) {
        Page<VideosDTO> dto = service.searchAll(pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<VideosMinDTO>> findByName(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable){
        Page<VideosMinDTO> dto = service.serachByName(name, pageable);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<VideosDTO> insert(@Valid @RequestBody VideosDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VideosDTO> update(@PathVariable Long id, @Valid @RequestBody VideosDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
