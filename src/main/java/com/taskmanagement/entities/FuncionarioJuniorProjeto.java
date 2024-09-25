package com.taskmanagement.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionarioJuniorProjeto")
public class FuncionarioJuniorProjeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Date dataParticipacaoInicial;
	private Date dataParticipacaoFinal;
	
	@OneToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
	
	@OneToOne
	@JoinColumn(name="funcionarioJunior_id")
	private FuncionarioJunior funcionarioJunior;
}
