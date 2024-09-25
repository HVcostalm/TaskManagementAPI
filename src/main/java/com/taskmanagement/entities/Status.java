package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_status")
public enum Status {
	Pendente(1), EmAndamento(2), Concluido(3);

	Status(int status) {
		// TODO Auto-generated constructor stub
	}
}
