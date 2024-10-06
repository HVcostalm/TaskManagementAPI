package com.taskmanagement.entities;

public enum Prioridade {
	Baixa(0), Media(1), Alta(2);

	private final int valor;

    Prioridade(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
	
}
