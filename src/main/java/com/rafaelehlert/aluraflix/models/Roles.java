package com.rafaelehlert.aluraflix.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_roles")
public class Roles implements GrantedAuthority{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeRole;
	
	public Roles(Roles entity) {
		id = entity.getId();
		nomeRole = entity.getNomeRole();
	}

	public Roles() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNomeRole() {
		return nomeRole;
	}

	public void setNomeRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String getAuthority() {
		return nomeRole;
	}
}
