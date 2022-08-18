package com.springboot.form.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.springboot.form.editors.NombreMayusculaEditor;
import com.springboot.form.models.domain.Usuario;
import com.springboot.form.validadores.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador  validador;
	//
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		dateFormat.setLenient(false); //el analizador si es estricto o tolerante, si escribimos mal el formato de manera automatica lo convertira
		binder.registerCustomEditor(Date.class, "fecha", new CustomDateEditor(dateFormat, true));
		
		//Que atributo o campo convertir en mayusculas
		binder.registerCustomEditor(String.class,"nombre", new NombreMayusculaEditor());
	}
	
	
	// Metodo Handler 1. para mostrar de tipo get y otro para procesar
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Joel");
		usuario.setApellido("Vas");
		usuario.setIdentificador("123.456.j");

		model.addAttribute("titulo", "Resultado Form");
		model.addAttribute("usuario", usuario);

		return "form";
	}
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("España", "Mexico", "Peru","Nicaragua");
	}

	@PostMapping("/form")
	public String procesar(@Valid  Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		//	validador.validate(usuario, result);
		// Sessionstatus(interfaz)
		// Antes de procesar el formulario
		// BindingResult objeto propio de Spring, representa el resultado de la
		// validacion
		// contiene los mensajes de error en caso que haya una falla, se inyecta de
		// manera automatica
		// cuando tenemos anotado el @Valid, siempre el binding result tiene que estar
		// justo despues
		// del objeto que se valida objeto usuario, segundo el bindingresult
		model.addAttribute("titulo", "Resultado Form");
		// Un objeto nulo en java no se puede invocar metodos ni atributos
		if (result.hasErrors()) {
			// Pasar los mensajes de error a la vista, utilizando hashmap, map de java de
			// tipo String
			// -Map<String, String> errores = new HashMap<>();
			// obtener lista de errores.foreach -> funcion lambda
			// -result.getFieldErrors().forEach(err -> {
			// -errores.put(err.getField(),
			// - "El campo ".concat(err.getField()).concat("
			// ").concat(err.getDefaultMessage()));
			// -});
			// pasar a la vista los errores
			// model.addAttribute("error", errores);
			return "form";
		}

		// Pasar los mensajes de error a la vista
		model.addAttribute("usuario", usuario);
		status.setComplete(); //Completar el proceso de forma automatica elimina la sesion
		return "resultado";
	}

}
