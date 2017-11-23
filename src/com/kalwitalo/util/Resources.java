package com.kalwitalo.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.inject.Produces;

public class Resources {

	@Produces
	@Messages
	public ResourceBundle getResourceBundle() {
		Locale pt_BR = new Locale("pt", "BR");
		return ResourceBundle.getBundle("META-INF/messages", pt_BR);
	}
}
