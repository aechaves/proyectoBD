import java.sql.*;

public class Conexion {
	
	public Connection connection;
	
	public Conexion() {
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
	
	public void insertarVehiculo() {
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Usuario";
			ResultSet resultSet = statement.executeQuery(query);
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
}
