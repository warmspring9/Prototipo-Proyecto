package Pasos;

import java.util.Scanner;

import packageProyecto1.Grupo;

public class FactoryPasos {
	private static Scanner scan;
	public static Paso crearPaso(TypoPaso opcion, String pregunta, Grupo grupo, int consecutivo) {
		switch (opcion) {
		case SelecMult: SeleccionMult paso = new SeleccionMult(pregunta, grupo,consecutivo);
			System.out.println("Add option? 1:Yes,0:No");
			boolean op = pedirOpcion();
			while(op) {
				paso.agregarRespuestas(pedirString("option"));
				System.out.println("Add adicional option? yes:1 no:0");
				op = pedirOpcion();
			}
			return paso;
		case RespCorta: return new RespuestaCorta(pregunta,grupo,consecutivo);
		default: return null;
		}
	}
	public static String pedirString(String campo) {
		String respuestas;
		System.out.println("Insert " + campo);
		while (!scan.hasNext()) {
			System.out.println("Please enter a valid " + campo);
			scan.next();
		}
		respuestas = scan.next();
		 
		return respuestas;
	}
	
	public static boolean pedirOpcion() {
		Scanner scan = new Scanner(System.in);
		int respuesta;
		while (!scan.hasNextInt()) {
			System.out.println("Please choose 1 or 0");
			scan.next();
		}
		respuesta = scan.nextInt();
		 
		return (respuesta == 1)?true:false;
	}
}
