package com.jonaschagas.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jonaschagas.domain.Cidade;
import com.jonaschagas.domain.Usuario;

public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O nome da cliente deve conter no minimo 5 caracteres e no máximo 120.")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido!")
	private String email;
	
	private String cidade; 

	public UsuarioDTO(Usuario usuario) {
		super();
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		cidade = usuario.getEnderecos().get(0).getCidade().getNome();
	}	

	public UsuarioDTO() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
