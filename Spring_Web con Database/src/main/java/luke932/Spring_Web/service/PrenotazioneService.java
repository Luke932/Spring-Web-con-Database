package luke932.Spring_Web.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.Prenotazione;
import luke932.Spring_Web.entities.Utente;
import luke932.Spring_Web.payloads.NewPostazioneBody;

@Service
public class PrenotazioneService {

	private List<Prenotazione> prn = new ArrayList<>();

	@Autowired
	UtenteService utS;

	@Autowired
	PostazioneService pstS;

	public Prenotazione save(NewPostazioneBody newBody) throws Exception {

		LocalDate dataCorrente = newBody.getDate();
		Postazione postazioneCorrente = pstS.findById(newBody.getId_postazione())
				.orElseThrow(() -> new Exception("Postazione non trovata"));
		Utente utenteCorrente = utS.findById(newBody.getId_utente())
				.orElseThrow(() -> new Exception("Utente non trovato"));

		for (Prenotazione p : getBookings()) {
			// Check postazione data occupata
			if (p.getPostazione().getId() == postazioneCorrente.getId()
					&& p.getDataPrenotazione().equals(dataCorrente)) {
				throw new Exception("La postazione è già occupata in questa data");
			}

			// Check utente con più prenotazioni nello stesso giorno
			if (p.getUtente().getId() == utenteCorrente.getId() && p.getDataPrenotazione().equals(dataCorrente)) {
				throw new Exception("L'utente ha già prenotato una postazione in questa data");
			}
		}

		// Check data di prenotazione entro due giorni prima
		if (LocalDate.now().isAfter(dataCorrente.minusDays(2))) {
			throw new Exception("Troppo tardi per prenotare");
		}

		// Costruzione prenotazione dal body
		int id_prenotazione = Math.abs(new Random().nextInt());
		Prenotazione prenotazioneCorrente = new Prenotazione(id_prenotazione, dataCorrente, utenteCorrente,
				postazioneCorrente);

		this.prn.add(prenotazioneCorrente);

		return prenotazioneCorrente;
	}

	public List<Prenotazione> getBookings() {
		return this.prn;
	}

	public Optional<Prenotazione> findByid(int id) {
		Prenotazione booking = null;

		for (Prenotazione currentbook : prn)
			if (currentbook.getId() == id)
				booking = currentbook;

		return Optional.ofNullable(booking);
	}

	public void findByIdandDelete(int id) {
		ListIterator<Prenotazione> iterator = this.prn.listIterator();

		while (iterator.hasNext()) {
			Prenotazione currentbook = iterator.next();
			if (currentbook.getId() == id)
				iterator.remove();
		}
	}

	public boolean makeReservation(LocalDate date, String workstationId) {
		if (date.isBefore(LocalDate.now().plusDays(2))) {
			return false;
		}
		return false;
	}
}
