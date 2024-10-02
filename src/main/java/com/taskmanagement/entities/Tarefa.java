package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_tarefa;
	private String nome_tarefa;
	private String descricao_tarefa;
	
	@Enumerated(EnumType.ORDINAL)
	private Prioridade prioridade; //= Prioridade.Alta
	
	@Enumerated(EnumType.ORDINAL)
	private Status status; //= Status.Concluido
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
	
	public Tarefa(String nome_tarefa, String descricao_tarefa, Prioridade prioridade, Status status, Projeto projeto) {
		super();
		this.nome_tarefa = nome_tarefa;
		this.descricao_tarefa = descricao_tarefa;
		this.prioridade = prioridade;
		this.status = status;
		this.projeto = projeto;
	}

	public Long getId_tarefa() {
		return id_tarefa;
	}

	public void setId_tarefa(Long id_tarefa) {
		this.id_tarefa = id_tarefa;
	}

	public String getNome_tarefa() {
		return nome_tarefa;
	}

	public void setNome_tarefa(String nome_tarefa) {
		this.nome_tarefa = nome_tarefa;
	}

	public String getDescricao_tarefa() {
		return descricao_tarefa;
	}

	public void setDescricao_tarefa(String descricao_tarefa) {
		this.descricao_tarefa = descricao_tarefa;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	
	
}
