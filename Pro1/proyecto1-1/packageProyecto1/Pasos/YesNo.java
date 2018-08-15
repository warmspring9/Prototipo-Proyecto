package Pasos;

import java.io.Serializable;
import java.util.Scanner;

import packageProyecto1.Grupo;

public class YesNo extends Paso implements Serializable {

	Boolean respuesta;
	
	public YesNo(String pregunta, Grupo grupo, int counter) {
		super(pregunta, grupo, 3, counter);
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void print() {
		System.out.println(super.getPregunta());
		System.out.println("Yes/No?");
	}

	@Override
	public String completar() {
		Scanner scan = new Scanner(System.in);
		print();
		System.out.println("Ingrese su respuestas");
		respuesta = (scan.next()=="Yes")?true:false;
		return (respuesta)?"Yes":"No";
	}
	
}
