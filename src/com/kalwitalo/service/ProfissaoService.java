package com.kalwitalo.service;

import java.util.List;

import javax.ejb.Local;

import com.kalwitalo.entity.Profissao;

@Local
public interface ProfissaoService {
	public List<Profissao> buscarProfissaoPorNome(String nome) throws Exception;
	public List<Profissao> buscarTodos() throws Exception;
}
