package ec.edu.espe.pruebaparcial2galarza.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRS implements Serializable {

	private String cedula;
	private String nombres;
	private String apellidos;

}
