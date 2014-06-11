/**
 * Clase que implementa una única instancia de conexión a la base de datos del sistema.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */

import java.sql.*;

public class Conexion {
	
	static Connection connection = null;
	
	/**
	 * Constructor
	 */
	private Conexion() {
		try {
			String driver = "org.gjt.mm.mysql.Driver";
		
			String url = "jdbc:mysql://localhost/proyectoBD";
			String username = "root";
			String password = "";
        
			// Load database driver if not already loaded.
			Class.forName(driver);
			// Establish network connection to database.
			connection = DriverManager.getConnection(url, username, password);
		} catch(ClassNotFoundException cnfe) {
		      System.err.println("Error loading driver: " + cnfe);
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Retorna la instancia de la Conexion.
	 */
	public static Connection getConexion() {
		if (connection==null) {
			new Conexion();
		}
		return connection;
	}
	
	
}
