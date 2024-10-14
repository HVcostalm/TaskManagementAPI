package com.taskmanagement.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_projeto;
	private String nome_projeto;
	private String descricao_projeto;
	private LocalDate  data_prevista_entrega;
	private LocalDate  data_conclusao = null;
	private boolean status=true;
	
	public Projeto(String nome_projeto, String descricao_projeto, LocalDate  data_prevista_entrega) {
		this.nome_projeto = nome_projeto;
		this.descricao_projeto = descricao_projeto;
		this.data_prevista_entrega = data_prevista_entrega;
	}
	
	public Projeto() {
		
	}

	public Long getId_projeto() {
		return id_projeto;
	}

	public void setId_projeto(Long id_projeto) {
		this.id_projeto = id_projeto;
	}

	public String getNome_projeto() {
		return nome_projeto;
	}

	public void setNome_projeto(String nome_projeto) {
		this.nome_projeto = nome_projeto;
	}

	public String getDescricao_projeto() {
		return descricao_projeto;
	}

	public void setDescricao_projeto(String descricao_projeto) {
		this.descricao_projeto = descricao_projeto;
	}

	public LocalDate getData_prevista_entrega() {
		return data_prevista_entrega;
	}

	public void setData_prevista_entrega(LocalDate data_prevista_entrega) {
		this.data_prevista_entrega = data_prevista_entrega;
	}

	public LocalDate getData_conclusao() {
		return data_conclusao;
	}

	public void setData_conclusao(LocalDate data_conclusao) {
		this.data_conclusao = data_conclusao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
