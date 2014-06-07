import java.sql.*;

public class app {
	
	public static void main (String[] args) {
		try {
			
	        Insercion.insertarMoto("DDD123","Bueno",4863,"2011",31299371,5,1);
	        
	        ResultSet resultSet = Consulta.vehiculosCantidadAnuncios();
	        
	        while(resultSet.next()) {
	           
	            System.out.print(" Patente: " + resultSet.getString("patente"));
	            System.out.print(" Marca: " + resultSet.getString("marca"));
	            System.out.print(" Modelo: " + resultSet.getString("modelo"));
	            System.out.print(" Cantidad Anuncios: " + resultSet.getString("cant_anuncios"));
	            
	            System.out.print("\n   ");
	            System.out.print("\n   ");
	        }
	        
	        
		} catch(SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}

	}
}
