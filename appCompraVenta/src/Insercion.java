/**
 * Clase que implementa métodos para efectuar las inserciones que se deseen sobre la base de datos de el sistema.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */
import java.sql.*;

public class Insercion {
	

	/**
	 * Inserta los atributos de un Vehículo
	 */
	private static void insertarVehiculo(String patente,String estado,int km,String año,int dniUsuario, int idModelo) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			
			//Inserción de los atributos de Vehiculo
			String query ="INSERT INTO Vehiculo(patente,estado,km,año,dni_usuario,id_modelo) " +
					"VALUES ('"+patente+"','"+estado+"','"+km+"','"+año+""+
					"','"+dniUsuario+"','"+idModelo+"');";
			statement.execute(query);
		
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Inserta los atributos de Vehículo y de Auto en las tablas correspondientes.
	 */
	public static void insertarAuto(String patente,String estado,int km,String año,int dniUsuario, int idModelo,int cantAsientos) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			
			//Inserción de los atributos de Vehículo
			insertarVehiculo(patente,estado,km,año,dniUsuario,idModelo);
			
			//Inserción de los atributos de Auto
			String query = 	"INSERT INTO Auto(patente_vehiculo,cantAsientos) " +
							"VALUES ('"+patente+"','"+cantAsientos+"');";
			statement.execute(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Inserta los atributos de Vehículo y de Camión en las tablas correspondientes.
	 */
	public static void insertarCamion(String patente,String estado,int km,String año,int dniUsuario, int idModelo,double tara){
		try {
			Statement statement = Conexion.getConexion().createStatement();
			
			//Inserción de los atributos de Vehiculo
			insertarVehiculo(patente,estado,km,año,dniUsuario,idModelo);
			
			//Inserción de los atributos de Camión
			String query = 	"INSERT INTO Camion(patente_vehiculo,tara) " +
							"VALUES ('"+patente+"','"+tara+"');";
			statement.execute(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Inserta los atributos de Vehículo y de Moto en las tablas correspondientes.
	 */
	public static void insertarMoto(String patente,String estado,int km,String año,int dniUsuario, int idModelo,int carenada) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			
			//Inserción de los atributos de Vehiculo
			insertarVehiculo(patente,estado,km,año,dniUsuario,idModelo);
			
			//Inserción de los atributos de Moto
			String query = "INSERT INTO Moto(patente_vehiculo,carenada) " +
					"VALUES ('"+patente+"','"+carenada+"');";
			statement.execute(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Inserta los atributos de Vehículo y de PickUp en las tablas correspondientes.
	 */
	public static void insertPickUp(String patente,String estado,int km,String año,int dniUsuario, int idModelo,String traccion) {
		try {
			Statement statement = Conexion.getConexion().createStatement();
			
			//Inserción de los atributos de Vehiculo
			insertarVehiculo(patente,estado,km,año,dniUsuario,idModelo);
			
			//Inserción de los atributos de PickUp
			String query = "INSERT INTO PickUp(patente_vehiculo,traccion) " +
					"VALUES ('"+patente+"','"+traccion+"');";
			statement.execute(query);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.err.println("Error connecting: " + sqle);
		}
	}
	
	/**
	 * Inserción de un Anuncio
	 */
	public static void insertarAnuncio(String titulo,String descripcion,Date fechaAlta,Date fechaBaja,String tipo,int monto,int dniUsuario,String patenteVehiculo) {
			
	}

	
}
