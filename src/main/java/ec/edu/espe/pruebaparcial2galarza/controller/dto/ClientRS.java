package ec.edu.espe.pruebaparcial2galarza.controller.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRS implements Serializable {

	private String id;
	private String name;
	private String lastName;
	private String address;
	private String phone;
	private String email;

}
