package com.jonaschagas.dto;

import java.util.List;

import com.jonaschagas.domain.Endereco;
import com.jonaschagas.domain.Usuario;

public class UsuarioUpdateDTO {

	private long id;

	private String nome;

	private String email;

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

	public UsuarioUpdateDTO(Usuario usuario) {
		id = usuario.getId();
		nome = usuario.getNome();
		email = usuario.getEmail();
		buildEnderecos(usuario.getEnderecos());		
	}
	
	public UsuarioUpdateDTO() {
		super();
	}
	
	private void buildEnderecos(List<Endereco> enderecos) {
		
		if(enderecos != null) {
			logradouro = enderecos.get(0).getLogradouro();
			complemento = enderecos.get(0).getComplemento();
			numero = enderecos.get(0).getNumero();
			bairro = enderecos.get(0).getBairro();
			cep = enderecos.get(0).getCep();
			cidadeId = enderecos.get(0).getCidade().getId();
			
			logradouro2 = enderecos.get(1).getLogradouro();
			complemento2 = enderecos.get(1).getComplemento();
			numero2 = enderecos.get(1).getNumero();
			bairro2 = enderecos.get(1).getBairro();
			cep2 = enderecos.get(1).getCep();
			cidadeId2 = enderecos.get(1).getCidade().getId();
		}		
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
