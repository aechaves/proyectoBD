import java.sql.*;
import java.util.Scanner;

public class app {
	
	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		char c = ' ';			
		char salir = 'S';
		
		while (c!=salir) {
			System.out.println("-------------------COMPRA-VENTA VEHICULOS-----------------------------");
			System.out.println("1 - Insertar Vehículo");
			System.out.println("2 - Eliminar Anuncio");
			System.out.println("3 - Listar Anuncios");
			System.out.println("S - Salir");
			c = scanner.next().charAt(0);
			if (c=='1') {
				seleccionarTipoVehiculo();
			}
			if (c=='2') {
				
			}
			if (c=='3') {
				System.out.println("Ingrese el id: ");
				int id = scanner.nextInt();
				Mostrar.anuncioConsultasRespuestas(""+id+"");
			}
		}
		
		
		
		
		/*try {
			
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
		}*/
	}
	
	public static void seleccionarTipoVehiculo() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Ingrese la patente: ");
		String patente = s.next();
		System.out.print("\n");
		
		System.out.println("Ingrese el estado");
		String estado = s.next();
		System.out.print("\n");
		
		System.out.println("Ingrese los kilómetros");
		int km = s.nextInt();
		System.out.print("\n");
		
		System.out.println("Ingrese el año");
		String año = s.next();
		System.out.print("\n");
		
		System.out.println("Ingrese el DNI del dueño");
		int dni = s.nextInt();
		System.out.print("\n");
				
		System.out.println("Ingrese el Modelo");
		int modelo = s.nextInt();
		System.out.print("\n");
		
		char c = ' ';			
		char volver = 'V';
		
		while (c!=volver) {
			System.out.println("-------------------SELECCIONAR VEHICULO-----------------------------");
			System.out.println("1 - Auto");
			System.out.println("2 - Camión");
			System.out.println("3 - Moto");
			c = s.next().charAt(0);
			if (c=='1') {
				System.out.println("Ingrese la cantidad de asientos: ");
				int cantAsientos = s.nextInt();
				Insercion.insertarAuto(patente, estado, km, año, dni,modelo, cantAsientos);
				System.out.println("Insertado exitosamente");
			}
			if (c=='2') {
				System.out.println("Ingrese la tara: ");
				double tara = s.nextDouble();
				Insercion.insertarCamion(patente, estado, km, año, dni,modelo, tara);
				System.out.println("Insertado exitosamente");
			}
			if (c=='3') {
				System.out.println("Ingrese 1 si es carenada o 0 si no lo es");
				int carenada  = s.nextInt();
				Insercion.insertarMoto(patente, estado, km, año, dni,modelo, carenada);
				System.out.println("Insertado exitosamente");
			}
		}
	}
}
