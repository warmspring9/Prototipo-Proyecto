package packageProyecto1;

import java.io.Serializable;
import java.util.ArrayList;

public class Tarea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	ArrayList<Paso> Pasos = new ArrayList<Paso>();
	Grupo group;
	
	public Tarea(Grupo group,int counter) {
		this.group = group;
		codigo = "T-" + counter;
	}
	
	public void agregarPaso(Paso ingresado) {
		Pasos.add(ingresado);
	}
	
	public String getCodigo() {
		return codigo;
	}
	public Paso get(int e) {
		return Pasos.get(e);
	}
	
	public int count() {
		return Pasos.size();
	}
	
	public void eliminarPaso(int i) {
		Pasos.remove(i);
	}
	
	public int buscar(String codigo) {
		for(int i = 0; i < Pasos.size();i++) {
			if(Pasos.get(i).getCodigo().equals(codigo)) {
				return i;
			}
		}
		return -1;
	}
	public Grupo getGrupo() {
		return group;
	}
}
