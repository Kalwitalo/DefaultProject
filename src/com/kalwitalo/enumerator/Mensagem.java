package com.kalwitalo.enumerator;

public enum Mensagem{
	
		SUCESSO(0,"Sucesso"),
		PRIMEIROACESSO(88,"Primeiro Acesso"),
		ERRO(1,"Erro"),
		ERRO_INTEGRIDADE(2,"Integridade"),
		ERRO_EXCLUSAO_PAI_FILHO_ATIVO(3,"Constraint exclusao logica");

	  private Integer id;
	  private String msg;
	
	  private Mensagem(Integer id, String tipo) {
	    this.id = id;
	    this.msg = tipo;
	  }
	
	  public String getMsg() {
	    return msg;
	  }
	
	  public Integer getId() {
	    return id;
	  }
	
	  public static boolean contains(String msg) {
	    for (Mensagem ad : Mensagem.values()) {
	      if (msg.equals(ad.getMsg()))
	        return true;
	    }
	    return false;
	  }
	
	  public String toString() {
	    return this.msg;
	  }
}
	
	
