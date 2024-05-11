package com.rafaelehlert.aluraflix.services;

import com.rafaelehlert.aluraflix.models.Roles;
import com.rafaelehlert.aluraflix.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafaelehlert.aluraflix.dto.UsuarioDTO;
import com.rafaelehlert.aluraflix.models.Usuario;
import com.rafaelehlert.aluraflix.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private RolesRepository rolesRepository;
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Roles role = rolesRepository.searchByNomeRole(dto.getRole());
		Usuario usuario = new Usuario();
		copyDtoToEntity(dto, usuario);
		usuario.setRoles(role);
		var senhaCriptocrafada = new BCryptPasswordEncoder().encode(usuario.getPassword());
		usuario.setPassword(senhaCriptocrafada);
		repository.save(usuario);
		return new UsuarioDTO(usuario);
	}
	
	private static void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());
	}
}
