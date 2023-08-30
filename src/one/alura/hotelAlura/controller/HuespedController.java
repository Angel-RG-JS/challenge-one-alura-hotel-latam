package one.alura.hotelAlura.controller;

import java.sql.Date;
import java.util.List;

import one.alura.hotelAlura.dao.HuespedDAO;
import one.alura.hotelAlura.factory.ConnectionFactory;
import one.alura.hotelAlura.modelo.Reserva;
import one.alura.hotelAlura.modelo.Huesped;


public class HuespedController {
	
	private HuespedDAO huespedDao;

	public HuespedController() {
		this.huespedDao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, Integer id) {
		return huespedDao.modificar(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, id);
	}

	public int eliminar(Integer id) {
		return huespedDao.eliminar(id);
	}

	public List<Huesped> listar() {
		return huespedDao.listar();
	}
	
	public List<Huesped> listar(Reserva reserva) {
		return huespedDao.listar(reserva.getId());
	}
	
	public List<Huesped> busqueda(String busqueda) {
        return this.huespedDao.listarPorBusqueda(busqueda);
    }

    public void guardar(Huesped huesped, Integer idReserva) {
    	huespedDao.guardar(huesped); 
    	huesped.setIdReserva(idReserva);
	}
}
