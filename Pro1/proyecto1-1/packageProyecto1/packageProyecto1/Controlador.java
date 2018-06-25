package packageProyecto1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;

public class Controlador implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Grupo> grupos = new ArrayList<Grupo>();
	ArrayList<Instance> instancias = new ArrayList<Instance>();
	ArrayList<Proceso> procesos = new ArrayList<Proceso>();
	private static Controlador instance = null;
	String usuario;
	Grupo grupo;
	private String Admin = "Admin";
	private String AdminPass = "4DM1N";
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
		Scanner scan = new Scanner(System.in);
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
		scan.close();
		return grupos.get(number);
	}
	
	public Proceso crearProceso(String nombre) {
		
		Proceso proceso = new Proceso(nombre);
		procesos.add(proceso);
		return proceso;
	}
	
	public Tarea crearTarea(Proceso proceso) throws Exception {
		
		Tarea tarea = new Tarea(escogerGrupo());
		proceso.agregarTarea(tarea);
		return tarea;
	}
	
	public String pedirString(String campo) {
		Scanner scan = new Scanner(System.in);
		String respuestas;
		System.out.println("Insert " + campo);
		while (!scan.hasNext()) {
			System.out.println("Please enter a valid " + campo);
			scan.next();
		}
		respuestas = scan.next();
		scan.close();
		return respuestas;
	}
	
	public boolean pedirOpcion() {
		Scanner scan = new Scanner(System.in);
		int respuesta;
		while (!scan.hasNextInt()) {
			System.out.println("Please choose 1 or 0");
			scan.next();
		}
		respuesta = scan.nextInt();
		scan.close();
		return (respuesta == 1)?true:false;
	}
	
	public Proceso escogerProceso() throws Exception {
		Scanner scan = new Scanner(System.in);
		for(int i = 0; i < procesos.size();i++) {
			System.out.println(i + " - " + procesos.get(i).getCodigo());
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
		scan.close();
		return procesos.get(number);
	}
	
	public void crearPaso(String pregunta, Tarea tarea, boolean opcion) {
		if(opcion) {
			SeleccionMult paso = new SeleccionMult(pregunta, tarea.getGrupo());
			System.out.println("Add option? 1:Yes,0:No");
			boolean op = pedirOpcion();
			while(op) {
				paso.agregarRespuestas(pedirString("option"));
				System.out.println("Add adicional option? yes:1 no:0");
				op = pedirOpcion();
				
			}
			tarea.agregarPaso(paso);
		} else {
			RespuestaCorta paso = new RespuestaCorta(pregunta,tarea.getGrupo());
			tarea.agregarPaso(paso);
		}
	}
	
	public String getUsuario() {
		
		return usuario;
	}
	
	public Grupo getGrupo() {
		
		return grupo;
	}
	
	public void iniciarProceso() throws Exception {
		Instance instancia = new Instance(escogerProceso());
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
		scan.close();
		return proceso.get(number);
	}
	
	public void agregarAProceso() throws Exception {
		Proceso proceso = escogerProceso();
		System.out.println("Add a task:1 or step:0");
		if(pedirOpcion()) {
			crearTarea(proceso);
		} else {
			String pregunta = pedirString("pregunta");
			Tarea tarea = escogerTarea(proceso);
			System.out.println("Short Answear: 0 or Multiple Seleccion:1");
			crearPaso(pregunta,tarea,pedirOpcion());
		}
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
		scan.close();
		tarea.eliminarPaso(number);
	}
	
	public void eliminarDeProceso() throws Exception {
		Proceso proceso = escogerProceso();
		System.out.println("Delete a task:1 or step:0");
		if(pedirOpcion()) {
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