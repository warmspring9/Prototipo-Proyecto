package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Pasos.Paso;
import Pasos.RespuestaCorta;
import Pasos.SeleccionMult;
import packageProyecto1.Controlador;
import packageProyecto1.Grupo;
import packageProyecto1.Proceso;
import packageProyecto1.Tarea;
import packageProyecto1.Usuario;

class Tests {

	Paso p,p2;
	Grupo g;
	Controlador cont;
	Tarea t,t2;
	Proceso po;
	
	@Test
	void testGrupoBuscar() {
		g = new Grupo("Contaduria");
		Usuario sujeto = new Usuario("Persona","hola");
		
		g.ingresarUsuario(sujeto);
		Usuario sujeto2 = new Usuario("Persona2","hola");
		g.ingresarUsuario(sujeto2);
		assertEquals(0,g.indiceUsuario("Persona"));
	}
	
	@Test
	void testGrupoSize() {
		g = new Grupo("Contaduria");
		Usuario sujeto = new Usuario("Persona","hola");
		
		g.ingresarUsuario(sujeto);
		Usuario sujeto2 = new Usuario("Persona2","hola");
		g.ingresarUsuario(sujeto2);
		assertEquals(2,g.getSize());
	}
	
	@Test
	void testTareaBuscar() {
		p = new RespuestaCorta("Porque no me ama?", g, 0);
		p2 = new SeleccionMult("Porque no me compila?",g,1);
		
		t = new Tarea(g,0);
		t.agregarPaso(p);
		t.agregarPaso(p2);
		assertEquals(0,t.buscar("P-0"));
	}
	
	@Test
	void testTareaSize() {
		p = new RespuestaCorta("Porque no me ama?", g, 0);
		p2 = new SeleccionMult("Porque no me compila?",g,1);
		
		t = new Tarea(g,0);
		t.agregarPaso(p);
		t.agregarPaso(p2);
		assertEquals(2,t.count());
	}
	@Test
	void testProcesoBuscar() {
		t = new Tarea(g,0);
		t2 = new Tarea(g,1);
		po = new Proceso("Cosa bien hecha", 0);
		po.agregarTarea(t);
		po.agregarTarea(t2);
		assertEquals(1, po.search("T-1"));
	}
	@Test
	void testProcesoSize() {
		t = new Tarea(g,0);
		t2 = new Tarea(g,1);
		po = new Proceso("Cosa bien hecha", 0);
		po.agregarTarea(t);
		po.agregarTarea(t2);
		assertEquals(2, po.size());
	}

}
