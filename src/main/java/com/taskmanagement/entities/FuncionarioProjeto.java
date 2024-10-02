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
@Table(name = "tb_funcionario_projeto")
public class FuncionarioProjeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Date data_participacao_inicial;
	private Date data_participacao_final;
	
	@OneToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;
	
	@OneToOne
	@JoinColumn(name="funcionario_id")
	private Funcionario funcionario;
	
	public FuncionarioProjeto(Date data_participacao_inicial, Projeto projeto, Funcionario funcionario) {
		this.data_participacao_inicial = data_participacao_inicial;
		this.projeto = projeto;
		this.funcionario = funcionario;
	}

	public Date getData_participacao_inicial() {
		return data_participacao_inicial;
	}

	public void setData_participacao_inicial(Date data_participacao_inicial) {
		this.data_participacao_inicial = data_participacao_inicial;
	}

	public Date getData_participacao_final() {
		return data_participacao_final;
	}

	public void setData_participacao_final(Date data_participacao_final) {
		this.data_participacao_final = data_participacao_final;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
}
