/**
 * Clase que implementa métodos para obtener los resultados de las consultas que el sistema requiera.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */

import java.sql.*;

public class Consulta {
	
	
	/**
	 * Consulta que retorna cada Vehículo con la cantidad de Anuncios que tiene.
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
	 * Consulta que retorna el Anuncio que estuvo más tiempo publicado.
	 */
	public static ResultSet anuncioMayorPublicacion() {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * FROM ( SELECT id,(DATEDIFF(fechaFin,fechaAlta)) AS dias FROM " +
							"(SELECT id,date (IF (fechaBaja IS NULL,NOW(),fechaBaja ) ) AS fechaFin,fechaAlta FROM Anuncio ) s" +
							") t ORDER BY dias DESC LIMIT 1;";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
	/**
	 * Consulta que retorna un Anuncio
	 * @param id es el id del Anuncio que se desea buscar.
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
	 * Consulta que retorna todas las Consultas de un Anuncio
	 * @param id es el id del Anuncio del cual se van a obtener las Consultas.
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
	 * Consulta que retorna todas las Respuestas de una Consulta
	 * @param id es el id de la Consulta de la cual se van a obtener las Respuestas.
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
	
	/**
	* Consulta que retorna todas las Marcas disponibles.
	*/
	public static ResultSet getMarcas() {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * FROM Marca;";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}

	/**
	* Consulta que retorna todas los Modelos disponibles asociados a una Marca.
	* @param id es el id de la Marca de la cual se van a obtener los Modelos.
	*/
	public static ResultSet getModelos(int id) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			String query = 	"SELECT * FROM Modelo WHERE id_marca="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
			return null;
		}
	}
	
}