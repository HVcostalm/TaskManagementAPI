package com.taskmanagement.entities;

public enum Status {
	Pendente(0), EmAndamento(1), Concluido(2);

	private final int valor;

    Status(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
