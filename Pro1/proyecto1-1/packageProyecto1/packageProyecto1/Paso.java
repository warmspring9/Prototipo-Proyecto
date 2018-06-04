package packageProyecto1;

import java.io.Serializable;

public class Paso implements Serializable{
	String codigo;
	public static int counter = 0;
	String pregunta;
	Grupo grupo;
	int tipo;
	
	public Paso(String pregunta, Grupo grupo,int tipo) {
		this.pregunta = pregunta;
		this.grupo = grupo;
		codigo = "P-" + counter;
		counter++;
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
	
	public void print() {
		System.out.println(pregunta);
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public int getTipo() {
		return tipo;
	}

	public void completar() {
		// TODO Auto-generated method stub
		
	}
}
