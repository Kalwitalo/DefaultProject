package com.kalwitalo.util;

import java.util.ResourceBundle;

import javax.inject.Inject;


/***
 * @author Thiago Sarmento
 * 
 * */

public class MessagesUtil {

	@Inject
	@Messages
	private ResourceBundle msg;

	public String mensagem(String chave){
		return msg.getString(chave);
	}

	public ResourceBundle getMsg() {
		return msg;
	}
}
