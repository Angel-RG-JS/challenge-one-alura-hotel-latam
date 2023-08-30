package one.alura.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

import one.alura.hotelAlura.factory.ConnectionFactory;

public class PruebaPoolDeConexiones {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		for (int i = 0; i < 20; i++) {
			Connection conexion = connectionFactory.recuperaConexion();
			
			System.out.println("Abriendo la conexion de número " + (i + 1));
		}
	}

}
