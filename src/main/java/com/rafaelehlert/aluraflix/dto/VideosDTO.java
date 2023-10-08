package com.rafaelehlert.aluraflix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VideosDTO(Long id,
        //Validacao de tamanho do titulo, deve ter no minimo 5 caracteres e no maximo 50
        @Size(min = 5, max = 50, message = "Titulo precisa ter entre 5 e 50 caracteres")
        //Validacao de campo, verifica se foi ou nao deixado em branco    
        @NotBlank(message = "Campo obrigatorio") String titulo,
        //Validacao de tamanho da descricao, deve ter no minimo 20 caracteres e no maximo 200
        @Size(min = 20, max = 200, message = "descricao deve ter entre 200 e 200 caracteres")
        //Validacao de campo, verifica se foi ou nao deixado em branco     
        @NotBlank(message = "Campo obrigatorio") String descricao,
        //Validacao de campo, verifica se foi ou nao deixado em branco
        @NotBlank(message = "Campo obrigatorio") String url) {
}
