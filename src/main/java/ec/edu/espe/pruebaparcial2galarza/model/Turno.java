package ec.edu.espe.pruebaparcial2galarza.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Turno {

	@Id
	private String id;
	private String numeroTurno;
	private String cedulaCliente;
	private String nombreCliente;
	private String codigoUsuarioEjecutivo;
	private String nombreEjecutivo;
	private Date fechaHoraGeneracion;
	private Date fechaHoraInicioAtencion;
	private Date fechaHoraFinAtencion;
	private Integer calificacion;

}
