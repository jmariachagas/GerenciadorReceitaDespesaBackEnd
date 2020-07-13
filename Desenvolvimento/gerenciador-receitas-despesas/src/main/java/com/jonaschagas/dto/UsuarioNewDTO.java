package com.jonaschagas.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jonaschagas.service.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDTO {
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O nome da cliente deve conter no minimo 5 caracteres e no máximo 120.")
	private String nome;	
	private String cpf;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "Email inválido!")
	private String email;
	
	@NotEmpty
	private String senha;
	
	private String logradouro;
	private String complemento;
	
	private String numero;
	private String bairro;
	private String cep;	
	private Long cidadeId;
	
	private String logradouro2;
	private String complemento2;
	private String numero2;
	private String bairro2;
	private String cep2;	
	private Long cidadeId2;
	
	public UsuarioNewDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

	public String getLogradouro2() {
		return logradouro2;
	}

	public void setLogradouro2(String logradouro2) {
		this.logradouro2 = logradouro2;
	}

	public String getComplemento2() {
		return complemento2;
	}

	public void setComplemento2(String complemento2) {
		this.complemento2 = complemento2;
	}

	public String getNumero2() {
		return numero2;
	}

	public void setNumero2(String numero2) {
		this.numero2 = numero2;
	}

	public String getBairro2() {
		return bairro2;
	}

	public void setBairro2(String bairro2) {
		this.bairro2 = bairro2;
	}

	public String getCep2() {
		return cep2;
	}

	public void setCep2(String cep2) {
		this.cep2 = cep2;
	}

	public Long getCidadeId2() {
		return cidadeId2;
	}

	public void setCidadeId2(Long cidadeId2) {
		this.cidadeId2 = cidadeId2;
	}	
}
