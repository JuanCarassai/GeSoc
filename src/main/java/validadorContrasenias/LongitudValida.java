package validadorContrasenias;
//package com.validarContrase�as;

public class LongitudValida extends Requisito{
	public boolean validar(String contrasenia){
		return contrasenia.length() >= 8;
			
	}
}
