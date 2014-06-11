import java.sql.*;

public class Eliminacion {
	
	/**
	 * Elimina un anuncio.
	 * @param id referencia al anuncio que se desea eliminar.
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
