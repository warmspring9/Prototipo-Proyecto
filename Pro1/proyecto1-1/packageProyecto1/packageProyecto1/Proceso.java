package packageProyecto1;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Proceso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	String nombre;
	LocalDateTime fecha;
	ArrayList<Tarea> tareas = new ArrayList<Tarea>();
	
	public Proceso(String nombre,int counter) {
		this.nombre = nombre;
		codigo = "Pr-" + counter;
		fecha = LocalDateTime.now();
	}
	
	public void agregarTarea(Tarea ingresable) {
		tareas.add(ingresable);
	}
	
	public void eliminarTarea(Tarea tarea) {
		tareas.remove(search(tarea.getCodigo()));
	}
	
	public int search(String codigo) {
		for(int i = 0;i > tareas.size();i++) {
			if(tareas.get(i).equals(codigo)) {
				return i;
			}
		}
		return -1;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public Tarea get(int e) {
		return tareas.get(e);
	}
	
	public int size() {
		return tareas.size();
	}
	public int count() {
		int total = 0;
		for(int i = 0; i < tareas.size(); i++) {
			total += tareas.get(i).count();
		}
		return total;
	}
	public String getNombre() {
		return nombre;
	}
}
