package com.jonaschagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonaschagas.domain.Cidade;
import com.jonaschagas.domain.Endereco;
import com.jonaschagas.domain.Usuario;
import com.jonaschagas.dto.UsuarioDTO;
import com.jonaschagas.dto.UsuarioNewDTO;
import com.jonaschagas.dto.UsuarioUpdateDTO;
import com.jonaschagas.repository.CidadeRepository;
import com.jonaschagas.repository.EnderecoRepository;
import com.jonaschagas.repository.UsuarioRepository;
import com.jonaschagas.service.exceptions.ObjectNotFoundExcep;

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

	/**
	 * metodo de salvar usuario. Utiliza o padrao DTO para realizar as devidas
	 * validacoes na camada de negocio.
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	public Usuario fromDTO(UsuarioNewDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getCpf(), usuarioDTO.getEmail(),
				usuarioDTO.getSenha());

		Optional<Cidade> c = cidade_repository.findById(usuarioDTO.getCidadeId());

		Endereco end = new Endereco(usuarioDTO.getLogradouro(), usuarioDTO.getComplemento(), usuarioDTO.getNumero(),
				usuarioDTO.getBairro(), usuarioDTO.getCep(), c.get(), usuario);
		usuario.getEnderecos().add(end);

		if (usuarioDTO.getLogradouro2() != null && usuarioDTO.getCidadeId2() != null
				&& usuarioDTO.getNumero2() != null) {
			Optional<Cidade> c2 = cidade_repository.findById(usuarioDTO.getCidadeId());
			Endereco end2 = new Endereco(usuarioDTO.getLogradouro2(), usuarioDTO.getComplemento2(),
					usuarioDTO.getNumero2(), usuarioDTO.getBairro2(), usuarioDTO.getCep2(), c2.get(), usuario);
			usuario.getEnderecos().add(end2);
		}
		return usuario;
	}

	/**
	 * metodo de buscar usuarios. Utiliza o padrao DTO para nao expor todas
	 * informacoes do usuario.
	 * 
	 * @return
	 */
	public List<UsuarioDTO> buscar() {
		List<Usuario> list = new ArrayList<>();
		list = usuario_repository.findAll();
		List<UsuarioDTO> listDto = list.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return listDto;
	}

	/**
	 * metodo de buscar usuario. Metodo que busca um usuario pelo ID.
	 * 
	 * @param id
	 * @return
	 */
	public Usuario buscarId(Long id) {
		Optional<Usuario> optional = usuario_repository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundExcep(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
	}

	/**
	 * Metodo de alterar usuario parte 1. Metodo que pega os dados do usuario e
	 * transforma em DTO, para que nao seja possivel a modificacao do cpf e da senha
	 * do usuario.
	 * 
	 * @param usuarioDTO
	 * @return
	 */
	public Usuario updateFromDTO(UsuarioUpdateDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.getNome(), null, usuarioDTO.getEmail(), null);

		if (usuarioDTO.getCidadeId() != null && usuarioDTO.getLogradouro() != null && usuarioDTO.getNumero() != null) {
			Optional<Cidade> c = cidade_repository.findById(usuarioDTO.getCidadeId());
			Endereco end = new Endereco(usuarioDTO.getLogradouro(), usuarioDTO.getComplemento(), usuarioDTO.getNumero(),
					usuarioDTO.getBairro(), usuarioDTO.getCep(), c.get(), usuario);
			usuario.getEnderecos().add(end);
		}

		if (usuarioDTO.getLogradouro2() != null && usuarioDTO.getCidadeId2() != null
				&& usuarioDTO.getNumero2() != null) {
			Optional<Cidade> c2 = cidade_repository.findById(usuarioDTO.getCidadeId());
			Endereco end2 = new Endereco(usuarioDTO.getLogradouro2(), usuarioDTO.getComplemento2(),
					usuarioDTO.getNumero2(), usuarioDTO.getBairro2(), usuarioDTO.getCep2(), c2.get(), usuario);
			usuario.getEnderecos().add(end2);
		}
		return usuario;
	}

	/**
	 * Metodo de alterar usuario parte 2. Metodo que busca no banco o usuario com as
	 * informacoes antigas, apos chama outro metodo que realiza as modificacoes nos
	 * campos permitidos. Apos retorna o usuario modificado.
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario alterar(Usuario usuario) {
		Usuario usuario_novo = buscarId(usuario.getId());

		usuario.getEnderecos().get(0).setId(usuario_novo.getEnderecos().get(0).getId());
		updateData(usuario_novo, usuario);
		usuario = usuario_repository.save(usuario_novo);
		endereco_repository.saveAll(usuario_novo.getEnderecos());
		return usuario;
	}

	/**
	 * Metodo de alterar parte 3. Metodo privado que recebe dois usuario+s de
	 * usuario. U com informacoes do banco e outro com informacoes que devem/ou nao
	 * serem modificadas.
	 * 
	 * @param usuario_novo
	 * @param usuario
	 */
	private void updateData(Usuario usuario_novo, Usuario usuario) {

		usuario_novo.setNome(usuario.getNome());
		usuario_novo.setEmail(usuario.getEmail());
		int i = usuario_novo.getEnderecos().size();

		if (usuario_novo.getEnderecos().get(0) != null) {
			usuario_novo.getEnderecos().get(0).setLogradouro(usuario.getEnderecos().get(0).getLogradouro());
			usuario_novo.getEnderecos().get(0).setCep((usuario.getEnderecos().get(0).getCep()));
			usuario_novo.getEnderecos().get(0).setBairro((usuario.getEnderecos().get(0).getBairro()));
			usuario_novo.getEnderecos().get(0).setCidade(usuario.getEnderecos().get(0).getCidade());
			usuario_novo.getEnderecos().get(0).setComplemento(usuario.getEnderecos().get(0).getComplemento());
			usuario_novo.getEnderecos().get(0).setNumero(usuario.getEnderecos().get(0).getNumero());
		}

		switch (i) {
		case 0:
			if (usuario.getEnderecos().get(0).getLogradouro() != null
					&& usuario.getEnderecos().get(0).getCidade() != null
					&& usuario.getEnderecos().get(0).getNumero() != null) {

				Endereco end = new Endereco(usuario.getEnderecos().get(0).getLogradouro(),
						usuario.getEnderecos().get(0).getComplemento(), usuario.getEnderecos().get(0).getNumero(),
						usuario.getEnderecos().get(0).getBairro(), usuario.getEnderecos().get(0).getCep(),
						usuario.getEnderecos().get(0).getCidade(), usuario);
				usuario_novo.getEnderecos().add(end);
			}
			if (usuario.getEnderecos().get(1).getLogradouro() != null
					&& usuario.getEnderecos().get(1).getCidade() != null
					&& usuario.getEnderecos().get(1).getNumero() != null) {

				Endereco end2 = new Endereco(usuario.getEnderecos().get(1).getLogradouro(),
						usuario.getEnderecos().get(1).getComplemento(), usuario.getEnderecos().get(1).getNumero(),
						usuario.getEnderecos().get(1).getBairro(), usuario.getEnderecos().get(1).getCep(),
						usuario.getEnderecos().get(1).getCidade(), usuario);
				usuario_novo.getEnderecos().add(end2);
			}
			break;

		case 1:
			usuario_novo.getEnderecos().get(0).setLogradouro(usuario.getEnderecos().get(0).getLogradouro());
			usuario_novo.getEnderecos().get(0).setCep((usuario.getEnderecos().get(0).getCep()));
			usuario_novo.getEnderecos().get(0).setBairro((usuario.getEnderecos().get(0).getBairro()));
			usuario_novo.getEnderecos().get(0).setCidade(usuario.getEnderecos().get(0).getCidade());
			usuario_novo.getEnderecos().get(0).setComplemento(usuario.getEnderecos().get(0).getComplemento());
			usuario_novo.getEnderecos().get(0).setNumero(usuario.getEnderecos().get(0).getNumero());

			if (usuario.getEnderecos().get(1).getLogradouro() != null
					&& usuario.getEnderecos().get(1).getCidade() != null
					&& usuario.getEnderecos().get(1).getNumero() != null) {

				System.out.print("Entrou no metodo!" + i);

				Endereco end2 = new Endereco(usuario.getEnderecos().get(1).getLogradouro(),
						usuario.getEnderecos().get(1).getComplemento(), usuario.getEnderecos().get(1).getNumero(),
						usuario.getEnderecos().get(1).getBairro(), usuario.getEnderecos().get(1).getCep(),
						usuario.getEnderecos().get(1).getCidade(), usuario_novo);
				usuario_novo.getEnderecos().add(end2);
			}
			break;

		case 2:

			usuario_novo.getEnderecos().get(0).setLogradouro(usuario.getEnderecos().get(0).getLogradouro());
			usuario_novo.getEnderecos().get(0).setCep((usuario.getEnderecos().get(0).getCep()));
			usuario_novo.getEnderecos().get(0).setBairro((usuario.getEnderecos().get(0).getBairro()));
			usuario_novo.getEnderecos().get(0).setCidade(usuario.getEnderecos().get(0).getCidade());
			usuario_novo.getEnderecos().get(0).setComplemento(usuario.getEnderecos().get(0).getComplemento());
			usuario_novo.getEnderecos().get(0).setNumero(usuario.getEnderecos().get(0).getNumero());

			usuario.getEnderecos().get(1).setId(usuario_novo.getEnderecos().get(1).getId());
			usuario_novo.getEnderecos().get(1).setLogradouro(usuario.getEnderecos().get(1).getLogradouro());
			usuario_novo.getEnderecos().get(1).setCep((usuario.getEnderecos().get(1).getCep()));
			usuario_novo.getEnderecos().get(1).setBairro((usuario.getEnderecos().get(1).getBairro()));
			usuario_novo.getEnderecos().get(1).setCidade(usuario.getEnderecos().get(1).getCidade());
			usuario_novo.getEnderecos().get(1).setComplemento(usuario.getEnderecos().get(1).getComplemento());
			usuario_novo.getEnderecos().get(1).setNumero(usuario.getEnderecos().get(1).getNumero());

			break;
		}
	}

	public void deletar(Long id) {
		buscarId(id);
		usuario_repository.deleteById(id);
	}
}
