package com.kalwitalo.rest;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.kalwitalo.service.ProfissaoService;
import com.kalwitalo.util.Util;

public class RestServiceImpl implements RestService{
	
	@Inject
	private ProfissaoService profissaoService;
	
	@Override
	public Response buscarProfissaoPorNome(String nome) throws Exception {
		try {
			return Util.enviarResposta(profissaoService.buscarTodos());
        } catch (Exception e) {
        	e.printStackTrace();
        	return Util.processarExcecao(e);
        }
	}

}
