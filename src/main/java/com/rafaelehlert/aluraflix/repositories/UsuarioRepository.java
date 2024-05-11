package com.rafaelehlert.aluraflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.rafaelehlert.aluraflix.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	UserDetails findByUsername(String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Usuario procuraUserPorUsername(String username);
}
