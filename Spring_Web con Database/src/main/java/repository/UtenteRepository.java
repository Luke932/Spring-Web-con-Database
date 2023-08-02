package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luke932.Spring_Web.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
