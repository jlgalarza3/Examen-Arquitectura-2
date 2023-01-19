package ec.edu.espe.pruebaparcial2galarza.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuentaCliente {
	private String numeroCuenta;
	private String tipoCuenta;
	private BigDecimal saldoPromedio;
}
