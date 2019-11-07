package it.objectmethod.cometa.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.cometa.model.Documento;

@Component
public class DocumentiValidator {
	
	//TODO vari Autowired dao
	
	//Invece di string usare un enum ValidationResult con i vari tipi di errore 
	public List<String> validate(Documento doc) {
		List<String> errori = new ArrayList<>();
		//... richiamo metodi e faccio add sulla list
		return null;
	}
	
	private String validateDocumento() {
		return null;
	}
	
	private String validateLotto() {
		return null;
	}
	
	private String validateArticolo() {
		return null;
	}

}
