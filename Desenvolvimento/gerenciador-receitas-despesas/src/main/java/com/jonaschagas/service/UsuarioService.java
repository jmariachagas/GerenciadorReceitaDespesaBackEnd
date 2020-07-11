package com.jonaschagas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonaschagas.domain.Cidade;
import com.jonaschagas.domain.Endereco;
import com.jonaschagas.domain.Usuario;
import com.jonaschagas.dto.UsuarioNewDTO;
import com.jonaschagas.repository.CidadeRepository;
import com.jonaschagas.repository.EnderecoRepository;
import com.jonaschagas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	EnderecoRepository endereco_repository;
	
	@Autowired
	CidadeRepository cidade_repository;
	
	@Autowired
	UsuarioRepository usuario_repository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuario = usuario_repository.save(usuario);
		endereco_repository.saveAll(usuario.getEnderecos());
		return usuario;
	}
	
	public Usuario fromDTO(UsuarioNewDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getCpf(),  usuarioDTO.getEmail(),
				usuarioDTO.getSenha());
		
		Optional<Cidade> c = cidade_repository.findById(usuarioDTO.getCidadeId());
		
		Endereco end = new Endereco(usuarioDTO.getLogradouro(), usuarioDTO.getComplemento(), usuarioDTO.getNumero(),
				usuarioDTO.getBairro(), usuarioDTO.getCep(), c.get(), usuario);
		
		usuario.getEnderecos().add(end);
		
		return usuario;
	}
	
	

}
