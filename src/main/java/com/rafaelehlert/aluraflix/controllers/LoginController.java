package com.rafaelehlert.aluraflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelehlert.aluraflix.dto.LoginDTO;
import com.rafaelehlert.aluraflix.dto.TokenDTO;
import com.rafaelehlert.aluraflix.models.Usuario;
import com.rafaelehlert.aluraflix.security.TokenService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		var authentication = manager.authenticate(authenticationToken);
		
		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
		
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
	}
}
