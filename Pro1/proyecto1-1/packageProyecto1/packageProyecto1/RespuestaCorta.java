package packageProyecto1;

import java.io.Serializable;
import java.util.Scanner;

public class RespuestaCorta extends Paso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String respuesta;
	
	public RespuestaCorta(String pregunta, Grupo grupo) {
		super(pregunta,grupo,1);
	}
	public void print() {
		super.print();
		System.out.println("________");
	}
	public void llenarForm(String respuesta) {
		this.respuesta = respuesta;
	}
	@Override 
	public void completar() {
		Scanner scan = new Scanner(System.in);
		print();
		System.out.println("Ingrese su respuestas");
		respuesta = scan.next();
	}
}
