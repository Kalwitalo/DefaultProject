package com.kalwitalo.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("service")
public interface RestService {
	
	@GET
	@Path("/buscarProfissaoPorNome/{nome}")
	@Consumes(MediaType.APPLICATION_JSON)  
	@Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Response buscarProfissaoPorNome(@PathParam(value = "nome") String nome) throws Exception;
}
