package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import modelo.Grafo;
import modelo.GrafoDirigido;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void recorridoTopografico() {
		//Ejemplo 1
		Integer[][] matrizDeAdyacencia1 = {	{0, 1, 1, 0},
											{0, 0, 0, 1},
											{0, 0, 0, 1},
											{0, 0, 0, 0}};
		Grafo grafo = new GrafoDirigido(matrizDeAdyacencia1);
		
		ArrayList<Integer> resultadoEsperado = new ArrayList<Integer>();
		resultadoEsperado.add(0);
		resultadoEsperado.add(1);
		resultadoEsperado.add(2);
		resultadoEsperado.add(3);
		assertTrue(grafo.recorridoTopologico().equals(resultadoEsperado));
		
		
		//Ejemplo 2
		Integer[][] matrizDeAdyacencia2 = {	{0, 0, 1, 0, 0, 0, 1, 0},
											{1, 0, 0, 1, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 1, 0},
											{1, 0, 0, 0, 0, 0, 0, 1},
											{0, 1, 0, 0, 1, 0, 0, 0},
											{0, 0, 0, 0, 0, 0, 0, 0},
											{0, 0, 1, 0, 0, 0, 0, 0}};
		grafo = new GrafoDirigido(matrizDeAdyacencia2);
		
		resultadoEsperado = new ArrayList<Integer>();
		resultadoEsperado.add(5);
		resultadoEsperado.add(1);
		resultadoEsperado.add(3);
		resultadoEsperado.add(4);
		resultadoEsperado.add(0);
		resultadoEsperado.add(6);
		resultadoEsperado.add(7);
		resultadoEsperado.add(2);
		assertTrue(grafo.recorridoTopologico().equals(resultadoEsperado));
	}

}
