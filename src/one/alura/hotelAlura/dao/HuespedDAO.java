package one.alura.hotelAlura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import one.alura.hotelAlura.modelo.Huesped;

public class HuespedDAO {

	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO HUESPEDES "
							+ "(nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, idReserva)"
							+ " VALUES (?, ?, ?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);

			try(statement) {
				ejecutaRegistro(huesped, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

	private void ejecutaRegistro(Huesped huesped, PreparedStatement statement)
			throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaDeNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try(resultSet) {
			while(resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(
						String.format(
								"Fue insertado el huésped %s", huesped));
			}
		}
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();

		try {
			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, APELLIDO, FECHADENACIMIENTO, NACIONALIDAD, TELEFONO, IDRESERVA FROM HUESPEDES");

			try(statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try(resultSet) {
					while(resultSet.next()) {
						resultado.add(new Huesped(
								resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHADENACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("IDRESERVA")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public int modificar(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, Integer id) {
		try {
	        final PreparedStatement statement = con.prepareStatement(
	                "UPDATE HUESPEDES SET "
	                + " NOMBRE = ?, "
	                + " APELLIDO = ?,"
	                + " FECHADENACIMIENTO = ?,"
	                + " NACIONALIDAD = ?,"
	                + " TELEFONO = ?"
	                + " WHERE ID = ?");

	        try (statement) {
	            statement.setString(1, nombre);
	            statement.setString(2, apellido);
	            statement.setDate(3, fechaDeNacimiento);
	            statement.setString(4, nacionalidad);
	            statement.setString(5, telefono);
	            statement.setInt(6, id);
	            statement.execute();

	            int updateCount = statement.getUpdateCount();

	            return updateCount;
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");
			
			try(statement) {
				statement.setInt(1, id);
				
		        statement.execute();
		        
		        int deleteCount = statement.getUpdateCount();
		    
		        return deleteCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Huesped> listarPorBusqueda(String busqueda) {
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT * FROM HUESPEDES WHERE ID LIKE '%" + busqueda 
					+ "%' OR NOMBRE LIKE '%" + busqueda
					+ "%' OR APELLIDO LIKE '%" + busqueda
					+ "%' OR FECHADENACIMIENTO LIKE '%" + busqueda
					+ "%' OR NACIONALIDAD LIKE '%" + busqueda
					+ "%' OR TELEFONO LIKE '%" + busqueda + "%'";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet) {
					while(resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHADENACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"));
						resultado.add(huesped);
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}


	public List<Huesped> listar(Integer idReserva) {
		List<Huesped> resultado = new ArrayList<>();

		try {
			var querySelect = "\"SELECT ID, NOMBRE, APELLIDO, FECHADENACIMIENTO, NACIONALIDAD, TELEFONO "
					+ "FROM HUESPEDES "
					+ "WHERE IDRESERVA = ?";
			
			System.out.println(querySelect);
			
			final PreparedStatement statement = con
					.prepareStatement(querySelect);

			try(statement) {
				statement.setInt(1, idReserva);
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try(resultSet) {
					while(resultSet.next()) {
						resultado.add(new Huesped(
								resultSet.getInt("ID"),
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getDate("FECHADENACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO")));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
}
