package Pasos;

import java.io.Serializable;
import java.util.Scanner;

import packageProyecto1.Grupo;

public class RespuestaCorta extends Paso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String respuesta;
	
	public RespuestaCorta(String pregunta, Grupo grupo,int counter) {
		super(pregunta,grupo,1,counter);
	}
	public void print() {
		System.out.println(super.getPregunta());
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
