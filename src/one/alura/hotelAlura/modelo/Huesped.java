package one.alura.hotelAlura.modelo;

import java.sql.Date;

public class Huesped {

	private Integer id;
	
	private String nombre;
	
	private String apellido;
	
	private Date fechaDeNacimiento;
	
	private String nacionalidad;
	
	private String telefono;
	
	private Integer idReserva;

	public Huesped(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}

	public Huesped(int id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public Huesped(int id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, Integer idReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.idReserva = idReserva;
	}
	
	public Huesped(int id, String nombre, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int getIdReserva() {
		return this.idReserva;	
	}
	
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;	
	}

	@Override
	public String toString() {
		return String.format(
				"{id: %s, nombre: %s, apellido: %s, fecha de nacimiento: %tD, nacionalidad: %s, telefono: %s, idReserva: %s}", 
				this.id,
				this.nombre,
				this.apellido,
				this.fechaDeNacimiento,
				this.nacionalidad,
				this.telefono,
				this.idReserva
		);
	}
}
