package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarefa;
	private String nomeTarefa;
	private String descricaoTarefa;
	
	private Prioridade prioridade; //= Prioridade.Alta
	private Status status; //= Status.Concluido
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
}
