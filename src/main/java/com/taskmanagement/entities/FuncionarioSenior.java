package com.taskmanagement.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("FuncionarioSenior")
public class FuncionarioSenior extends FuncionarioJunior{
	
	public void atualizarProjeto(int idFuncionarioJunior){

	}
	
	public void criarTarefa() {
		
	}
	
	public void deletarTarefa(int idTarefa) {
		
	}
	
	public void finalizarProjeto() {
		
	}
	
}
