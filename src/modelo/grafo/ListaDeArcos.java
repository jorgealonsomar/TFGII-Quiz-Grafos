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
	
	
	public boolean isEmpty(){
		return (size().equals(0));
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
	
	
	public Integer getExtremoMayor(int indice){
		if(predecesores.get(indice) > nodos.get(indice)){
			return predecesores.get(indice);
		} else {
			return nodos.get(indice);
		}
	}
	
	
	public Integer getExtremoMenor(int indice){
		if(predecesores.get(indice) < nodos.get(indice)){
			return predecesores.get(indice);
		} else {
			return nodos.get(indice);
		}
	}
	
	
	public void retirarArco(int indice){
		nodos.remove(indice);
		predecesores.remove(indice);
		pesos.remove(indice);
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
	
	
	/** Devuelve el índice del arco con menor peso. En caso de existir más de un arco con similar
	 * peso, se devuelve el lexicográficamente menor. */
	public Integer getIndiceArcoConMenorPeso(){
		//Hallar el menor peso
		Integer menorPeso = Integer.MAX_VALUE;
		for(Integer peso : pesos){
			if(peso < menorPeso){
				menorPeso = peso;
			}
		}
		
		//Hallar el nodo lexicográficamente menor de entre aquellos con el menor peso
		Integer indiceDelArcoLexicograficamenteMenor = null;
		Integer primeraLetraDelArcoLexicograficamenteMenor = Integer.MAX_VALUE;
		Integer segundaLetraDelArcoLexicograficamenteMenor = Integer.MAX_VALUE;
		for(int n = 0; n < size(); n++){
			if(pesos.get(n).equals(menorPeso)){
			
				if(nodos.get(n) < primeraLetraDelArcoLexicograficamenteMenor){
					indiceDelArcoLexicograficamenteMenor = n;
					primeraLetraDelArcoLexicograficamenteMenor = nodos.get(n);
					segundaLetraDelArcoLexicograficamenteMenor = predecesores.get(n);
				} else if(nodos.get(n).equals(primeraLetraDelArcoLexicograficamenteMenor)
						&& predecesores.get(n) < segundaLetraDelArcoLexicograficamenteMenor){
					indiceDelArcoLexicograficamenteMenor = n;
					segundaLetraDelArcoLexicograficamenteMenor = predecesores.get(n);
				}
				
				if(predecesores.get(n) < primeraLetraDelArcoLexicograficamenteMenor){
					indiceDelArcoLexicograficamenteMenor = n;
					primeraLetraDelArcoLexicograficamenteMenor = predecesores.get(n);
					segundaLetraDelArcoLexicograficamenteMenor = nodos.get(n);
				} else if(predecesores.get(n).equals(primeraLetraDelArcoLexicograficamenteMenor)
						&& nodos.get(n) < segundaLetraDelArcoLexicograficamenteMenor){
					indiceDelArcoLexicograficamenteMenor = n;
					segundaLetraDelArcoLexicograficamenteMenor = nodos.get(n);
				}
				
			}
		}
		return indiceDelArcoLexicograficamenteMenor;
	}
	
}
