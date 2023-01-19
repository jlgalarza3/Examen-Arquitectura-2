package ec.edu.espe.pruebaparcial2galarza.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.pruebaparcial2galarza.model.Turno;
import ec.edu.espe.pruebaparcial2galarza.service.ServicioTurno;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/turnos")
public class ControladorTurnos {

	private final ServicioTurno servicioTurno;

	public ControladorTurnos(ServicioTurno servicioTurno) {
		this.servicioTurno = servicioTurno;
	}

	@PostMapping("/generar-turno")
	public ResponseEntity<Turno> generarTurno(@RequestBody String cedulaCliente,
			String nombreCliente) {
		Turno turno = servicioTurno.generarTurno(cedulaCliente, nombreCliente);
		return ResponseEntity.ok(turno);
	}

}
