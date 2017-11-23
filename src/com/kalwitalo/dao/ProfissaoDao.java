package com.kalwitalo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.kalwitalo.dao.util.GenericDAO;
import com.kalwitalo.entity.Profissao;
import com.kalwitalo.util.Transformador;

public class ProfissaoDao extends GenericDAO<Profissao>{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Profissao> findByName(String nome) throws Exception{
		List<Profissao> list = new ArrayList<Profissao>();
		
		try {
			Query query = getEntityManager().createNativeQuery("SELECT p.* FROM profissao p WHERE p.nome = :nome");
			query.setParameter("nome", nome);
			
			list = ((List<Profissao>) new Transformador()
										.setListaResultado(query.getResultList())
										.setClasseDTO(Profissao.class)
										.setCampos("id")
										.setCampos("nome")
										.executar());
			
			if (list == null || list.isEmpty()) throw new Exception("Registro não encontrado");
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}
}
