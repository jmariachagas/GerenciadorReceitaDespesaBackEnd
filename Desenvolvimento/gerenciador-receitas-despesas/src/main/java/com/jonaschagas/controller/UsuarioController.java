package com.jonaschagas.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonaschagas.domain.Usuario;
import com.jonaschagas.dto.UsuarioDTO;
import com.jonaschagas.dto.UsuarioNewDTO;
import com.jonaschagas.dto.UsuarioUpdateDTO;
import com.jonaschagas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuario_service;

	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody UsuarioNewDTO usuarioNewDto) {
		Usuario obj = usuario_service.fromDTO(usuarioNewDto);
		obj = usuario_service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
				path("{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public List<UsuarioDTO> buscar(){
		return usuario_service.buscar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarId(@PathVariable Long id) {
		Usuario usuario = usuario_service.buscarId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> alterar(@Valid @RequestBody UsuarioUpdateDTO usuarioUpdateDto, @PathVariable Long id) {
		Usuario usuario = usuario_service.updateFromDTO(usuarioUpdateDto);
		usuario.setId(id);
		usuario = usuario_service.alterar(usuario);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuario_service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
