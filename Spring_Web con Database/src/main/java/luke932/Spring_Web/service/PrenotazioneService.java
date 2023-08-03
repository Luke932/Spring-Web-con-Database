package luke932.Spring_Web.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.Prenotazione;
import luke932.Spring_Web.entities.Utente;
import luke932.Spring_Web.payloads.NewPrenotazionePayload;
import luke932.Spring_Web.repositories.PrenotazioneRepository;

@Service
public class PrenotazioneService {

	private final PrenotazioneRepository pr;
	private final UtenteService utS;
	private final PostazioneService pstS;

	@Autowired
	public PrenotazioneService(PrenotazioneRepository pr, UtenteService us, PostazioneService ps) {
		this.pr = pr;
		this.utS = us;
		this.pstS = ps;
	}

	public Prenotazione save(NewPrenotazionePayload body) throws Exception {

		int userId = body.getUserId();
		int postazioneId = body.getPostazioneId();
		LocalDate dataPrenotazione = body.getData();
		Utente user = utS.findById(userId);

		Postazione postazione = pstS.findById(postazioneId);

//		for (Prenotazione prenotazione : prenotazioni) {
//			if (prenotazione.getDataPrenotazione().equals(dataPrenotazione)) {
//				throw new IllegalArgumentException("Data già prenotata per questa postazione");
//			}
//		}

		int idPrenotazione = Math.abs(new Random().nextInt());
		Prenotazione Newprenotazione = new Prenotazione(idPrenotazione, dataPrenotazione, postazione, user);

		return pr.save(Newprenotazione);

	}

	public List<Prenotazione> getBookings() {
		return pr.findAll();
	}

//	public Optional<Prenotazione> findByid(int id) {
//		Prenotazione booking = null;
//
//		for (Prenotazione currentbook : prn)
//			if (currentbook.getId() == id)
//				booking = currentbook;
//
//		return Optional.ofNullable(booking);
//	}
//
//	public void findByIdandDelete(int id) {
//		ListIterator<Prenotazione> iterator = this.prn.listIterator();
//
//		while (iterator.hasNext()) {
//			Prenotazione currentbook = iterator.next();
//			if (currentbook.getId() == id)
//				iterator.remove();
//		}
//	}

}
