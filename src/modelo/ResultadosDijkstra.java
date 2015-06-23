package modelo;

import java.util.ArrayList;

public class ResultadosDijkstra {
	
	private ArrayList<Integer> distanciasAlNodoOrigen;
	private ArrayList<Integer> nodosPrevios;
	private ArrayList<Integer> ordenDeSeleccion;
	
	
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

	public Integer getDistanciaAlNodoOrigen(Integer nodo){
		return distanciasAlNodoOrigen.get(nodo);
	}
	
	
	public ArrayList<Integer> getDistanciasAlNodoOrigen(){
		return distanciasAlNodoOrigen;
	}
	
	
	public void setDistanciaAlNodoOrigen(Integer nodo, Integer distancia){
		distanciasAlNodoOrigen.set(nodo, distancia);
	}
	
	
	public Integer getNodoPrevio(Integer nodo){
		return nodosPrevios.get(nodo);
	}
	
	
	public void setNodoPrevio(Integer nodo, Integer nodPrevio){
		nodosPrevios.set(nodo, nodPrevio);
	}
	
	
	public void addOrdenDeSeleccion(Integer nodo){
		ordenDeSeleccion.add(nodo);
	}
	
	
	public ArrayList<Integer> getOrdenDeSeleccion(){
		return ordenDeSeleccion;
	}
	
	
}
