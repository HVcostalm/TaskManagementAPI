package com.taskmanagement.entities;

import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Table(name = "tb_funcionario_junior")
public class FuncionarioJunior {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_funcionario_junior;
	private String login_funcionario;
	private String email;
	private String senha;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
	

	public Long getId_funcionario_junior() {
		return id_funcionario_junior;
	}

	public void setId_funcionario_junior(Long id_funcionario_junior) {
		this.id_funcionario_junior = id_funcionario_junior;
	}

	public String getLogin_funcionario() {
		return login_funcionario;
	}

	public void setLogin_funcionario(String login_funcionario) {
		this.login_funcionario = login_funcionario;
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
