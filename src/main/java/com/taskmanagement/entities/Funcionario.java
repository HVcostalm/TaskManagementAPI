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
@Table(name = "tb_funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_funcionario;
	private String login_funcionario;
	private String email;
	private String senha;
	private boolean status = true;
	
	@Enumerated(EnumType.ORDINAL)
	private NivelPermissao nivel_permissao;
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto = null;
	
	public Funcionario(Long id_funcionario, String login_funcionario, String email, String senha, NivelPermissao nivel_permissao) {
		this.id_funcionario = id_funcionario;
		this.login_funcionario = login_funcionario;
		this.email = email;
		this.senha = senha;
		this.nivel_permissao = nivel_permissao;
	}
	
	

	public Funcionario() {
	}



	public Long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(Long id_funcionario) {
		this.id_funcionario = id_funcionario;
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
	
	public NivelPermissao getNivel_permissao() {
		return nivel_permissao;
	}

	public void setNivel_permissao(NivelPermissao nivel_permissao) {
		this.nivel_permissao = nivel_permissao;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}	
	
}
