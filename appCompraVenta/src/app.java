/**
 * Clase principal que implementa la interfaz que permite acceder a las funcionalidades del sistema.
 * @authors Angelo Chávez - Eduardo Benmergui - Facundo Molina 
 */

import java.util.Scanner;

public class app {
	
	/**
	 * Menú principal
	 */
	public static void main (String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		char c = ' ';			
		char salir = 'S';
		
		while (c!=salir) {
			System.out.println("-------------------COMPRA-VENTA VEHICULOS-----------------------------");
			System.out.println("1 - Insertar Vehículo");
			System.out.println("2 - Insertar Anuncio");
			System.out.println("3 - Eliminar Anuncio");
			System.out.println("4 - Listar Anuncio");
			System.out.println("S - Salir");
			c = scanner.next().charAt(0);
			if (c=='1') {
				concluirInsercionVehiculo();
			}
			if (c=='2') {
				concluirInsercionAnuncio();
			}
			if (c=='3') {
				System.out.println("Ingrese el id: ");
				int id = scanner.nextInt();
				Eliminacion.eliminarAnuncio(id);
				System.out.println("Eliminado Correctamente \n");
			}
			if (c=='4') {
				System.out.println("Ingrese el id: ");
				int id = scanner.nextInt();
				Mostrar.anuncioConsultasRespuestas(""+id+"");
			}
			
		}
		
	}
	
	/**
	 * Menú secundario para concluir con la inserción de un Vehículo.
	 */
	public static void concluirInsercionVehiculo() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Ingrese la patente: ");
		String patente = s.next();
		System.out.print("\n");
	
		System.out.print("Ingrese el estado: ");
		String estado = s.next();
		System.out.print("\n");
	
		System.out.print("Ingrese los kilómetros: ");
		int km = s.nextInt();
		System.out.print("\n");
 
		System.out.print("Ingrese el año: ");
		String año = s.next();
		System.out.print("\n");
		
		System.out.print("Ingrese el DNI del dueño: ");
		int dni = s.nextInt();
		System.out.print("\n");
				
		System.out.print("Ingrese el ID de la marca deseada: ");
		Mostrar.marcas();
		int marca = s.nextInt();
		System.out.print("\n");

		System.out.print("Ingrese el ID del modelo deseado: ");
		Mostrar.modelos(marca);
		int modelo = s.nextInt();
		System.out.println("\n");


		char c = ' ';			
		char volver = 'V';
		
		while (c!=volver) {
			System.out.println("-------------------SELECCIONAR VEHICULO-----------------------------");
			System.out.println("1 - Auto");
			System.out.println("2 - Camión");
			System.out.println("3 - Moto");
			System.out.println("4 - PickUp");
			System.out.println("V - Volver");
			c = s.next().charAt(0);
			if (c=='1') {
				System.out.print("Ingrese la cantidad de asientos: ");
				int cantAsientos = s.nextInt();
				Insercion.insertarAuto(patente, estado, km, año, dni,modelo, cantAsientos);
				System.out.println("Insertado exitosamente \n");
				c='V';				
			}
			if (c=='2') {
				System.out.print("Ingrese la tara: ");
				double tara = s.nextDouble();
				Insercion.insertarCamion(patente, estado, km, año, dni,modelo, tara);
				System.out.println("Insertado exitosamente \n");
				c='V';
			}
			if (c=='3') {
				System.out.println("Ingrese 1 si es carenada o 0 si no lo es");
				int carenada  = s.nextInt();
				Insercion.insertarMoto(patente, estado, km, año, dni,modelo, carenada);
				System.out.println("Insertado exitosamente \n");
				c='V';
			}
			if (c=='4') {
				System.out.println("Ingrese Simple o Doble de acuerdo a la traccion: ");
				String traccion  = s.next();
				Insercion.insertarPickUp(patente, estado, km, año, dni,modelo,traccion);
				System.out.println("Insertado exitosamente \n");
				c='V';
			}
			
		}
		
	}
	
	/**
	 * Menú secundario para inserción de un Anuncio
	 */
	public static void concluirInsercionAnuncio() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Ingrese el titulo: ");
		String titulo = s.nextLine();
		System.out.print("\n");
	
		System.out.print("Ingrese una descripcion: ");
		String descripcion = s.nextLine();
		System.out.print("\n");
	
		System.out.print("Ingrese Venta o Permuta según el tipo: ");
		String tipo = s.nextLine();
		System.out.print("\n");
 
		System.out.print("Ingrese el monto: ");
		int monto = s.nextInt();
		System.out.print("\n");
		
		System.out.print("Ingrese el DNI del dueño: ");
		int dniUsuario = s.nextInt();
		System.out.print("\n");
		
		System.out.print("Ingrese la patente del vehiculo: ");
		String patenteVehiculo = s.next();
		System.out.print("\n");
		s.nextLine();
		
		Insercion.insertarAnuncio(titulo,descripcion, tipo, monto, dniUsuario, patenteVehiculo);
		System.out.println("Insertado exitosamente");
		
	}
}
