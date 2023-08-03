package luke932.Spring_Web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.TipoPostazione;
import luke932.Spring_Web.payloads.NewPostazionePayload;
import luke932.Spring_Web.repositories.PostazioneRepository;

@Service
public class PostazioneService {

	private List<Postazione> postazioni = new ArrayList<>();

	@Autowired
	PostazioneRepository postazioniRepository;

	public Postazione save(NewPostazionePayload body) throws Exception {
		boolean disponibilita = body.isDisponibilita();
		boolean postazioneDisp = postazioniRepository.findByDisponibilita(disponibilita).isPresent();
		if (postazioneDisp) {
			throw new Exception("Una postazione con disponibilità " + disponibilita + " è già presente");
		}
		Postazione newPostazione = new Postazione(body.getDescrizione(), body.getNumerMaxOccupanti(),
				body.isDisponibilita(), body.getTipoPostazione(), body.getCitta());
		return postazioniRepository.save(newPostazione);
	}

	public List<Postazione> getPositions() {
		return postazioniRepository.findAll();
	}

	public List<Postazione> findByTipoandCittà(TipoPostazione tipo, String città) {
		List<Postazione> result = new ArrayList<>();
		for (Postazione postazione : postazioni) {
			if (postazione.getTipo() == tipo && postazione.getCittà().equalsIgnoreCase(città)) {
				result.add(postazione);
			}
		}
		return result;
	}

	public Postazione findById(int id) throws Exception {
		return postazioniRepository.findById(id).orElseThrow(() -> new Exception("ID postazione non trovato"));
	}

	public void findByIdAndDelete(int id) {
		ListIterator<Postazione> iterator = this.postazioni.listIterator();

		while (iterator.hasNext()) {
			Postazione currentposition = iterator.next();
			if (currentposition.getId() == id)
				iterator.remove();
		}
	}

	public Optional<Postazione> findByIdAndUpdate(int id, Postazione position) {
		Postazione found = null;

		for (Postazione currentpositions : postazioni)
			if (currentpositions.getId() == id) {
				found = currentpositions;
				found.setId(id);
				found.setDescrizione(position.getDescrizione());
				found.setTipo(position.getTipo());
				found.setCittà(position.getCittà());
				found.setNumeroMassimoOccupanti(position.getNumeroMassimoOccupanti());
			}

		return Optional.ofNullable(found);
	}

}
