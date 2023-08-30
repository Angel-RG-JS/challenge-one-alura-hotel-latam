package one.alura.hotelAlura.pruebas;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import one.alura.hotelAlura.factory.ConnectionFactory;

public class PruebaUpdate {
	
	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
		
		Statement statement = con.createStatement();
        statement.execute("UPDATE HUESPEDES SET NOMBRE = 'Alfonso' WHERE ID = 99");
        
        System.out.println(statement.getUpdateCount());	
	}
}
