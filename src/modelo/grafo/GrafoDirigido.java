package modelo.grafo;

import java.util.ArrayList;
import java.util.Random;

import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Grafo cuyos arcos son dirigidos.
 * @author Jorge Alonso Márquez
 */
public class GrafoDirigido extends Grafo {
	
	/**
	 * Constructor de la clase. Construye un nuevo grafo dirigido al azar a partir de los parámetros fijados.
	 * @param nNodos
	 *            Número de nodos del grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos con los que se genera el grafo.
	 * @param esPonderado
	 *            Si el grafo es o no ponderado.
	 * @param randomGenerator
	 *            Generador de números aleatorios.
	 * @param grafoSinCiclos
	 *            Si el grafo debe no contener ciclos.
	 */
	public GrafoDirigido(Integer nNodos, Double porcentajeArcos, boolean esPonderado, Random randomGenerator,
			boolean grafoSinCiclos) {
		super(nNodos, porcentajeArcos, esPonderado, randomGenerator, grafoSinCiclos);
	}
	
	
	/**
	 * Constructor de la clase. Crea el grafo a partir de una matriz de adyacencia dada.
	 * @param matrizDeAdyacencia
	 *            Matriz de adyacencia del grafo.
	 */
	public GrafoDirigido(Integer[][] matrizDeAdyacencia) {
		super(matrizDeAdyacencia);
	}

	/**
	 * Añade un nuevo arco a la matriz de adyacencia. 
	 * El sentido del arco se decide de forma aleatoria.
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 * @param valorArco
	 *            Valor que tendrá el arco.
	 */
	@Override
	protected void addArco(Integer nodo1, Integer nodo2) {
		getMatrizDeAdyacencia()[nodo1][nodo2] = generarValorDeArco();
	}
	
	
	/**
	 * Añade de forma aleatoria nuevos arcos dirigidos al grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de nuevos arcos.
	 */
	protected void construirArcosExtra(Double porcentajeDeArcos) {

		// Almacenar en una lista los arcos no existentes
		ArrayList<Arco> arcosNoExistentes = new ArrayList<Arco>();
		for (int i = 0; i < getMatrizDeAdyacencia().length; i++) {
			for (int j = 0; j < getMatrizDeAdyacencia()[i].length; j++) {
				if (getMatrizDeAdyacencia()[i][j].equals(0)) {
					arcosNoExistentes.add(new Arco(i, j));
				}
			}
		}

		anadirArcosAlAzarDeLaLista(arcosNoExistentes, porcentajeDeArcos);
	}
	
	
	/**
	 * Devuelve la cadena que representa a este grafo, consistente en una representación del
	 * mismo en forma de matriz de adyacencia.
	 */
	@Override
	public String toString() {
		return (super.toString() + "\n(directed graph)");
	}
	
	
	/**
	 * Realiza una clasificación topológica del grafo.
	 * @return Lista con los nodos ordenados tras la clasificación.
	 */
	public ArrayList<Integer> clasificacionTopologica() {
		ArrayList<Integer> nodosClasificados = new ArrayList<Integer>();
		
		//Crear una copia auxiliar de la matriz de adyacencia que poder modificar
		Integer[][] matrizAdyacenciaAux = clonarMatrizDeAdyacencia();
		
		boolean seguirRecorriendo = true;
		while (seguirRecorriendo){
			
			//Encontrar el primer nodo sin arcos de entrada:
			Integer nodoSinArcosDeEntrada = null;
			//Por cada columna (recorridas en orden numérico):
			for(int j = 0; j < matrizAdyacenciaAux.length; j++){
				//Si ese nodo ya se ha añadido al recorrido, se ignora
				if(!nodosClasificados.contains(j)){
					boolean todoACero = true;
					//Se comprueba si cada elemento de esa columna es cero
					for(int i = 0; i < matrizAdyacenciaAux.length; i++){
						if(matrizAdyacenciaAux[i][j] != 0){
							todoACero = false;
						}
					}
					//Si lo es:
					if(todoACero){
						//Ese nodo es el primer nodo sin arcos de entrada
						nodoSinArcosDeEntrada = j;
						break;
					}
				}
			}
			
			//Si no queda ningún nodo sin arcos de entrada, detenemos el algoritmo
			if(nodoSinArcosDeEntrada == null){
				seguirRecorriendo = false;
			} else {
				//Eliminamos todos los enlaces que salen de ese nodo 
				//(Ponemos a cero todos los elementos de su fila)
				for(int j = 0; j < matrizAdyacenciaAux.length; j++){
					matrizAdyacenciaAux[nodoSinArcosDeEntrada][j] = 0;
				}
				//Y añadimos ese nodo al recorrido topográfico
				nodosClasificados.add(nodoSinArcosDeEntrada);
			}
		}
		
		return nodosClasificados;
	}
	
	
	/**
	 * Añade un nuevo arco dirigido al modelo del grafo visual.
	 * @param grafoJung
	 *            Modelo del grafo visual.
	 * @param f
	 *            Nodo origen (fila en la matriz de adyacencia).
	 * @param c
	 *            Nodo destino (columna en la matriz de adyacencia).
	 */
	@Override
	public void añadirArcoAlGrafoVisual(SparseMultigraph<Integer, String> grafoJung, int f, int c) {
		
		grafoJung.addEdge(new String("Nodo " + f + "-" + c + ": " + getMatrizDeAdyacencia()[f][c]),
				f, c, EdgeType.DIRECTED);
	}

}
