package ec.edu.espe.pruebaparcial2galarza.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import ec.edu.espe.pruebaparcial2galarza.model.Turno;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioCliente;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioTurno;

@Service
public class ServicioTurno {

	private final RepositorioTurno repositorioTurno;
	private final RepositorioCliente repositorioCliente;

	public ServicioTurno(RepositorioTurno repositorioTurno, RepositorioCliente repositorioCliente) {
		this.repositorioTurno = repositorioTurno;
		this.repositorioCliente = repositorioCliente;
	}

	public Turno generarTurno(String cedulaCliente, String nombreCompleto) {
		Cliente client = repositorioCliente.findByCedula(cedulaCliente);
		if (client == null) {
			throw new IllegalArgumentException("Cédula de cliente no existe");
		}
		String numeroSiguienteTurno = generarNumeroSiguienteTurno();
		Turno turno = Turno.builder()
				.numeroTurno(numeroSiguienteTurno)
				.cedulaCliente(cedulaCliente)
				.nombreCliente(numeroSiguienteTurno)
				.fechaHoraGeneracion(new Date())
				.build();

		return repositorioTurno.save(turno);

	}

	private String generarNumeroSiguienteTurno() {
		Turno numeroTurno = repositorioTurno.findFirstByOrderByNumeroTurnoDesc();
		if (numeroTurno == null) {
			return "A0001";
		}
		String numeroUltimoTurno = numeroTurno.getNumeroTurno();
		String prefijo = numeroUltimoTurno.substring(0, 1);
		String parteNumero = numeroUltimoTurno.substring(1);
		int siguienteNumero = Integer.parseInt(parteNumero) + 1;
		String siguienteNumeroParte = String.format("%04d", siguienteNumero);
		return prefijo + siguienteNumeroParte;
	}

}
