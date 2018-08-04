package packageProyecto1;

import java.util.Scanner;

public class InputHelper {
	
	public static String pedirString(String campo) {
		Scanner scan = new Scanner(System.in);
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
