package packageProyecto1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Pasos.TypoPaso;

public class main {
	static Controlador controlador;
	static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		
		main console = new main();
		try {
            controlador = Controlador.CargarDatos();
            System.out.println("El archivo se cargo correctamente");
        } catch (Exception e) {
            controlador = new Controlador();
            System.out.println("Error al cargar los datos");
        }
		console.logIn();
		while (true) {
			console.menu();
		}
}
	
	public void logIn() {
		
		String username;
		String password;
		do {
			username = InputHelper.pedirString("username");
			password = InputHelper.pedirString("password");
		} while(!controlador.logIn(username, password));

	}

	public void agregarUsuario() {
		String username;
		String password;
		Grupo grupo = null;
		do {
			username = InputHelper.pedirString("username");
			password = InputHelper.pedirString("password");
			System.out.println("Escoger un grupo");
			try {
				grupo = controlador.escogerGrupo();
			} catch (Exception e) {
				System.out.println("There are no available groups");
				return;
			}
			grupo.getNombre();
		} while (controlador.crearUsuario(username, password, grupo));
	}
	
	public void crearProceso() {
		Proceso proceso = controlador.crearProceso(InputHelper.pedirString("nombre"));
		
		System.out.println("Do you want to insert tasks to this procedure 1:yes,0:no");
		boolean op = InputHelper.pedirOpcion();
		while (op) {
			Tarea tarea = null;
			try {
				tarea = controlador.crearTarea(proceso);
			} catch (Exception e) {
				System.out.println("There are no available groups");
				return;
			}
			
			System.out.println("Do you want to insert steps to this task 1:yes,0:no");
			boolean op2 = InputHelper.pedirOpcion();
			while (op2) {
				String pregunta = InputHelper.pedirString("instruccion");
				controlador.crearPaso(pregunta, tarea,escogerPaso());
				System.out.println("Add an aditional step? 1:Yes,0:no");
				op2 = InputHelper.pedirOpcion();
			}
			System.out.println("Add an aditional task? 1:Yes,0:no");
			op = InputHelper.pedirOpcion();
		}
	}
	
	public TypoPaso escogerPaso() {
		System.out.println("Pick the step type");
		System.out.println("0 - Multiple Answear");
		System.out.println("1 - Short Answear");
		int respuesta;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    respuesta = scan.nextInt();
		} while (respuesta < 0 || respuesta > 1 );
		switch(respuesta) {
		case 0: return TypoPaso.SelecMult;
		case 1: return TypoPaso.RespCorta;
		default: return null;
		}
	}
	
	public void crearGrupo() {
		String nombre = InputHelper.pedirString("new group");
		controlador.crearGrupo(nombre);
	}
	
	public void iniciarProceso() {
		try {
			controlador.iniciarProceso();
		} catch (Exception e) {
			System.out.println("There are no available proceses");
			return;
		}
	}
	
	public void actualizarProceso() {
		System.out.println("Delete from:1 o Add to:0");
		if(InputHelper.pedirOpcion()) {
			try {
				controlador.eliminarDeProceso();
			} catch (Exception e) {
				System.out.println("There are no available proceses");
				return;
			}
		} else {
			try {
				controlador.agregarAProceso();
			} catch (Exception e) {
				System.out.println("There are no available groups");
				return;
			}
		}
	}
	
	public void verProcesos() {
		controlador.verProcesos();
	}
	
	public void completarPaso() {
		verProcesos();
		int respuesta;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    respuesta = scan.nextInt();
		} while (respuesta < 0 || respuesta > controlador.getInstanceSize());
		controlador.completarPaso(respuesta);
	}
	
	public void menu() {
		System.out.println("0 - salir");
		System.out.println("1 - view available tasks");
		System.out.println("2 - start process");
		System.out.println("3 - complete task");
		System.out.println("4 - change user");
		if(controlador.isAdmin()) {
			System.out.println("5 - add user");
			System.out.println("6 - add group");
			System.out.println("7 - update process");
			System.out.println("8 - add process");
		}
		int max = (controlador.isAdmin())?8:4;
		int respuesta = max+1;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    respuesta = scan.nextInt();   
		} while (respuesta < 0 || respuesta > max);
		
		switch(respuesta) {
		case 0: try {
				Controlador.GuardarDatos(controlador);
				System.out.println("Los datos se guardaron correctamente");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Los datos no se guardaron correctamente");
				e.printStackTrace();
			} System.exit(0);
		case 1: verProcesos(); break;
		case 2: iniciarProceso(); break;
		case 3: completarPaso(); break;
		case 4: logIn(); break;
		case 5: agregarUsuario(); break;
		case 6: crearGrupo(); break;
		case 7: actualizarProceso(); break;
		case 8: crearProceso(); break;
		default: System.out.println("Respuesta invalida");
		}
		
	}
	
	
	
}
