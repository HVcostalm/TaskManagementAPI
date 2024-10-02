package com.taskmanagement.entities;

import java.util.Date;

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
	private Date data_prevista_entrega;
	private Date data_conclusao;
	private boolean status;
	
	public Projeto(String nome_projeto, String descricao_projeto, Date data_prevista_entrega, boolean status) {
		this.nome_projeto = nome_projeto;
		this.descricao_projeto = descricao_projeto;
		this.data_prevista_entrega = data_prevista_entrega;
		this.status = status;
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
	public Date getData_prevista_entrega() {
		return data_prevista_entrega;
	}
	public void setData_prevista_entrega(Date data_prevista_entrega) {
		this.data_prevista_entrega = data_prevista_entrega;
	}
	public Date getData_conclusao() {
		return data_conclusao;
	}
	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
