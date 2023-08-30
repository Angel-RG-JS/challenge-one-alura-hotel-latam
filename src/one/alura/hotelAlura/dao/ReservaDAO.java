package one.alura.hotelAlura.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import one.alura.hotelAlura.modelo.Reserva;
import one.alura.hotelAlura.modelo.Huesped;

public class ReservaDAO {
	
	private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO RESERVAS "
							+ "(fechaEntrada, fechaSalida, valor, formaPago)"
							+ " VALUES (?, ?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);

			try(statement) {
				ejecutaRegistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}

	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement)
			throws SQLException {
		statement.setDate(1, reserva.getFechaDeEntrada());
		statement.setDate(2, reserva.getFechaDeSalida());
		statement.setDouble(3, reserva.getValor());
		statement.setString(4, reserva.getFormaDePago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try(resultSet) {
			while(resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(
						String.format(
								"Fue insertada la reserva %s", reserva));
			}
		}
	}
	
	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT ID, FECHAENTRADA, FECHASALIDA, VALOR, FORMAPAGO FROM RESERVAS";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet) {
					while(resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("ID"),
								resultSet.getDate("FECHAENTRADA"),
								resultSet.getDate("FECHASALIDA"),
								resultSet.getDouble("VALOR"),
								resultSet.getString("FORMAPAGO"));
						resultado.add(reserva);
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}
	
	public List<Reserva> listarPorBusqueda(String busqueda) {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT * FROM RESERVAS WHERE ID LIKE '%" + busqueda 
					+ "%' OR FECHAENTRADA LIKE '%" + busqueda
					+ "%' OR FECHASALIDA LIKE '%" + busqueda
					+ "%' OR VALOR LIKE '%" + busqueda
					+ "%' OR FORMAPAGO LIKE '%" + busqueda + "%'";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet) {
					while(resultSet.next()) {
						var reserva = new Reserva(resultSet.getInt("ID"),
								resultSet.getDate("FECHAENTRADA"),
								resultSet.getDate("FECHASALIDA"),
								resultSet.getDouble("VALOR"),
								resultSet.getString("FORMAPAGO"));
						resultado.add(reserva);
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}


	public List<Reserva> listarPorHuespedes() {
		List<Reserva> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT R.ID, R.FECHAENTRADA, R.FECHASALIDA, R.VALOR, R.FORMAPAGO, H.ID, H.NOMBRE, H.APELLIDO, H.FECHADENACIMIENTO, H.NACIONALIDAD, H.TELEFONO "
					+ "FROM RESERVAS R "
					+ "INNER JOIN HUESPEDES H ON R.ID = H. IDRESERVA";
			System.out.println(querySelect);
			
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			
			try(statement) {
				final ResultSet resultSet = statement.executeQuery();

				try(resultSet) {
					while(resultSet.next()) {
						Integer reservaId = resultSet.getInt("ID");
						Date reservaFechaDeEntrada = resultSet.getDate("FECHAENTRADA");
						Date reservaFechaDeSalida = resultSet.getDate("FECHASALIDA");
						Double reservaValor = resultSet.getDouble("VALOR");
						String reservaFormaDePago = resultSet.getString("FORMAPAGO");
						
						var reserva = resultado
								.stream()
								.filter(res -> res.getId().equals(reservaId))
								.findAny().orElseGet(()-> {
									Reserva res = new Reserva(reservaId,
											reservaFechaDeEntrada,
											reservaFechaDeSalida,
											reservaValor,
											reservaFormaDePago);
									
									resultado.add(res);
									
									return res;
								});
								
						Huesped huesped = new Huesped(resultSet.getInt("H.ID"),
								resultSet.getString("H.NOMBRE"),
								resultSet.getString("H.APELLIDO"),
								resultSet.getDate("H.FECHADENACIMIENTO"),
								resultSet.getString("H.NACIONALIDAD"),
								resultSet.getString("H.TELEFONO"));
						
						reserva.agregar(huesped);
					}
				};
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return resultado;
	}

	public int modificar(Date fechaEntrada, Date fechaSalida, Double valor, String formaPago, Integer id) {
		try {
	        final PreparedStatement statement = con.prepareStatement(
	                "UPDATE RESERVAS SET "
	                + " FECHAENTRADA = ?, "
	                + " FECHASALIDA = ?,"
	                + " VALOR = ?,"
	                + " FORMAPAGO = ?"
	                + " WHERE ID = ?");

	        try (statement) {
	            statement.setDate(1, fechaEntrada);
	            statement.setDate(2, fechaSalida);
	            statement.setDouble(3, valor);
	            statement.setString(4, formaPago);
	            statement.setInt(5, id);
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?");
			
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
}
