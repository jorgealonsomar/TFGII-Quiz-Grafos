package test;

import static org.junit.Assert.assertEquals;
import modelo.GrafoDirigido;
import modelo.pregunta.Pregunta;
import modelo.pregunta.PreguntaDeAnchura;

import org.junit.Test;

import util.Idioma;

public class PreguntaTest {

	@Test
	public void preguntaDeAnchura() {
		Integer nNodos = 5;
		Double porcentajeDeArcos = 1.00;
		boolean esDirigido = true;
		
		Pregunta preguntaDeAnchura = new PreguntaDeAnchura(nNodos, porcentajeDeArcos, esDirigido);
		
		assertEquals(preguntaDeAnchura.getGrafo(), new GrafoDirigido(nNodos, porcentajeDeArcos));
		
		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ESP), "Pregunta de Recorrido en Anchura");
		assertEquals(preguntaDeAnchura.getTitulo(Idioma.ENG), "Breadth-First Search Question.");
		
		assertEquals(preguntaDeAnchura.getEnunciado(Idioma.ESP), "Partiendo de A, recorre el grafo en anchura. Los nodos se deben escoger en orden alfabético. ");
	}

}
