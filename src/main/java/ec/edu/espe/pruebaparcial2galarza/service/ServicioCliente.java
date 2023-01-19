package ec.edu.espe.pruebaparcial2galarza.service;

import org.springframework.stereotype.Service;

import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioCliente;

@Service
public class ServicioCliente {

	private final RepositorioCliente repositorioCliente;

	public ServicioCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}

	public Cliente obtenerInformacionCliente(String cedula) {
		return repositorioCliente.findByCedula(cedula);
	}

}
