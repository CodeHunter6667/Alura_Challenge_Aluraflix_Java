package com.rafaelehlert.aluraflix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriasDTO(Long id,
@NotBlank(message = "O campo é obrigatério")
@Size(min = 5, max = 20, message = "O titulo deve ter entre 5 e 20 caracteres")
String titulo,
@NotBlank(message = "O campo é obrigatório") 
String cor) 
 {
}