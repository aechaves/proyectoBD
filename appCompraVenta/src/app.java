import java.sql.*;

public class app {
	
	public static void main (String[] args) {
		try {
			String driver = "org.gjt.mm.mysql.Driver";
	        String url = "jdbc:mysql://localhost/proyectoBD";
	        String username = "root";
	        String password = "";
	        
	        // Load database driver if not already loaded.
	        Class.forName(driver);
	        // Establish network connection to database.
	        Connection connection = DriverManager.getConnection(url, username, password);
	        
	        Statement statement = connection.createStatement();
	        String query = "SELECT * FROM Usuario";
	        ResultSet resultSet = statement.executeQuery(query);
	        
	        while(resultSet.next()) {
	            // Quarter

	            System.out.print(" DNI: " + resultSet.getString("dni"));
	            System.out.print(" Nombre: " + resultSet.getString("nombre"));
	            
	            System.out.print("\n   ");
	            System.out.print("\n   ");
	        }
	        
	        
		} catch(ClassNotFoundException cnfe) {
		      System.err.println("Error loading driver: " + cnfe);
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}

	}
}
