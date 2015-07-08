package modelo.grafo;

import java.util.ArrayList;

/**
 * Resultados de aplicar el algoritmo de Dijkstra sobre un grafo.
 * @author Jorge Alonso Márquez
 */
public class ResultadosDijkstra {
	
	/**
	 * Distancia de cada nodo al nodo origen.
	 */
	private ArrayList<Integer> distanciasAlNodoOrigen;
	
	/**
	 * Nodo previo a cada nodo.
	 */
	private ArrayList<Integer> nodosPrevios;
	
	/**
	 * Orden en el que cada nodo fue seleccionado.
	 */
	private ArrayList<Integer> ordenDeSeleccion;
	
	
	/**
	 * Constructor de la clase.
	 * Inicializa sus componentes.
	 * @param nNodos
	 *            Número de nodos del grafo sobre el que se ha aplicado el algoritmo de Dijkstra.
	 */
	public ResultadosDijkstra(int nNodos){
		distanciasAlNodoOrigen = new ArrayList<Integer>(nNodos);
		nodosPrevios = new ArrayList<Integer>(nNodos);
		ordenDeSeleccion = new ArrayList<Integer>();
		
		//Inicializar a null los arraylists
		for(int i = 0; i < nNodos; i++){
			distanciasAlNodoOrigen.add(Integer.MAX_VALUE);
			nodosPrevios.add(null);
		}
	}
	
	
	/**
	 * Devuelve la distancia de un nodo indicado al nodo origen.
	 * @param nodo
	 *            Nodo cuya distancia al origen se quiere conocer.
	 * @return Distancia del nodo indicado al origen.
	 */
	public Integer getDistanciaAlNodoOrigen(Integer nodo){
		return distanciasAlNodoOrigen.get(nodo);
	}
	
	
	/**
	 * Devuelve la lista que guarda las distancias de cada nodo al nodo origen.
	 * @return Distancias de cada nodo al origen.
	 */
	public ArrayList<Integer> getDistanciasAlNodoOrigen(){
		return distanciasAlNodoOrigen;
	}
	
	
	/**
	 * Establece la distancia de un nodo indicado al nodo origen.
	 * @param nodo
	 *            Nodo cuya distancia al origen se quiere establecer.
	 * @param distancia
	 *            Distancia del nodo indicado al origen.
	 */
	public void setDistanciaAlNodoOrigen(Integer nodo, Integer distancia){
		distanciasAlNodoOrigen.set(nodo, distancia);
	}
	
	
	/**
	 * Devuelve el nodo previo al nodo indicado.
	 * @param nodo
	 *            Nodo cuyo nodo previo se quiere conocer.
	 * @return Nodo previo al nodo indicado.
	 */
	public Integer getNodoPrevio(Integer nodo){
		return nodosPrevios.get(nodo);
	}
	
	
	/**
	 * Establece el nodo previo al nodo indicado.
	 * @param nodo
	 *            Nodo cuyo nodo previo se quiere establecer.
	 * @param nodPrevio
	 *            Nodo previo al nodo indicado.
	 */
	public void setNodoPrevio(Integer nodo, Integer nodPrevio){
		nodosPrevios.set(nodo, nodPrevio);
	}
	
	
	/**
	 * Anade un nuevo nodo a la lista ordenDeSeleccion, de forma que quedan ordenados según
	 * se van anadiendo.
	 * @param nodo Último nodo seleccionado por el algortimo.
	 */
	public void addOrdenDeSeleccion(Integer nodo){
		ordenDeSeleccion.add(nodo);
	}
	
	
	/**
	 * Devuelve la lista ordenDeSeleccion.
	 * @return Lista que contiene los nodos en el orden en el que han sido seleccionados por
	 * el algoritmo.
	 */
	public ArrayList<Integer> getOrdenDeSeleccion(){
		return ordenDeSeleccion;
	}
	
	
}
