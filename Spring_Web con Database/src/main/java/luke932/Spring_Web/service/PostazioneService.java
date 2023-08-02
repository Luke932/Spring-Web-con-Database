package luke932.Spring_Web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.TipoPostazione;

@Service
public class PostazioneService {

	private List<Postazione> postazioni = new ArrayList<>();

	public Postazione save(Postazione pst) {
		Random rndm = new Random();
		pst.setId(Math.abs(rndm.nextInt()));
		this.postazioni.add(pst);
		return pst;
	}

	public List<Postazione> getPositions() {
		return this.postazioni;
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

	public Optional<Postazione> findById(int id) {
		Postazione u = null;

		for (Postazione postazioneCorrente : postazioni)
			if (postazioneCorrente.getId() == id)
				u = postazioneCorrente;

		return Optional.ofNullable(u);
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
				found.setCodice(position.getCodice());
				found.setDescrizione(position.getDescrizione());
				found.setTipo(position.getTipo());
				found.setCittà(position.getCittà());
				found.setNumeroMassimoOccupanti(position.getNumeroMassimoOccupanti());
			}

		return Optional.ofNullable(found);
	}

}
