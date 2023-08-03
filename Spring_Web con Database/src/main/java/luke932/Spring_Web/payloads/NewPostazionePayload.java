package luke932.Spring_Web.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import luke932.Spring_Web.entities.TipoPostazione;

@Getter
@AllArgsConstructor
@ToString
public class NewPostazionePayload {

	private String descrizione;
	private int numerMaxOccupanti;
	private boolean disponibilita;
	private TipoPostazione tipoPostazione;
	private String citta;
}
