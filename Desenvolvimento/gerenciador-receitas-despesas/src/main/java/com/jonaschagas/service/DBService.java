package com.jonaschagas.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonaschagas.domain.Cidade;
import com.jonaschagas.domain.Endereco;
import com.jonaschagas.domain.Estado;
import com.jonaschagas.domain.Usuario;
import com.jonaschagas.repository.CidadeRepository;
import com.jonaschagas.repository.EnderecoRepository;
import com.jonaschagas.repository.EstadoRepository;
import com.jonaschagas.repository.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	EstadoRepository estado_repository;
	
	@Autowired
	CidadeRepository cidade_repository;
	
	@Autowired
	EnderecoRepository endereco_repository;

	@Autowired
	UsuarioRepository usuario_repository;
	
	public void instantiateTestDatabase() {

		Estado est1 = new Estado("Minas Gerais");
		Estado est2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Uberlândia", est1);
		Cidade c2 = new Cidade("São Paulo", est2);
		Cidade c3 = new Cidade("Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));
		est2.getCidades().addAll(Arrays.asList(c3));

		estado_repository.saveAll(Arrays.asList(est1, est2));
		cidade_repository.saveAll(Arrays.asList(c1, c2, c3));
		
		Usuario usuario1 = new Usuario("Jonas","123456789", "jonaschagas1@hotmail.com", "manu");
		Usuario usuario2 = new Usuario("Andrea","98756412302", "manu@hotmail.com", "jonas");
		
		Endereco end1 = new Endereco("Barão do Cerro Largo", "Apto 123", "52", "Centro", "97542-000", c1, usuario1);
		Endereco end2 = new Endereco("Gaspar Martins", "Sala A", "368", "Centro", "97542-000", c1, usuario1);
		Endereco end3 = new Endereco("dos Andradas", "Prédio 2", "123", "Centro", "97542-000", c2, usuario2);
		
		usuario1.getEnderecos().addAll(Arrays.asList(end1, end2));
		usuario2.getEnderecos().add(end3);
		
		usuario_repository.saveAll(Arrays.asList(usuario1, usuario2));
		endereco_repository.saveAll(Arrays.asList(end1, end2, end3));		

	}
	
}
