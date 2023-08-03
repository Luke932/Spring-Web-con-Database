package luke932.Spring_Web.payloads;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewUtentePayload {

	private String name;
	private String surname;
	private String email;

	public NewUtentePayload(String name, String surname, String email) {

		this.name = name;
		this.surname = surname;
		this.email = email;
	}

}
