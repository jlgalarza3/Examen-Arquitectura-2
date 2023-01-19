package ec.edu.espe.pruebaparcial2galarza.repository;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.pruebaparcial2galarza.model.Ejecutivo;

public interface RepositorioEjecutivo extends CrudRepository<Ejecutivo, String> {

	Ejecutivo findByCodigoDeUsuario(String codigoDeUsuario);

}
