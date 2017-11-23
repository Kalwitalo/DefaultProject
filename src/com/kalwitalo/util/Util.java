package com.kalwitalo.util;


import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.kalwitalo.enumerator.Mensagem;


/**
 * @author Thiago.Sarmento
 * 
 */
public class Util {
	
	@Inject
	private static MessagesUtil messagesUtil;

	public static String getIpServidor() throws UnknownHostException {

		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public static Timestamp getHoraExata() {
		return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public static String extraiModulo(Class<?> clazz) {
		return clazz.getPackage().getName().split(Pattern.quote("."))[5];
	}
	
	/**
	 * @author Thiago.Sarmento Metodo responsavel por receber objeto generico e
	 *         enviar resposta Json ao cliente.
	 * @throws Exception
	 * 
	 */
	public static <T> Response enviarResposta(T obj) throws Exception {

		if (obj == null)
			throw new Exception(messagesUtil.mensagem("message.seguranca.objetoNaoEncontrado"));
		return Response.ok().entity(new Gson().toJson(new RespostaGeneric<T>(Mensagem.SUCESSO.getId(), Mensagem.SUCESSO.getMsg(), obj))).build();
	}

	/**
	 * @author Thiago.Sarmento Metodo responsavel por receber uma lista
	 *         generica e enviar resposta Json ao cliente.
	 * @throws Exception
	 * 
	 */
	public static <T> Response enviarRespostaLista(List<T> lista) throws Exception {

		if (lista == null || lista.size() == 0)
			throw new Exception(messagesUtil.mensagem("message.seguranca.objetoNaoEncontrado"));
		return Response.ok().entity(new Gson().toJson(new RespostaGeneric<List<T>>(Mensagem.SUCESSO.getId(), Mensagem.SUCESSO.getMsg(), lista))).build();
	}
	
	/**
	 * @throws Exception
	 * 
	 */
	
	public static Response processarExcecao(Exception e ) throws UnsupportedEncodingException{
		return Response.ok().entity(new RespostaGeneric<String>(1, Mensagem.ERRO.getMsg(), e.getMessage())).build();
		
	}	
	
	public static String retirarCarateresProcessoDocumento(String valor){
		 valor = valor.toLowerCase().replaceAll("[.-]", "");
		 return valor;
	}
	

}
	

