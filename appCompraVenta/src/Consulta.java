import java.sql.*;

public class Consulta {
	
	
	/**
	 * Consulta que retorna cada vehículo con la cantidad de anuncios que tiene.
	 */
	public static ResultSet vehiculosCantidadAnuncios() {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT v.patente,mar.nombre AS marca,m.nombre AS modelo,cant_anuncios FROM (((SELECT patente_vehiculo,COUNT(*) AS cant_anuncios FROM Anuncio GROUP BY patente_vehiculo) a " +
							"JOIN (SELECT patente,id_modelo FROM Vehiculo) v ON a.patente_vehiculo=v.patente) " +
							"JOIN Modelo m ON v.id_modelo=m.id)" +
							"JOIN Marca mar ON m.id_marca=mar.id;";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	
	}

}