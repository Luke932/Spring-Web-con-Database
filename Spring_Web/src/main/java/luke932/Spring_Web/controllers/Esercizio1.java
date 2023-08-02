package luke932.Spring_Web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages")
public class Esercizio1 {

	@GetMapping("/type")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String languageIta(@RequestParam String lang) {
		if (lang.equalsIgnoreCase("italiano")) {
			return "Testo in lingua italiana";
		} else if (lang.equalsIgnoreCase("english")) {
			return "Text in english language";
		} else {
			throw new IllegalArgumentException("Lingua non supportata");
		}
	}
}
