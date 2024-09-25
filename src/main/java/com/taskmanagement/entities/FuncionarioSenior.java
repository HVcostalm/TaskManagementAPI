package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionarioSenior")
public class FuncionarioSenior extends FuncionarioJunior{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionarioSenior;
	
	public void atualizarProjeto(int idFuncionarioJunior){

	}
	
	public void criarTarefa() {
		
	}
	
	public void deletarTarefa(int idTarefa) {
		
	}
	
	public void finalizarProjeto() {
		
	}
	
	public Long getIdFuncionarioSenior() {
		return idFuncionarioSenior;
	}

	public void setIdFuncionarioSenior(Long idFuncionarioSenior) {
		this.idFuncionarioSenior = idFuncionarioSenior;
	}
	
	
	
}
