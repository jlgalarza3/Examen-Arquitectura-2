package ec.edu.espe.pruebaparcial2galarza.controller.mapper;

import ec.edu.espe.pruebaparcial2galarza.controller.dto.ClientRS;
import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientMapper {

	public static ClientRS toClientRS(Cliente client) {
		return ClientRS.builder().id(client.getId()).name(client.getName()).lastName(client.getLastName())
				.address(client.getAddress()).phone(client.getPhone()).email(client.getEmail()).build();
	}

}
