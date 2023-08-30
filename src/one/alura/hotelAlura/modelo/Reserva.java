package one.alura.hotelAlura.modelo;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

public class Reserva {
	
	private Integer id;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private Double valor;
	
	private String formaPago;
	
	private List<Huesped> huespedes;
	
	public Reserva(Date fechaDeEntrada, Date fechaDeSalida, Double valor, String formaPago) {
		this.fechaEntrada = fechaDeEntrada;
		this.fechaSalida = fechaDeSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}
	
	public Reserva(int id, Date fechaEntrada, Date fechaSalida, Double valor, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public Date getFechaDeEntrada() {
		return fechaEntrada;
	}

	public void setFechaDeEntrada(Date fechaDeEntrada) {
		this.fechaEntrada = fechaDeEntrada;
	}

	public Date getFechaDeSalida() {
		return fechaSalida;
	}

	public void setFechaDeSalida(Date fechaDeSalida) {
		this.fechaSalida = fechaDeSalida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getFormaDePago() {
		return formaPago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaPago = formaDePago;
	}

	@Override
	public String toString() {
		return String.format(
				"No. %s, Entrada %tD", 
				this.id,
				this.fechaEntrada
		);
	}
	
	public void agregar(Huesped huesped) {
		if (this.huespedes == null) {
			this.huespedes = new ArrayList<>();
		}
		
		this.huespedes.add(huesped);
	}

	public List<Huesped> getHuespedes() {
		return this.huespedes;
	}

}
