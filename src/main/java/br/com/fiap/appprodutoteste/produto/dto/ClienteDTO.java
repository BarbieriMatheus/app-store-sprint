package br.com.fiap.appprodutoteste.produto.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteDTO {

	@NotNull
	@NotBlank
	private String nome;
	@NotNull
	@NotBlank
	private String documento;
	@NotNull
	@NotBlank
	private String endereco;

	public ClienteDTO(String nome, String documento, String endereco) {
		this.nome = nome;
		this.documento = documento;
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
