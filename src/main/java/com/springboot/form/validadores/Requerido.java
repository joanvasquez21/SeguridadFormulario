package com.springboot.form.validadores;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy  = RequeridoValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface Requerido {
	
	String message() default "Campo APELLIDO es requerido";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
