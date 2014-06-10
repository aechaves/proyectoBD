import java.sql.*;

public class Consulta {
	
	
	/**
	 * Consulta que retorna cada vehículo con la cantidad de anuncios que tiene.
	 */
	public static ResultSet vehiculosCantidadAnuncios() {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT v.patente,mar.nombre AS marca,m.nombre AS modelo,cant_anuncios FROM " +
							"(((SELECT patente_vehiculo,COUNT(*) AS cant_anuncios FROM Anuncio " +
							"GROUP BY patente_vehiculo) a " +
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

	
	/**
	 * Consulta que retorna el anuncio que estuvo más tiempo publicado
	 */
	public static ResultSet anuncioMayorPublicacion() {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"select id,MAX(DATEDIFF(fechaFin,fechaAlta)) as dias from " +
							"(select id,date (if (fechaBaja is null,NOW(),fechaBaja ) ) " +
							"as fechaFin,fechaAlta " +
							"FROM Anuncio ) s;";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
	/**
	 * Consulta que retorna un anuncio
	 */
	public static ResultSet getAnuncio(String id) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * from Anuncio WHERE id="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
	/**
	 * Consulta que retorna todas las consultas de un anuncio
	 */
	public static ResultSet consultasAnuncio(String id) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * from Consulta WHERE id_anuncio="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
	/**
	 * Consulta que retorna todas las respuestas de una consulta
	 */
	public static ResultSet respuestasConsulta(String id) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * from Respuesta WHERE id_consulta="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
}