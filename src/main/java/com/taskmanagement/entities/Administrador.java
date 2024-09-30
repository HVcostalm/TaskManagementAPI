package com.taskmanagement.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends FuncionarioSenior{
	
	public void cadastrarFuncionario() {
		
	}
	
	public void criarProjeto(int idFuncionarioSenior) {
		
	}
	
	public void deletarProjeto(int idProjeto) {
		
	}
	
	public void listarProjetos() {
		
	}
	
}
