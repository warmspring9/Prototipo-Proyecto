package packageProyecto1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import Pasos.FactoryPasos;
import Pasos.Paso;
import Pasos.RespuestaCorta;
import Pasos.SeleccionMult;
import Pasos.TypoPaso;

import java.io.Serializable;

public class Controlador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Scanner scan = new Scanner(System.in);
	private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	private ArrayList<Instance> instancias = new ArrayList<Instance>();
	private ArrayList<Proceso> procesos = new ArrayList<Proceso>();
	private int consecutivoProceso = 0;
	private int consecutivoTarea = 0;
	private int consecutivoPaso = 0;
	private int consecutivoInstancia = 0;
	private String usuario;
	private Grupo grupo;
	private String Admin = "Admin";
	private String AdminPass = "4DM1N";
	private static Controlador instance = null;
	protected Controlador() {}
	
	public static Controlador getInstance() {
		
		if (instance == null) {
			instance = new Controlador();
		}
		return instance;
	}
	
	public static void GuardarDatos(Controlador data) throws FileNotFoundException, IOException{
        FileOutputStream file = new FileOutputStream("persistencia.dat");
        file.write(("").getBytes());
        ObjectOutputStream stream = new ObjectOutputStream(file);
        stream.writeObject(data);
        stream.close();
        
    }
    public static Controlador CargarDatos() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream("persistencia.dat");
        ObjectInputStream stream = new ObjectInputStream(file);
        Controlador data = (Controlador)stream.readObject();
        stream.close();
        return data;
    }


	public boolean logIn(String username, String password) {
		if(username.equals(Admin)) {
			if(password.equals(AdminPass)) {
				System.out.println("Welcome Admin");
				usuario = Admin;
				return true;
			}
			System.out.println("Incorrect Password");
			return false;
		}
		for (int i = 0; i < grupos.size();i++) {
			System.out.println(grupos.get(i).getNombre());
			System.out.println(grupos.get(i).indiceUsuario(username));
			if(grupos.get(i).buscarUsuario(username)) {
				System.out.println("entre");
				int pos = grupos.get(i).indiceUsuario(username);
				if (grupos.get(i).getPassword(pos).equals(password)) {
					System.out.println("Sign up succesful");
					System.out.println("Welcome " + username);
					usuario = grupos.get(i).getUsername(pos);
					grupo = grupos.get(i);
					return true;
				} else {
					System.out.println("Incorrect Password");
					return false;
				}
			}
		}
		System.out.println("Usuario no encontrado");
		return false;
	}
	
	public boolean crearUsuario(String username, String password, Grupo grupo) {
		
		for(int i = 0; i < grupos.size();i++) {
			if(grupos.get(i).buscarUsuario(username)) {
				System.out.println("El usuario ya existe en " + grupos.get(i).getNombre());
				return false;
			}
		}
		Usuario user = new Usuario(username, password);
		grupo.ingresarUsuario(user);
		return true;
	}
	
	public Grupo escogerGrupo() throws Exception {
		
		for(int i = 0; i < grupos.size();i++) {
			System.out.println(i + " - " + grupos.get(i).getNombre());
		}
		if(grupos.size() == 0) {
			throw new Exception ("There are no groups available");  
		}
		System.out.println("Escoja uno: ");
		int number;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    number = scan.nextInt();
		} while (number < 0 || number > grupos.size());
		System.out.println("Thank you!");
		return grupos.get(number);
	}
	
	public Proceso crearProceso(String nombre) {
		
		Proceso proceso = new Proceso(nombre,consecutivoProceso);
		consecutivoProceso++;
		procesos.add(proceso);
		return proceso;
	}
	
	public Tarea crearTarea(Proceso proceso) throws Exception {
		
		Tarea tarea = new Tarea(escogerGrupo(),consecutivoTarea);
		consecutivoTarea++;
		proceso.agregarTarea(tarea);
		return tarea;
	}	
	
	public Proceso escogerProceso() throws Exception {
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < procesos.size();i++) {
			System.out.println(i + " - " + procesos.get(i).getNombre());
		}
		if(procesos.size()==0) {
			throw new Exception ("There are no proceses available");
		}
		System.out.println("Escoja uno: ");
		int number;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    number = scan.nextInt();
		} while (number < 0 || number > procesos.size());
		System.out.println("Thank you!");
		 
		return procesos.get(number);
	}
	
	public void crearPaso(String pregunta, Tarea tarea, TypoPaso opcion) {
		tarea.agregarPaso(FactoryPasos.crearPaso(opcion, pregunta, tarea.getGrupo(), consecutivoPaso));
		consecutivoPaso++;
	}
	
	public String getUsuario() {
		
		return usuario;
	}
	
	public Grupo getGrupo() {
		
		return grupo;
	}
	
	public void iniciarProceso() throws Exception {
		Instance instancia = new Instance(escogerProceso(),consecutivoInstancia);
		consecutivoInstancia++;
		instancia.guardarInic(usuario);
		instancias.add(instancia);
		
	}
	
	public void crearGrupo(String nombre) {
		Grupo grupo = new Grupo(nombre);
		grupos.add(grupo);
	}
	
	public Tarea escogerTarea(Proceso proceso) throws Exception {
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < proceso.size(); i++) {
			System.out.println(i + " - " + proceso.get(i).getCodigo());
		}
		if(proceso.size()==0) {
			throw new Exception ("There are no tasks available");
		}
		System.out.println("Escoja uno: ");
		int number;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    number = scan.nextInt();
		} while (number < 0 || number > proceso.size());
		System.out.println("Thank you!");
		 
		return proceso.get(number);
	}
	
	public void agregarAProceso() throws Exception {
		Proceso proceso = escogerProceso();
		System.out.println("Add a task:1 or step:0");
		if(InputHelper.pedirOpcion()) {
			crearTarea(proceso);
		} else {
			String pregunta = InputHelper.pedirString("pregunta");
			Tarea tarea = escogerTarea(proceso);
			crearPaso(pregunta,tarea,escogerPaso());
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
	
	@Override
	public Controlador clone() {
		return instance;
	}
	public void eliminarTarea(Proceso proceso) throws Exception {
		proceso.eliminarTarea(escogerTarea(proceso));
	}
	
	public void eliminarPaso(Proceso proceso) throws Exception {
		Scanner scan = new Scanner(System.in);
		Tarea tarea = escogerTarea(proceso);
		for(int i = 0; i < tarea.count();i++ ) {
			System.out.println(i + " - " + tarea.get(i).getCodigo());
		}
		System.out.println("Escoja uno: ");
		int number;
		do {
		    System.out.println("Please enter a valid number!");
		    while (!scan.hasNextInt()) {
		        System.out.println("That's not a number!");
		        scan.next();
		    }
		    number = scan.nextInt();
		} while (number < 0 || number > tarea.count());
		System.out.println("Thank you!");
		 
		tarea.eliminarPaso(number);
	}
	
	public void eliminarDeProceso() throws Exception {
		Proceso proceso = escogerProceso();
		System.out.println("Delete a task:1 or step:0");
		if(InputHelper.pedirOpcion()) {
			eliminarTarea(proceso);
		} else {
			eliminarPaso(proceso);
		}
	}

	public void verProcesos() {
		for(int i = 0;i < instancias.size();i++) {
			if(!instancias.get(i).isCompletado()) {
				if(grupo == instancias.get(i).getGrupo() || usuario.equals(Admin)) {
					System.out.print(i + " - " + instancias.get(i).getCodigo()+" / ");
					instancias.get(i).getPaso().print();
				}
			}
		}
	}
	
	public void completarPaso(int i) {
		Paso paso = instancias.get(i).getPaso();
		paso.completar();
		
		instancias.get(i).incPaso();
		try {
			instancias.get(i).guardarLog(usuario,paso);
		} catch (Exception e) {
			System.out.println("ERROR  at saving log");
		};
	}
	public int getInstanceSize() {
		return instancias.size();
	}
	
	public boolean isAdmin() {
		return (usuario.equals(Admin))?true:false;
	}
}
