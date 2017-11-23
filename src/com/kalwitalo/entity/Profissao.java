package com.kalwitalo.entity;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;


/**
 * The persistent class for the profissao database table.
 * 
 */
@Entity
@Named(value="profissao")
@NamedNativeQuery(name="Profissao.findByName", query="SELECT p.* FROM profissao p WHERE p.nome = :nome")
public class Profissao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nome;

	public Profissao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}