import java.sql.*;

public class Mostrar {
	
	public static void anuncioConsultasRespuestas(String id) {
		
		//Obtenemos anuncio
		try {
			ResultSet resultSet = Consulta.getAnuncio(id);
			while(resultSet.next()) {
	           
	            System.out.println(" Titulo: " + resultSet.getString("titulo"));
	            System.out.println(" Descripcion: " + resultSet.getString("descripcion"));
	            System.out.println(" Fecha Alta: " + resultSet.getString("fechaAlta"));
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
		
		//Obtenemos las consultas del anuncio
		try {
			ResultSet resultSet = Consulta.consultasAnuncio(id);
			while(resultSet.next()) {
	     		
	     		System.out.println("Consultas:");
	            System.out.println(" DNI Usuario: " + resultSet.getString("dni_usuario"));
	            System.out.println(" Descripcion: " + resultSet.getString("descripcion"));
	       
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

	public static void marcas() {
		//Obtenemos las marcas
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

	public static void modelos(int id_marca) {
		//Obtenemos los modelos
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
