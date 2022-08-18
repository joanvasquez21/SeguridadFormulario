package com.springboot.form.validadores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;



public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//StringUtils si es distinto a vacio y si tiene texto
		if(value==null || !StringUtils.hasText(value)) {
			return false;
		}
		return true;
	}

}
