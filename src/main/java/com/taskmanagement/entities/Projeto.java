package com.taskmanagement.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_projeto")
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjeto;
	private String nomeProjeto;
	private String descricaoProjeto;
	private Date dataPrevistaEntrega;
	private Date dataConclusao;
	private boolean status;
}
