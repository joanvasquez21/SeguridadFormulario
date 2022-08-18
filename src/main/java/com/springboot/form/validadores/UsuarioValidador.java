package com.springboot.form.validadores;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.springboot.form.models.domain.Usuario;

//para que se pueda inyectar despues en el controlador y validar
@Component
public class UsuarioValidador implements Validator{

	//Para indicar que clase entity o pojo vamos a validar
	@Override
	public boolean supports(Class<?> clazz) {		
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
			//Usuario usuario= (Usuario)target;
			//ValidationUtils.rejectifempty rechaza la validacion con un mensaje de error
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
			
			//Validad el identificador
			//if(usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") ==false) {
				//errors.rejectValue("identificador", "pattern.usuario.identificador");
			//}
			
	}

}
