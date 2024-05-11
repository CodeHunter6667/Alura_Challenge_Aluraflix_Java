package com.rafaelehlert.aluraflix.dto;

import com.rafaelehlert.aluraflix.models.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO{
	
	private Long id;
	
	@NotBlank
	@Size(min = 5, max = 20, message = "Username deve ter entre 5 e 20 caracteres")
	private String username;
	
	@NotBlank
	@Size(min = 8, max = 15, message = "Password deve ter entre 8 e 15 caracteres")
	private String password;
	private String role;
	
	public UsuarioDTO(Long id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		username = entity.getUsername();
		password = entity.getPassword();
		role = entity.getRoles().getNomeRole();
	}
	
	public Long getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
}
