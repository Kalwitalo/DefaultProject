package com.kalwitalo.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="anos_experiencia")
	private int anosExperiencia;

	private String nome;

	//bi-directional many-to-one association to Profissao
	@ManyToOne
	private Profissao profissao;

	public Pessoa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnosExperiencia() {
		return this.anosExperiencia;
	}

	public void setAnosExperiencia(int anosExperiencia) {
		this.anosExperiencia = anosExperiencia;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Profissao getProfissao() {
		return this.profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

}