package ec.edu.espe.pruebaparcial2galarza.model;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ejecutivo {

	@Id
	private String id;
	private String codigoDeUsuario;
	private String nombreCompleto;
	private String escritorioDesignado;

}
