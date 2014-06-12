/**
 * Clase que implementa métodos como interfaces para mostrar los resultados de las consultas que el sistema requiera.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */

import java.sql.*;

public class Mostrar {
	
	/**
	 * Muestra un Anuncio con sus Consultas y las Respuestas asociadas a cada consulta.
	 * @param id es el id del Anuncio que se va a mostrar.
	 */
	public static void anuncioConsultasRespuestas(String id) {
		
		System.out.println("------------------------------ANUNCIO "+id+"-------------------------------");
		//Obtenemos el Anuncio y mostramos sus datos
		try {
			ResultSet resultSet = Consulta.getAnuncio(id);
			while(resultSet.next()) {
	           
	            System.out.println(" Titulo: " + resultSet.getString("titulo"));
	            System.out.println(" Descripcion: " + resultSet.getString("descripcion"));
	            System.out.println(" Fecha Alta: " + resultSet.getString("fechaAlta"));
	            String fechaBaja = resultSet.getString("fechaBaja");
	            if (fechaBaja != null) {
	            	System.out.println(" Fecha Baja: " + fechaBaja);
	            }
	            System.out.println(" Tipo: " + resultSet.getString("tipo"));
	            System.out.println(" Monto: " + resultSet.getString("monto"));
	            System.out.println(" DNI Dueño: " + resultSet.getString("dni_usuario"));
	            System.out.println(" Patente Vehiculo: " + resultSet.getString("patente_vehiculo"));
	            System.out.print("\n");
	            System.out.print("\n");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
		
		//Obtenemos y mostramos las Consultas del Anuncio.
		try {
			ResultSet resultSet = Consulta.consultasAnuncio(id);
			while(resultSet.next()) {
	     		
	     		System.out.println("Consultas:");
	            System.out.println(" DNI Usuario: " + resultSet.getString("dni_usuario"));
	            System.out.println(" Descripcion: " + resultSet.getString("descripcion"));
	       
	       		//Para la Consulta actual obtenemos y mostramos las Respuestas asociadas a ella.
	            try {
	            	ResultSet respuestas = Consulta.respuestasConsulta(resultSet.getString("id"));
	            	while (respuestas.next()) {
	            		System.out.println(" Respuesta: " + respuestas.getString("descripcion"));
	            	}
	            } catch(SQLException sqle) {
	            	sqle.printStackTrace();
	            	System.err.println("Error connecting: " + sqle);
	            }
	            System.out.print("\n");
	            System.out.print("\n");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}

	/**
	 * Muestra la lista de las Marcas de Vehiculos disponibles.
	 */
	public static void marcas() {
		System.out.println("");
		try {
			ResultSet resultSet = Consulta.getMarcas();
			while(resultSet.next()) {
	           
	            System.out.println(" Marca: " + resultSet.getString("nombre"));
	            System.out.println(" ID: " + resultSet.getString("id"));
	            System.out.print("\n");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}

	}

	/**
	 * Muestra la lista de Modelos disponibles asociados a una Marca
	 * @param id_marca es el id de la marca de la cual se van a obtener los Modelos
	 */
	public static void modelos(int id_marca) {
		System.out.println("");
		try {
			ResultSet resultSet = Consulta.getModelos(id_marca);
			while(resultSet.next()) {
	           
	            System.out.println(" Modelo: " + resultSet.getString("nombre"));
	            System.out.println(" ID: " + resultSet.getString("id"));
	            System.out.print("\n");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}

	}
	
}
