package validadorContrasenias;
//package com.validarContrase�as;

public class CaracteresRepetidos extends Requisito{
	 public boolean validar(String contrasenia){
		for(int i=1;i<contrasenia.length()-1;i++) {
			if(contrasenia.charAt(i)==contrasenia.charAt(i+1) && contrasenia.charAt(i)== contrasenia.charAt(i-1)) 
				return false;
		}
		return true;
	}
	
}

