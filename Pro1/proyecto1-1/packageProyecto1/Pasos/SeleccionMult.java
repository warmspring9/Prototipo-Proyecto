package Pasos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import packageProyecto1.Grupo;

public class SeleccionMult extends Paso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> respuestas;
	int seleccion;
	
	public SeleccionMult(String pregunta, Grupo grupo,int counter) {
		super(pregunta, grupo,0,counter);
		respuestas = new ArrayList<String>();
	}
	public void agregarRespuestas(String respuesta) {
		respuestas.add(respuesta);
	}
	public void seleccionarForm(int respuesta) {
		seleccion = respuesta;
	}
	public void print() {
		System.out.println(super.getPregunta());
		for (int i = 0; i < respuestas.size(); i++) {
			System.out.print(" " + i + " ");
			System.out.println(respuestas.get(i));
		}
		System.out.println("Pick one");
	}
	@Override
	public void completar() {
		Scanner scan = new Scanner(System.in);
		for(int i = 0;i<respuestas.size();i++) {
			System.out.println(i + " - " + respuestas.get(i));
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
		} while (number < 0 || number > respuestas.size());
		seleccion = number;
	}
}
