package ec.edu.espe.pruebaparcial2galarza.repository;

import org.springframework.data.repository.CrudRepository;

import ec.edu.espe.pruebaparcial2galarza.model.Cliente;

public interface RepositorioCliente extends CrudRepository<Cliente, String> {

	Cliente findByCedula(String cedula);

}
