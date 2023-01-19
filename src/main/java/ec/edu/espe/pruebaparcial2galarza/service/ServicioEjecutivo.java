package ec.edu.espe.pruebaparcial2galarza.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.pruebaparcial2galarza.model.Ejecutivo;
import ec.edu.espe.pruebaparcial2galarza.model.Turno;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioEjecutivo;
import ec.edu.espe.pruebaparcial2galarza.repository.RepositorioTurno;

@Service
public class ServicioEjecutivo {

	private final RepositorioTurno repositorioTurno;
	private final RepositorioEjecutivo repositorioEjecutivo;
	private final ServicioTurno servicioTurno;

	public ServicioEjecutivo(RepositorioTurno repositorioTurno, RepositorioEjecutivo repositorioEjecutivo,
			ServicioTurno servicioTurno) {
		this.repositorioTurno = repositorioTurno;
		this.repositorioEjecutivo = repositorioEjecutivo;
		this.servicioTurno = servicioTurno;
	}

	@Transactional
	public void generarInicioAtencion(String numeroTurno, String codigoDeUsuario) {
		Turno turno = repositorioTurno.findByNumeroTurno(numeroTurno);
		if (turno != null) {
			turno = servicioTurno.generarTurno(turno.getCedulaCliente(), turno.getNombreCliente());
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

}
