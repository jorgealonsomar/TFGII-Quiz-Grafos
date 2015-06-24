package modelo.grafo;

import java.util.ArrayList;

/** Lista de arcos, ordenados en orden de inserción. Cada arco se define en función de la
 * identidad de sus dos extremos y de su peso.  */
public class ListaDeArcos {
	
	/** Nodos del primer extremo de cada arco */
	private ArrayList<Integer> nodos = new ArrayList<Integer>();
	
	/** Nodos del segundo extremo de cada arco */
	private ArrayList<Integer> predecesores = new ArrayList<Integer>();
	
	/** Peso de cada arco */
	private ArrayList<Integer> pesos = new ArrayList<Integer>();
	
	
	/** Añade un nuevo arco, sólo si el un arco con esos extremos no existe ya.
	 * Dado ese caso, no lo añadirá y devolverá false. */
	public boolean addArco(Integer nodo, Integer predecesor, Integer peso){
		//Si un arco con esos extremos ya existe dentro de la lista
		if (contieneElArco(nodo, predecesor)){
			return false;
		} else {
			//Se añade el nuevo arco
			nodos.add(nodo);
			predecesores.add(predecesor);
			pesos.add(peso);
			
			return true;
		}
	}
	
	
	public Integer size(){
		return nodos.size();
	}
	
	
	public Integer getNodoDelArco(int indice){
		return nodos.get(indice);
	}
	
	
	public Integer getPredecesorDelArco(int indice){
		return predecesores.get(indice);
	}
	
	
	public Integer getPesoDelArco(int indice){
		return pesos.get(indice);
	}
	
	
	public boolean contieneElArco(Integer extremo1, Integer extremo2){
		//Por cada arco:
		for(int a = 0; a < nodos.size(); a++){
			//Si los extremos de ese arco son iguales a esos dos nodos dados
			if(nodos.get(a).equals(extremo1) && predecesores.get(a).equals(extremo2)
					|| nodos.get(a).equals(extremo2) && predecesores.get(a).equals(extremo1)){
				//Se devuelve true
				return true;
			}
		}
		//Si no se encontró ningún arco con esos mismos extremos, se devuelve false
		return false;
	}
	
}
