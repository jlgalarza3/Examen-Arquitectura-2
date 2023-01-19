package ec.edu.espe.pruebaparcial2galarza.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "cliente")
public class Cliente {

	@Id
	private String id;
	@Indexed
	private String cedula;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String direccionPrincipal;
	private String telefono;
	private String correoElectronico;
	private List<CuentaCliente> cuentas;
}
