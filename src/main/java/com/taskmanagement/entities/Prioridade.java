package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_prioridade")
public enum Prioridade {
	Baixa(1), MediaA(2), Alta(3);

	Prioridade(int prioridade) {
		// TODO Auto-generated constructor stub
	}
	
	
}
