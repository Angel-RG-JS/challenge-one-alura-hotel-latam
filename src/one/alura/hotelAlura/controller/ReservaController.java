package one.alura.hotelAlura.controller;

import java.sql.Date;
import java.util.List;

import one.alura.hotelAlura.dao.ReservaDAO;
import one.alura.hotelAlura.factory.ConnectionFactory;
import one.alura.hotelAlura.modelo.Reserva;

public class ReservaController {
	
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
    	reservaDAO.guardar(reserva); 
	}
	
	public int modificar(Date fechaEntrada, Date fechaSalida, Double valor, String formaPago, Integer id) {
		return reservaDAO.modificar(fechaEntrada, fechaSalida, valor, formaPago, id);
	}
	
	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

    public List<Reserva> cargaReporte() {
        return this.reservaDAO.listarPorHuespedes();
    }
    
    public List<Reserva> busqueda(String busqueda) {
        return this.reservaDAO.listarPorBusqueda(busqueda);
    }

}
