package luke932.Spring_Web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import luke932.Spring_Web.entities.Utente;

@Service
public class UtenteService {

	private List<Utente> userP = new ArrayList<>();

	public Utente save(Utente user) {
		Random rndm = new Random();
		user.setId(Math.abs(rndm.nextInt()));
		this.userP.add(user);
		return user;
	}

	public List<Utente> getUsers() {
		return this.userP;
	}

	public Optional<Utente> findById(int id) {
		Utente u = null;

		for (Utente utenteCorrente : userP)
			if (utenteCorrente.getId() == id)
				u = utenteCorrente;

		return Optional.ofNullable(u);
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
