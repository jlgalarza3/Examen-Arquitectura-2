package ec.edu.espe.pruebaparcial2galarza.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import ec.edu.espe.pruebaparcial2galarza.controller.dto.ClientRS;
import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioCliente;
import ec.edu.espe.pruebaparcial2galarza.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public ResponseEntity<List<ClientRS>> getAll() {
		List<ClientRS> clients = new ArrayList<>();
		clientService.findAll().forEach(client -> clients.add(ClientRS.builder().id(client.getId())
				.name(client.getName()).lastName(client.getLastName()).address(client.getAddress())
				.phone(client.getPhone()).email(client.getEmail()).build()));
		return ResponseEntity.ok(clients);
	}

}
