package Pasos;

import java.io.Serializable;

import packageProyecto1.Grupo;

public abstract class Paso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String codigo;
	String pregunta;
	Grupo grupo;
	int tipo;
	
	public Paso(String pregunta, Grupo grupo,int tipo,int counter) {
		this.pregunta = pregunta;
		this.grupo = grupo;
		codigo = "P-" + counter;
		}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getPregunta() {
		return pregunta;
	}
	
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	
	public abstract void print();
	
	public Grupo getGrupo() {
		return grupo;
	}
	public int getTipo() {
		return tipo;
	}

	public abstract String completar();
}
