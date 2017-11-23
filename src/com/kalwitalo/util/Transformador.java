package com.kalwitalo.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Thiago Sarmento 24/07/2017
 * 
 * Classe transforma resultado NativeQuery para DTO
 *   campos texto do banco viram String 
 *   campos BigDecimal do banco viram Long
 * 
 * */

public class Transformador {
	
	private List<String> campos;
	
	private Class<?> clazz;
	
	private List<Object[]> lista;
	
	public Transformador() {
		this.campos = new ArrayList<String>();
	}
	
	public Transformador setCampos(String valor){
		campos.add(valor);
		return this;
	}
	
	public Transformador setClasseDTO(Class<?> clazz){
		this.clazz = clazz;
		return this;
	}
	
	public Transformador setListaResultado(List<Object[]> lista){
		this.lista = lista;
		return this;
	}
	
	public Object executar() throws InstantiationException, IllegalAccessException, SQLException{
		
        Field [] declaredFields =  null;
        Object ob = null;
        boolean flag = false;
        List<Object> listaOb = new ArrayList<Object>(); 
        try {
           
            if(campos != null && !campos.isEmpty())
            	for (int i = 0; i < lista.size(); i++) {
            		ob =  clazz.newInstance();
            		declaredFields = ob.getClass().getDeclaredFields();
                    
                    Object[] row = lista.get(i);
            		List<String> camposTemp = retiraCamposNUll(row,new ArrayList<String>(campos));

            		for (int j = 0; j < row.length; j++)
            		for_externo:
            		  for(String key: camposTemp){
            			  for (int x = 0; x < declaredFields.length; x++) {
            				  if(!declaredFields[x].getName().equalsIgnoreCase("serialVersionUID"))
		            			if(key.equalsIgnoreCase(declaredFields[x].getName())){
		            			if(row[j] != null){
		            				declaredFields[x].setAccessible(true);
		            				
		            				
		            				
		            				
		            				if(row[j] instanceof BigDecimal){
		            					if(declaredFields[x].getType().isAssignableFrom(Long.class)){
		            						BigDecimal big = (BigDecimal) row[j];
				            				declaredFields[x].set(ob, big.longValue());
				            				camposTemp.remove(key);  
				            				break for_externo;
			            				}
			            			}
		            				
		            				if(row[j] instanceof Date){
		            					if(declaredFields[x].getType().isAssignableFrom(Date.class)){
		            						Date big = (Date) row[j];
				            				declaredFields[x].set(ob, big);
				            				camposTemp.remove(key);  
				            				break for_externo;
			            				}
			            			}
		            				
		            				if(row[j] instanceof BigDecimal){/**SE DO BANCO VOLTAR BigDecimal E O DTO ESPERAR INTEGER*/
		            					if(declaredFields[x].getType().isAssignableFrom(Integer.class)){
		            						Integer big =  Integer.parseInt(row[j].toString());
				            				declaredFields[x].set(ob, big);
				            				camposTemp.remove(key);  
				            				break for_externo;
			            				}
			            			}
		            				 
		            				if(row[j] instanceof Blob){
		            					if(declaredFields[x].getType().isAssignableFrom(byte[].class)){
		            						Blob blob = (Blob)row[j];
		            						
		            						int blobLength = (int) blob.length();  
		            						byte[] blobAsBytes = blob.getBytes(1, blobLength);
		            						blob.free();
		            						
				            				declaredFields[x].set(ob, blobAsBytes);
				            				camposTemp.remove(key);  
				            				break for_externo;
			            				}
			            			}
		            				
		            				
		            				if(row[j] != null){
			            			declaredFields[x].set(ob, row[j].toString());  
			            			camposTemp.remove(key);
			            			break for_externo;
		            				}
		            			}
		            		}
						}
            		  }
            		flag = true;
            		listaOb.add(ob);
            	}
            	
            	if(campos == null || !flag) 
            		for (int i = 0; i < lista.size(); i++) {
	            		Object[] row = lista.get(i);
	            		
	            		for (int j = 0; j < row.length; j++){
	        				declaredFields[j].setAccessible(true);
	        			
	        				if(row[j] instanceof BigDecimal){
	            				BigDecimal big = (BigDecimal) row[j];
	            				declaredFields[j].set(ob, big.longValue());	
	            				continue;
	        				}
	            			
	        				declaredFields[j].set(ob, row[j]);
	        				continue;
						}
	            		listaOb.add(ob);
            		}

        } catch (SecurityException
                | IllegalArgumentException 
                | IllegalAccessException e) {
            e.printStackTrace();
            throw e;
        }
        return listaOb;
	}
	
	public List<String> retiraCamposNUll(Object[] obj, List<String> campos){
		
		for (int i = 0; i < obj.length; i++) {
			if(obj[i] == null){
				System.out.println("removido : "+campos.get(i));
				campos.set(i, "");
			}
		}
		
		return campos;
	}
}
