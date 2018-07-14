package packageProyecto1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;

import Pasos.Paso;

public class Instance implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Proceso proceso;
	String codigo;
	int tareas = 0;
	int pasos = 0;
	boolean completado = false;
	
	public Instance(Proceso proceso,int counter) {
		this.proceso = proceso;
		codigo = "Ins-" + counter; 
	}
	public Proceso getProceso() {
		return proceso;
	}
	public String getCodigo() {
		return codigo;
	}
	public int count() {
		return proceso.count();
	}
	public boolean isCompletado() {
		return completado;
	}
	public Grupo getGrupo() {
		return proceso.get(tareas).getGrupo();
	}
	public Paso getPaso() {
		return proceso.get(tareas).get(pasos);
	}
	public void incPaso() {
		if(pasos >= proceso.get(tareas).count()) {
			pasos = 0;
			if(tareas >= proceso.size()) {
				completado = true;
				tareas = 0;
			} else {
				tareas++;
				if(proceso.get(tareas).count() == 0) {
					incPaso();
				}
			}
		}else {
			pasos++;
		}
	}
	
	public void guardarInic(String username) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true));
		Date date = new Date();
		writer.println(codigo + " " + username + " " +"Iniciado"+" "+ date.toString() );
		writer.close();
	}
	public void guardarLog(String username, Paso paso) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true));
		Date date = new Date();
		writer.println(codigo + " " + username + " " +paso.getCodigo()+" "+ date.toString() );
		writer.close();
	}
}
