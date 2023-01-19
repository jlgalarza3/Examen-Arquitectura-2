package ec.edu.espe.pruebaparcial2galarza.controller.dto;

import ec.edu.espe.pruebaparcial2galarza.model.Cliente;

public class ClienteMapper {

	public static ClienteRS mapToClienteRS(Cliente cliente) {
		return ClienteRS.builder()
				.cedula(cliente.getCedula())
				.nombres(cliente.getNombres())
				.apellidos(cliente.getApellidos())
				.build();
	}

}
