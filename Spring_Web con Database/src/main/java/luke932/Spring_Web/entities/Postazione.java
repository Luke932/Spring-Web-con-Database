package luke932.Spring_Web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Postazione {

	@Id
	@GeneratedValue
	private int id;

	private boolean disponibilita;

	private String descrizione;

	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;

	private String città;

	private int numeroMassimoOccupanti;

	public Postazione(String descrizione, int numerMaxOccupanti, boolean disponibilita, TipoPostazione tipoPostazione,
			String citta) {
		this.descrizione = descrizione;
		this.numeroMassimoOccupanti = numerMaxOccupanti;
		this.disponibilita = disponibilita;
		this.tipo = tipoPostazione;
		this.città = citta;
	}

}
