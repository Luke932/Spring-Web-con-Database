package luke932.Spring_Web.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Utente {

	@Id
	@GeneratedValue
	private int id;

	private String username;

	private String nomeCompleto;

	private String email;

	@OneToMany(mappedBy = "utente")
	private List<Prenotazione> prenotazioni;

	public Utente(String name, String surname, String email) {
		this.nomeCompleto = name;
		this.username = surname;
		this.email = email;

	}
}