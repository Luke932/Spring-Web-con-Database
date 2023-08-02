package luke932.Spring_Web.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Prenotazione {

	private int id;

	private LocalDate dataPrenotazione;

	private Utente utente;

	private Postazione postazione;

}
