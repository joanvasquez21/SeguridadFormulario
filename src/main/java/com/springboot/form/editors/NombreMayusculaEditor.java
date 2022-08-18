package com.springboot.form.editors;

import java.beans.PropertyEditorSupport;

public class NombreMayusculaEditor extends PropertyEditorSupport{

	//Asignar como un texto(recibe el valor de cada campo del formulario)
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		setValue(text.toUpperCase().trim());
	}
	

	
	
}
