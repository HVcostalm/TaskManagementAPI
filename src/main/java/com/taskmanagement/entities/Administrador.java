package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_administrador")
public class Administrador extends FuncionarioSenior{
	private Long idAdministrador;
	
	public void cadastrarFuncionario() {
		
	}
	
	public void criarProjeto(int idFuncionarioSenior) {
		
	}
	
	public void deletarProjeto(int idProjeto) {
		
	}
	
	public void listarProjetos() {
		
	}
	
	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}
	
}
