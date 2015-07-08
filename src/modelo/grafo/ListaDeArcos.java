package modelo.grafo;

import java.util.ArrayList;

/**
 * Lista de arcos, ordenados en orden de inserción. Cada arco se define en función de la identidad de
 * sus dos extremos y de su peso. 
 * @author Jorge Alonso Márquez
 */
public class ListaDeArcos {
	
	/**
	 * Nodos del primer extremo de cada arco
	 * */
	private ArrayList<Integer> nodos = new ArrayList<Integer>();
	
	/**
	 * Nodos del segundo extremo de cada arco
	 * */
	private ArrayList<Integer> predecesores = new ArrayList<Integer>();
	
	/**
	 * Peso de cada arco
	 * */
	private ArrayList<Integer> pesos = new ArrayList<Integer>();
	
	
	/**
	 * Anade un nuevo arco, sólo si el un arco con esos extremos no existe ya.
	 * Dado ese caso, no lo anadirá y devolverá false.
	 * @param nodo
	 *            Nodo del primer extremo del arco.
	 * @param predecesor
	 *            Nodo del segundo extremo del arco.
	 * @param peso
	 *            Peso del arco.
	 * @return Si se anadió o no el arco (no se anadió si ya existía un arco con
	 *         los mismo extremos).
	 */
	public boolean addArco(Integer nodo, Integer predecesor, Integer peso){
		//Si un arco con esos extremos ya existe dentro de la lista
		if (contieneElArco(nodo, predecesor)){
			return false;
		} else {
			//Se anade el nuevo arco
			nodos.add(nodo);
			predecesores.add(predecesor);
			pesos.add(peso);
			
			return true;
		}
	}
	
	
	/**
	 * Devuelve el número de arcos de la lista.
	 * @return Número de arcos.
	 */
	public Integer size(){
		return nodos.size();
	}
	
	
	/**
	 * Indica si la lista no contiene ningún arco.
	 * @return Si la lista no contiene ningún arco.
	 */
	public boolean isEmpty(){
		return (size().equals(0));
	}
	
	
	/**
	 * Devuelve el primer nodo del arco con el índice dado.
	 * @param indice
	 *            Indice del arco.
	 * @return Primer nodo del arco indicado.
	 */
	public Integer getNodoDelArco(int indice){
		return nodos.get(indice);
	}
	
	
	/**
	 * Devuelve el segundo nodo del arco con el índice dado.
	 * @param indice
	 *            Indice del arco.
	 * @return Segundo nodo del arco indicado.
	 */
	public Integer getPredecesorDelArco(int indice){
		return predecesores.get(indice);
	}
	
	
	/**
	 * Devuelve el peso del arco con el índice dado.
	 * @param indice
	 *            Indice del arco.
	 * @return Peso del arco indicado.
	 */
	public Integer getPesoDelArco(int indice){
		return pesos.get(indice);
	}
	
	
	/**
	 * Devuelve uno de los nodos del arco indicado: Aquél con el mayor peso.
	 * @param indice
	 *            Indice del arco.
	 * @return Nodo del arco con el mayor peso.
	 */
	public Integer getExtremoMayor(int indice){
		if(predecesores.get(indice) > nodos.get(indice)){
			return predecesores.get(indice);
		} else {
			return nodos.get(indice);
		}
	}
	
	
	/**
	 * Devuelve uno de los nodos del arco indicado: Aquél con el menor peso.
	 * @param indice
	 *            Indice del arco.
	 * @return Nodo del arco con el menor peso.
	 */
	public Integer getExtremoMenor(int indice){
		if(predecesores.get(indice) < nodos.get(indice)){
			return predecesores.get(indice);
		} else {
			return nodos.get(indice);
		}
	}
	
	
	/**
	 * Retira de la lista el arco indicado.
	 * @param indice Indice del arco.
	 */
	public void retirarArco(int indice){
		nodos.remove(indice);
		predecesores.remove(indice);
		pesos.remove(indice);
	}
	
	
	/**
	 * Indica si el arco definido por los dos extremos dados está dentro de esta lista.
	 * @param extremo1 Primer extremo del arco.
	 * @param extremo2 Segundo extremo del arco.
	 * @return Si el arco está dentro de esta lista.
	 */
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
	
	
	/**
	 * Devuelve el índice del arco con menor peso. En caso de existir más de un arco con similar
	 * peso, se devuelve el lexicográficamente menor.
	 * @return Indice del arco lexicográficamente menor de entre aquellos con el menor peso.
	 */
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
