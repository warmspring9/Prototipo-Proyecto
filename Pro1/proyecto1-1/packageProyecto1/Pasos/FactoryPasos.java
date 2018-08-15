package Pasos;

import packageProyecto1.Grupo;
import packageProyecto1.InputHelper;

public class FactoryPasos {
	public static Paso crearPaso(TypoPaso opcion, String pregunta, Grupo grupo, int consecutivo) {
		switch (opcion) {
		case SelecMult: SeleccionMult paso = new SeleccionMult(pregunta, grupo,consecutivo);
			System.out.println("Add option? 1:Yes,0:No");
			boolean op = InputHelper.pedirOpcion();
			while(op) {
				paso.agregarRespuestas(InputHelper.pedirString("option"));
				System.out.println("Add adicional option? yes:1 no:0");
				op = InputHelper.pedirOpcion();
			} 
			return paso;
		case RespCorta: return new RespuestaCorta(pregunta,grupo,consecutivo);
		case YesNo: return new YesNo(pregunta,grupo,consecutivo);
		default: return null;
		}
	}
}
