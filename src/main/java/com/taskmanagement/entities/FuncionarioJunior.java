package com.taskmanagement.entities;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionarioJunior")
public class FuncionarioJunior {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFuncionarioJunior;
	private String loginFuncionario;
	private String email;
	private String senha;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
	
	public void atualizarFuncionario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
	
	public void atualizarTarefa(){
		
	}
	
	public Long getIdFuncionarioJunior() {
		return idFuncionarioJunior;
	}

	public void setIdFuncionarioJunior(Long idFuncionarioJunior) {
		this.idFuncionarioJunior = idFuncionarioJunior;
	}

	public String getLoginFuncionario() {
		return loginFuncionario;
	}

	public void setLoginFuncionario(String loginFuncionario) {
		this.loginFuncionario = loginFuncionario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Projeto getProjeto() {
		return projeto;
	}	
	
}
