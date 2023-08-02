package luke932.Spring_Web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import luke932.Spring_Web.entities.Prenotazione;
import luke932.Spring_Web.payloads.NewPostazioneBody;
import luke932.Spring_Web.service.PrenotazioneService;

@RestController
@RequestMapping("/bookings")
public class PrenotazioneController {

	@Autowired
	PrenotazioneService bookS;

	// #POST salvataggio prenotazione
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione saveBook(@RequestBody NewPostazioneBody body) throws Exception {
		return bookS.save(body);
	}

	// #GET ritorna lista postazioni
	@GetMapping
	public List<Prenotazione> getUsers() {
		return bookS.getBookings();
	}

	@GetMapping("/{id_prenotazione}")
	public Prenotazione findById(@PathVariable int id) throws Exception {
		return bookS.findByid(id).orElseThrow(() -> new Exception("ID prenotazione non trovata"));
	}

	@DeleteMapping("/id_prenotazione")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable int id) {
		bookS.findByIdandDelete(id);
	}

}
