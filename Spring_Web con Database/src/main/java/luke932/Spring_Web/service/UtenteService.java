package luke932.Spring_Web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Utente;
import luke932.Spring_Web.payloads.NewUtentePayload;
import luke932.Spring_Web.repositories.UtenteRepository;

@Service
public class UtenteService {

	private List<Utente> userP = new ArrayList<>();

	@Autowired
	UtenteRepository userRepository;

	public Utente save(NewUtentePayload body) {
		userRepository.findByEmail(body.getEmail());
		Utente newUser = new Utente(body.getName(), body.getSurname(), body.getEmail());
		return userRepository.save(newUser);
	}

	public List<Utente> getUsers() {
		return userRepository.findAll();
	}

	public Utente findById(int id) throws Exception {
		Optional<Utente> optionalUtente = userRepository.findById(id);
		if (optionalUtente.isPresent()) {
			Utente utente = optionalUtente.get();
			// Log per verificare se l'utente è stato trovato
			System.out.println("Utente trovato: " + utente);
			return utente;
		} else {
			// Log per verificare se l'utente non è stato trovato
			System.out.println("Utente non trovato per ID: " + id);
			throw new Exception("ID utente non trovato");
		}
	}

	public void findByIdAndDelete(int id) {
		ListIterator<Utente> iterator = this.userP.listIterator();

		while (iterator.hasNext()) {
			Utente currentUser = iterator.next();
			if (currentUser.getId() == id)
				iterator.remove();
		}
	}

	public Optional<Utente> findByIdAndUpdate(int id, Utente users) {
		Utente found = null;

		for (Utente currentUser : userP) {
			if (currentUser.getId() == id) {
				found = currentUser;
				found.setId(id);
				found.setUsername(users.getUsername());
				found.setNomeCompleto(users.getNomeCompleto());
				found.setEmail(users.getEmail());
			}
		}
		return Optional.ofNullable(found);
	}

}
