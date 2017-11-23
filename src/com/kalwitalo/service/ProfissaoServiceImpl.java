package com.kalwitalo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.kalwitalo.dao.ProfissaoDao;
import com.kalwitalo.entity.Profissao;

@Stateless
public class ProfissaoServiceImpl implements ProfissaoService {
	
	@Inject
	private ProfissaoDao profissaoDao;
	
	public ProfissaoServiceImpl() {}
	
	public List<Profissao> buscarProfissaoPorNome(String nome) throws Exception{
		return profissaoDao.findByName(nome);
	}

	@Override
	public List<Profissao> buscarTodos() throws Exception {
		return profissaoDao.buscarTodos();
	}
}
