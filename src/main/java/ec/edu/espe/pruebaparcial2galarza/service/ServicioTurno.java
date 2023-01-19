package ec.edu.espe.pruebaparcial2galarza.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.pruebaparcial2galarza.model.Cliente;
import ec.edu.espe.pruebaparcial2galarza.model.Ejecutivo;
import ec.edu.espe.pruebaparcial2galarza.model.Turno;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioCliente;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioEjecutivo;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioTurno;

@Service
public class ServicioTurno {

	private final RepositorioTurno repositorioTurno;
	private final RepositorioCliente repositorioCliente;
	private final RepositorioEjecutivo repositorioEjecutivo;

	public ServicioTurno(RepositorioTurno repositorioTurno, RepositorioCliente repositorioCliente,
			RepositorioEjecutivo repositorioEjecutivo) {
		this.repositorioTurno = repositorioTurno;
		this.repositorioCliente = repositorioCliente;
		this.repositorioEjecutivo = repositorioEjecutivo;
	}

	@Transactional
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

	@Transactional
	public void registrarInicioAtencion(String numeroTurno, String codigoDeUsuario) {
		Turno turno = repositorioTurno.findByNumeroTurno(numeroTurno);
		if (turno != null) {
			turno = generarTurno(numeroTurno, codigoDeUsuario);
		}
		Ejecutivo ejecutivo = repositorioEjecutivo.findByCodigoDeUsuario(codigoDeUsuario);
		if (ejecutivo == null) {
			throw new IllegalArgumentException("Ejecutivo no existe");
		}
		if (turno.getFechaHoraInicioAtencion() != null) {
			throw new IllegalArgumentException("La atención ya ha comenzado");
		}
		turno.setFechaHoraInicioAtencion(new Date());
		turno.setCodigoUsuarioEjecutivo(codigoDeUsuario);
		repositorioTurno.save(turno);
	}

	@Transactional
	public void registroFinAtencion(String numeroTurno) {
		Turno turno = repositorioTurno.findByNumeroTurno(numeroTurno);
		if (turno == null) {
			throw new IllegalArgumentException("Numero de turno no existe");
		}
		if (turno.getFechaHoraInicioAtencion() == null) {
			throw new IllegalArgumentException("La atención aún no ha comenzado");
		}
		if (turno.getFechaHoraFinAtencion() != null) {
			throw new IllegalArgumentException("La atención ya ha finalizado");
		}
		turno.setFechaHoraFinAtencion(new Date());
		repositorioTurno.save(turno);
	}

	public void calificarTurno(String cedulaCliente, String numeroTurno, Integer calificacion) {
		Cliente cliente = repositorioCliente.findByCedula(cedulaCliente);
		if (cliente == null) {
			throw new IllegalArgumentException("Cédula de cliente no existe");
		}
		Turno turno = repositorioTurno.findByNumeroTurno(numeroTurno);
		if (turno == null) {
			throw new IllegalArgumentException("Turno no existe");
		}
		if (!turno.getCedulaCliente().equals(cedulaCliente)) {
			throw new IllegalArgumentException("Turno no pertenece al cliente");
		}
		if (turno.getFechaHoraFinAtencion() == null) {
			throw new IllegalArgumentException("Turno no ha sido atendido");
		}
		if (turno.getCalificacion() != null) {
			throw new IllegalArgumentException("Turno ya ha sido calificado");
		}
		turno.setCalificacion(calificacion);
		repositorioTurno.save(turno);
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
