package com.kalwitalo.dao.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.kalwitalo.util.Parametros;

public abstract class GenericDAO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName ="DefaultProject")
	private EntityManager em;
	
	public GenericDAO(){
	}
	
	public void inserir(T objeto) throws Exception{
		em.persist(objeto);
	}
	
	public T inserirComRetorno(T objeto) throws Exception{	
		em.persist(objeto);
		
		return objeto;
	}
	
	
	public void alterar(T objeto) throws Exception{
		em.merge(objeto);
	}
	
	public void deletar(List<T> objetos) throws Exception{	
		for(Object objeto : objetos){
			em.remove(em.merge(objeto));
		}
	}
	
	public void deletarPorId(T object) throws Exception{
		em.remove(em.merge(object));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscarObjetoPorNamedQueryList(String namedQuery, Parametros<?>... parametros) {
		Query query = em.createNamedQuery(namedQuery);
		if (parametros != null) {
			for (Parametros<?> item : parametros) {
				if (item != null)
					query.setParameter(item.getNome(), item.getValor());
			}
		}
		try {
			return  query.getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T buscarObjetoPorNamedQuerySingle(String namedQuery, Parametros<?>... parametros) {
		Query query = em.createNamedQuery(namedQuery);
		if (parametros != null) {
			for (Parametros<?> item : parametros) {
				if (item != null)
					query.setParameter(item.getNome(), item.getValor());
			}
		}
		try {
			return (T)query.getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> buscarObjetoPorNativeNamedQuery(String nativeNamedQuery, Parametros<?>... parametros) {
		Query query = em.createNamedQuery(nativeNamedQuery);
	
		if (parametros != null) {
			for (Parametros<?> item : parametros) {
				if (item != null)
					query.setParameter(item.getNome(), item.getValor());
			}
		}
		try {
			return  query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public T buscarUm(Object id) throws Exception{
		return  em.find(getClasseEntidade(), id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() throws Exception{
		return  em.createQuery("SELECT o FROM "+getClasseEntidade().getSimpleName()+" o").getResultList();
	}
	

	@SuppressWarnings("unchecked")
	public Class<T> getClasseEntidade(){
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return em;
	}
}
