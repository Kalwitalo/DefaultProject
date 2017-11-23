package com.kalwitalo.util;


/**
 * @author Thiago Sarmento
 * 
 * */

public class Parametros<T> {

	private String nome;
	private T valor;

	public Parametros() {

	}

	public Parametros(String nome, T valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}
}
