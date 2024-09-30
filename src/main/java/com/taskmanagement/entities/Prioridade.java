package com.taskmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public enum Prioridade {
	Baixa(0), MediaA(1), Alta(2);

	private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
	
}
