package luke932.Spring_Web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.TipoPostazione;
import luke932.Spring_Web.payloads.NewPostazionePayload;
import luke932.Spring_Web.service.PostazioneService;

@RestController
@RequestMapping("/positions")
public class PostazioneController {

	@Autowired
	PostazioneService pstS;

	// #POST salvataggio postazioni
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Postazione savePst(@RequestBody NewPostazionePayload body) throws Exception {
		Postazione createPst = pstS.save(body);
		return createPst;
	}

	// #GET ritorna lista postazioni
	@GetMapping
	public List<Postazione> getPositions() {
		return pstS.getPositions();
	}

	@GetMapping("/{id_postazione}")
	public Postazione findById(@PathVariable("id_postazione") int id) throws Exception {
		return pstS.findById(id);
	}

	@PutMapping("/{id_postazione}")
	public Postazione findByIdAndUpdate(@PathVariable int id, @RequestBody Postazione body) throws Exception {
		return pstS.findByIdAndUpdate(id, body).orElseThrow(() -> new Exception("ID postazione non trovata"));
	}

	@DeleteMapping("/{id_postazione}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findByIdAndDelete(@PathVariable int id) {
		pstS.findByIdAndDelete(id);
	}

	@GetMapping("tipo&citta")
	public List<Postazione> searchPositionByTipoCitta(@RequestParam TipoPostazione tipo, @RequestParam String citta) {
		return pstS.findByTipoandCittà(tipo, citta);
	}

}
