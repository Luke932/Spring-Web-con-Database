package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luke932.Spring_Web.entities.Postazione;
import luke932.Spring_Web.entities.TipoPostazione;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, Integer> {
	List<Postazione> findByTipoAndCittà(TipoPostazione tipo, String città);
}
