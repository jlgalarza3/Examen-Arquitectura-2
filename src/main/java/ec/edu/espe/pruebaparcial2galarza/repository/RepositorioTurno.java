package ec.edu.espe.pruebaparcial2galarza.repository;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.pruebaparcial2galarza.model.Turno;

public interface RepositorioTurno extends CrudRepository<Turno, String> {

	Turno findFirstByOrderByNumeroTurnoDesc();

}
