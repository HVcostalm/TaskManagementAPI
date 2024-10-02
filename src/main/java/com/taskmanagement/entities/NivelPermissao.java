package com.taskmanagement.entities;

public enum NivelPermissao {
	Junior(0), Senior(1), Administrador(2);
	
	private final int valor;

	NivelPermissao(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
