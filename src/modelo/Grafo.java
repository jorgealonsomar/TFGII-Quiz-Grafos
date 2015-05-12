package modelo;

import java.util.ArrayList;
import java.util.Random;


public abstract class Grafo {
	
	/** Número de nodos que componen el grafo */
	private Integer nNodos;
	
	/** Matriz de adyacencia del grafo */
	private Integer[][] matrizDeAdyacencia;
	

	/** Constructor de la clase */
	public Grafo(Integer nNodos, Double porcentajeDeArcos) {
		this.setNNodos(nNodos);
		
		//Instanciar la matriz de adyacencia
		matrizDeAdyacencia = new Integer[nNodos][nNodos];
		for(int i = 0; i < matrizDeAdyacencia.length; i++){
			for(int j = 0; j < matrizDeAdyacencia[i].length; j++){
				matrizDeAdyacencia[i][j] = 0;
			}
		}

//		construirArcosConcretos();
		construirArcosMinimos();
		construirArcosExtra(porcentajeDeArcos);
	}
	
	
	/** Construye un árbol de expansión mínimo que recorra el grafo.
	 * 
	 * Une un nodo visitado al azar con un nodo no visitado al azar (que queda marcado como 
	 * visitado), y repite este proceso hasta haber acabado con todos los nodos no visitados. */
	private void construirArcosMinimos() {
		Random random = new Random();
		
		ArrayList<Integer> nodosNoVisitados = new ArrayList<Integer>(nNodos);
		for(int i = 0; i <  nNodos; i++){
			nodosNoVisitados.add(i);
		}
		ArrayList<Integer> nodosVisitados = new ArrayList<Integer>();
		
		//Visitamos un nodo inicial
		Integer nodoInicial = nodosNoVisitados.remove(random.nextInt(nodosNoVisitados.size()));
		nodosVisitados.add(nodoInicial);
		
		//Por cada nodo no visitado
		while(!nodosNoVisitados.isEmpty()){
			//Cogemos un nodo de los visitados
			Integer nodoVisit = nodosVisitados.get(random.nextInt(nodosVisitados.size()));
			
			//Retiramos un nodo de los que aún no han sido visitados
			Integer nodoNoVisit = nodosNoVisitados.remove(random.nextInt(nodosNoVisitados.size()));
			
			//Construimos un arco entre esos dos nodos
			addArco(nodoVisit, nodoNoVisit, 1);
			
			//Marcamos ese segundo nodo como visitado
			nodosVisitados.add(nodoNoVisit);
		}
	}
	
	
	
	@SuppressWarnings("unused")
	private void construirArcosConcretos(){
		//TODO (borrar antes de entregar)
		addArco(0, 1, 1);
		addArco(0, 2, 1);
		addArco(1, 3, 1);
		addArco(1, 4, 1);
		addArco(2, 4, 1);
		addArco(4, 3, 1);
	}
	
	
	protected abstract void construirArcosExtra(Double porcentajeDeArcos);
	
	
	/** */
	protected void anadirArcosAlAzarDeLaLista(ArrayList<Arco> listaDeArcos, Double porcentajeDeArcos){
		Integer cantidadDeArcosAAñadir = (int) (porcentajeDeArcos *listaDeArcos.size());
		
		//Por cada arco que haya que añadir
		for(int i = 0; i < cantidadDeArcosAAñadir; i++){
			//Se saca un arco de la lista de posibles arcos de existentes
			Arco arcoAAñadir = listaDeArcos.remove(new Random().nextInt(listaDeArcos.size()));
			
			//Se añade al grafo ese arco
			addArco(arcoAAñadir.getNodoOrigen(), arcoAAñadir.getNodoDestino(), 1);
		}
	}
	
	
	/** Añade un nuevo arco a la matriz de adyacencia.
	 * 
	 * @param nodo1	Nodo a un extremo del arco.
	 * @param nodo2	Nodo al otro extremo del arco.
	 * @param valorArco	Valor que tendrá el arco. */
	protected abstract void addArco(Integer nodo1, Integer nodo2, int valorArco);
	
	
	/** Recorre en anchura el grafo.
	 * 
	 * @param nodoInicial Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados */
	public ArrayList<Integer> recorrerEnAnchura(int nodoInicial){
		ArrayList<Integer> pendientes = new ArrayList<Integer>();
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		
		pendientes.add(nodoInicial);
		
		//Mientras queden nodos pendientes:
		while(!pendientes.isEmpty()){
			
			//Se saca el siguiente nodo pendiente
			Integer nodoActual = pendientes.remove(0);
			
			//Por cada nodo vecino de ese nodo:
			for(Integer nodoVecino : hallarNodosVecinos(nodoActual)){
				//Si ese vecino aún no ha sido visitado:
				if(!pendientes.contains(nodoVecino) && !recorrido.contains(nodoVecino)){
					//Ese vecino se añade a la lista de nodos pendientes
					pendientes.add(nodoVecino);
				}
			}
			
			//Se añade el nodo actual al recorrido
			recorrido.add(nodoActual);
		}
		
		return recorrido;
	}
	
	
	/** Recorre en profundidad el grafo.
	 * 
	 * @param nodoInicial Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados */
	public ArrayList<Integer> recorrerEnProfundidad(int nodoInicial){
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		
		recorrerEnProfundidad_funcionRecursiva(nodoInicial, recorrido);
		
		return recorrido;
	}
	
	
	private void recorrerEnProfundidad_funcionRecursiva(int nodo, ArrayList<Integer> nodosRecorridos){
		nodosRecorridos.add(nodo);
		
		for(Integer nodoVecino : hallarNodosVecinos(nodo)){
			if(!nodosRecorridos.contains(nodoVecino)){
				recorrerEnProfundidad_funcionRecursiva(nodoVecino, nodosRecorridos);
			}
		}
		
	}


	private ArrayList<Integer> hallarNodosVecinos(int nodo) {
		ArrayList<Integer> nodosVecinos = new ArrayList<Integer>(); 
		for(int vecino_i = 0; vecino_i < nNodos; vecino_i++){
			
			//Si se puede llegar del nodo al vecino i:
			if(matrizDeAdyacencia[vecino_i][nodo] != 0){
				nodosVecinos.add(vecino_i);
			}
			
		}
		
		return nodosVecinos;
	}
	
	
	public Integer getNNodos() {
		return nNodos;
	}


	public void setNNodos(Integer nNodos) {
		this.nNodos = nNodos;
	}
	
	
	public Integer[][] getMatrizDeAdyacencia(){
		return matrizDeAdyacencia;
	}
	
	
	@Override
	public String toString(){
		String cadena = "  ";
		
		for(int i = 0; i < matrizDeAdyacencia.length; i++){
			cadena += (convertirIndiceEnLetra(i) + " ");
		}
		
		for(int i = 0; i < matrizDeAdyacencia.length; i++){
			cadena += ("\n" + convertirIndiceEnLetra(i) + " ");
			for(int j = 0; j < matrizDeAdyacencia[i].length; j++){
				cadena += (matrizDeAdyacencia[i][j] + " ");
			}
		}
		return cadena;
	}
	
	
	public static char convertirIndiceEnLetra(int indice){
		return (char) (indice + 65);
	}
	
	
	@Override
	public boolean equals(Object o){
		if(this.getClass().equals(o.getClass())
				&& this.toString().equals(o.toString()) ){
			return true;
		} else {
			return false;
		}
	}
	
	
}
