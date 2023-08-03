package luke932.Spring_Web.payloads;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NewPrenotazionePayload {

	private int userId;
	private int postazioneId;
	private LocalDate data;
}
