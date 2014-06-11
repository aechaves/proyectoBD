/**
 * Clase que implementa métodos para efectuar las elimaciones que se deseen sobre la base de datos del sistema.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */
import java.sql.*;

public class Eliminacion {
	
	/**
	 * Elimina un Anuncio.
	 * @param id es el id del Anuncio que se desea eliminar.
	 */
	public static void eliminarAnuncio(int id){
		try {
			Statement statement = Conexion.getConexion().createStatement();
		
			String query ="DELETE FROM Anuncio WHERE id="+id+";";
			statement.execute(query);
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	
}
