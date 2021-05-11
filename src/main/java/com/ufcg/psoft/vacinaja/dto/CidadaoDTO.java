package com.ufcg.psoft.vacinaja.dto;

import java.util.Date;
import java.util.List;

public class CidadaoDTO {

	private String nome;
	private String endereco;
	private String cpf;
	private String numeroCartaoSus;
	private Date dataNascimento;
	private String telefone;
	private String profissao;
	private List<Long> comorbidades;

	public CidadaoDTO(String nome, String endereco, String cpf, String numeroCartaoSus,
			Date dataNascimento, String telefone, String profissao, List<Long> comorbidades) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.numeroCartaoSus = numeroCartaoSus;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.telefone = telefone;
		this.profissao = profissao;
		this.comorbidades = comorbidades;
	}
	
	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNumeroCartaoSus() {
		return numeroCartaoSus;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public List<Long> getComorbidades() {
		return comorbidades;
	}
}
