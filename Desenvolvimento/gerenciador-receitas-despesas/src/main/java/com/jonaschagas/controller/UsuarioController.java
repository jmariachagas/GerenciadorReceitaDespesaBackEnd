package com.jonaschagas.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonaschagas.domain.Usuario;
import com.jonaschagas.dto.UsuarioNewDTO;
import com.jonaschagas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuario_service;
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody UsuarioNewDTO clienteNewDto) {
		Usuario obj = usuario_service.fromDTO(clienteNewDto);
		obj = usuario_service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
				path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
