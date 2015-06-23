package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import modelo.Grafo;
import modelo.GrafoDirigido;
import modelo.ResultadosDijkstra;

import org.junit.Test;

public class GrafoTest {
	
	private Integer[][] matrizDeAdyacencia1 = {	{0, 1, 1, 0},
												{0, 0, 0, 1},
												{0, 0, 0, 1},
												{0, 0, 0, 0}};
	
	private Integer[][] matrizDeAdyacencia2 = {	{0, 0, 1, 0, 0, 0, 1, 0},
												{1, 0, 0, 1, 0, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 1, 0},
												{1, 0, 0, 0, 0, 0, 0, 1},
												{0, 1, 0, 0, 1, 0, 0, 0},
												{0, 0, 0, 0, 0, 0, 0, 0},
												{0, 0, 1, 0, 0, 0, 0, 0}};
	
	private Integer[][] matrizDeAdyacencia3 ={	{0,   20,  7,   13,  0,   0  },
												{20,  0,   0,   0,   0,   0  },
												{7,   0,   0,   0,   4,   100},
												{13,  0,   0,   0,   4,   15 },
												{0,   0,   4,   4,   0,   0  },
												{20,  0,   100, 15,  0,   0  }};
	
	private Integer[][] matrizDeAdyacencia4 ={	{0, 0, 1, 6, 6},
												{0, 0, 6, 0, 0},
												{1, 6, 0, 2, 5},
												{6, 0, 2, 0, 3},
												{6, 0, 5, 3, 0}};
	
	private Grafo grafo1 = new GrafoDirigido(matrizDeAdyacencia1);
	private Grafo grafo2 = new GrafoDirigido(matrizDeAdyacencia2);
	private Grafo grafo3 = new GrafoDirigido(matrizDeAdyacencia3);
	private Grafo grafo4 = new GrafoDirigido(matrizDeAdyacencia4);
	
	@Test
	public void recorridoTopografico() {
		//Ejemplo 1
		ArrayList<Integer> resultadoEsperado = new ArrayList<Integer>();
		resultadoEsperado.add(0);
		resultadoEsperado.add(1);
		resultadoEsperado.add(2);
		resultadoEsperado.add(3);
		assertTrue(grafo1.recorridoTopologico().equals(resultadoEsperado));
		
		
		//Ejemplo 2
		resultadoEsperado = new ArrayList<Integer>();
		resultadoEsperado.add(5);
		resultadoEsperado.add(1);
		resultadoEsperado.add(3);
		resultadoEsperado.add(4);
		resultadoEsperado.add(0);
		resultadoEsperado.add(6);
		resultadoEsperado.add(7);
		resultadoEsperado.add(2);
		assertTrue(grafo2.recorridoTopologico().equals(resultadoEsperado));
	}
	
	
	@Test
	public void algoritmoDijkstra() {
		//Ejemplo 3
		ResultadosDijkstra resultadosDijkstra = grafo3.algoritmoDeDijkstra(0);
		
		ArrayList<Integer> distanciasANodoOrigenEsperadas = new ArrayList<Integer>();
		distanciasANodoOrigenEsperadas.add(0);
		distanciasANodoOrigenEsperadas.add(20);
		distanciasANodoOrigenEsperadas.add(7);
		distanciasANodoOrigenEsperadas.add(13);
		distanciasANodoOrigenEsperadas.add(11);
		distanciasANodoOrigenEsperadas.add(28);
		assertTrue(resultadosDijkstra.getDistanciasAlNodoOrigen().equals(distanciasANodoOrigenEsperadas));
		
		assertTrue(resultadosDijkstra.getNodoPrevio(0) == null);
		assertTrue(resultadosDijkstra.getNodoPrevio(1).equals(0));
		assertTrue(resultadosDijkstra.getNodoPrevio(2).equals(0));
		assertTrue(resultadosDijkstra.getNodoPrevio(3).equals(0));
		assertTrue(resultadosDijkstra.getNodoPrevio(4).equals(2));
		assertTrue(resultadosDijkstra.getNodoPrevio(5).equals(3));
		
		ArrayList<Integer> ordenDeSeleccionEsperado = new ArrayList<Integer>();
		ordenDeSeleccionEsperado.add(2);
		ordenDeSeleccionEsperado.add(4);
		ordenDeSeleccionEsperado.add(3);
		ordenDeSeleccionEsperado.add(1);
		ordenDeSeleccionEsperado.add(5);
		
		assertTrue(resultadosDijkstra.getOrdenDeSeleccion().equals(ordenDeSeleccionEsperado));
		
		
		//Ejemplo 4
		resultadosDijkstra = grafo4.algoritmoDeDijkstra(0);
		
		distanciasANodoOrigenEsperadas = new ArrayList<Integer>();
		distanciasANodoOrigenEsperadas.add(0);
		distanciasANodoOrigenEsperadas.add(7);
		distanciasANodoOrigenEsperadas.add(1);
		distanciasANodoOrigenEsperadas.add(3);
		distanciasANodoOrigenEsperadas.add(6);
		
		assertTrue(resultadosDijkstra.getDistanciasAlNodoOrigen().equals(distanciasANodoOrigenEsperadas));
		
		assertTrue(resultadosDijkstra.getNodoPrevio(0) == null);
		assertTrue(resultadosDijkstra.getNodoPrevio(1).equals(2));
		assertTrue(resultadosDijkstra.getNodoPrevio(2).equals(0));
		assertTrue(resultadosDijkstra.getNodoPrevio(3).equals(2));
		assertTrue(resultadosDijkstra.getNodoPrevio(4).equals(0));
		
		ordenDeSeleccionEsperado = new ArrayList<Integer>();
		ordenDeSeleccionEsperado.add(2);
		ordenDeSeleccionEsperado.add(3);
		ordenDeSeleccionEsperado.add(4);
		ordenDeSeleccionEsperado.add(1);
		
		assertTrue(resultadosDijkstra.getOrdenDeSeleccion().equals(ordenDeSeleccionEsperado));
	}
		
	
	@Test
	public void listaDeAdyacencia(){
		String listaDeAdyacenciaEsperada = "";
		listaDeAdyacenciaEsperada += "A: B, C";
		listaDeAdyacenciaEsperada += "\nB: D";
		listaDeAdyacenciaEsperada += "\nC: D";
		listaDeAdyacenciaEsperada += "\nD";
		assertTrue(grafo1.toTablaListaDeAdyacencia().equals(listaDeAdyacenciaEsperada));
	}

}
