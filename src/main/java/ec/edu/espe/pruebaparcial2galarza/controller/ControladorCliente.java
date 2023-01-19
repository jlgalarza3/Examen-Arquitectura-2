package ec.edu.espe.pruebaparcial2galarza.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import ec.edu.espe.pruebaparcial2galarza.controller.dto.ClienteRS;
import ec.edu.espe.pruebaparcial2galarza.controller.mapper.ClienteMapper;
import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import ec.edu.espe.pruebaparcial2galarza.service.ServicioCliente;

@RestController
@RequestMapping("/clientes")
public class ControladorCliente {

	private final ServicioCliente servicioCliente;

	public ControladorCliente(ServicioCliente servicioCliente) {
		this.servicioCliente = servicioCliente;
	}

	@GetMapping(value = "/{cedula}")
	public ResponseEntity<ClienteRS> obtenerInformacionCliente(String cedula) {
		try {
			Cliente cliente = servicioCliente.obtenerInformacionCliente(cedula);
			ClienteRS clienteRS = ClienteMapper.mapToClienteRS(cliente);
			return ResponseEntity.ok(clienteRS);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
