package modelo.grafo;

import java.util.ArrayList;
import java.util.Random;

import modelo.Arco;

public class GrafoNoDirigido extends Grafo {
	
	public GrafoNoDirigido(Integer nNodos, Double porcentajeArcos, boolean esPonderado, Random randomGenerator) {
		super(nNodos, porcentajeArcos, esPonderado, randomGenerator);
		// TODO Auto-generated constructor stub
	}

	public GrafoNoDirigido(Integer[][] matrizDeAdyacencia) {
		super(matrizDeAdyacencia);
	}
	
	
	/** Añade un nuevo arco no dirigido a la matriz de adyacencia.
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 * @param valorArco
	 *            Valor que tendrá el arco. */
	@Override
	protected void addArco(Integer nodo1, Integer nodo2) {
		Integer valorArco = generarValorDeArco();
		
		getMatrizDeAdyacencia()[nodo1][nodo2] = valorArco;
		getMatrizDeAdyacencia()[nodo2][nodo1] = valorArco;
	}
	
	
	protected void construirArcosExtra(Double porcentajeDeArcos) {
		// Almacenar en una lista los arcos no existentes
		ArrayList<Arco> arcosNoExistentes = new ArrayList<Arco>();
		for (int i = 0; i < getMatrizDeAdyacencia().length; i++) {
			for (int j = 0; j < i; j++) {
				if (getMatrizDeAdyacencia()[i][j].equals(0)) {
					arcosNoExistentes.add(new Arco(i, j));
				}
			}
		}

		anadirArcosAlAzarDeLaLista(arcosNoExistentes, porcentajeDeArcos);
	}
	
	
	@Override
	public String toString() {
		return (super.toString() + "\n(undirected graph)");
	}
	
	
	public ListaDeArcos algoritmoDePrim(){
		ListaDeArcos arcosRecorridos = new ListaDeArcos();
		
		ArrayList<Integer> costesMinimos = new ArrayList<Integer>();
		ArrayList<Integer> nodosPredecesores = new ArrayList<Integer>();
		ArrayList<Integer> nodosPorRecorrer = new ArrayList<Integer>();
		
		// Inicializar valores
		for(int n = 0; n < getMatrizDeAdyacencia().length; n++){
			costesMinimos.add(Integer.MAX_VALUE);
			nodosPredecesores.add(null);
			
			nodosPorRecorrer.add(n);
		}
		
		//Mientras queden nodos por recorrer:
		while(!nodosPorRecorrer.isEmpty()){
			
			//Encontrar el nodo no visitado con menor coste mínimo
			Integer minimoCosteEncontrado = null;
			Integer nodoConElMinimoCosteEncontrado = null;
			
			for(int i = 0; i < nodosPorRecorrer.size(); i++){
				Integer n = nodosPorRecorrer.get(i);
				if(minimoCosteEncontrado == null || costesMinimos.get(n) < minimoCosteEncontrado){
					minimoCosteEncontrado = costesMinimos.get(n);
					nodoConElMinimoCosteEncontrado = n;
				}
			}
			Integer nodoActual = nodoConElMinimoCosteEncontrado;
			nodosPorRecorrer.remove(nodoConElMinimoCosteEncontrado);
			
			// Añadir el arco a la lista de arcos recorridos
			if(!nodoActual.equals(0)){  //Si no es el nodo inicial (el nodo A)
				arcosRecorridos.addArco(nodoActual, nodosPredecesores.get(nodoActual),
						getMatrizDeAdyacencia()[nodosPredecesores.get(nodoActual)][nodoActual]);
			}
			
			//Por cada vecino del nodo actual
			for(int v = 0; v < getMatrizDeAdyacencia()[nodoActual].length; v++){
				if(getMatrizDeAdyacencia()[nodoActual][v] > 0){	//(si son vecinos)
					
					//Si el coste de ir de nodoActual al nodo v es menor que el registrado actualmente
					//como coste mínimo para v: 
					if(getMatrizDeAdyacencia()[nodoActual][v] < costesMinimos.get(v)){
						//El coste mínimo de v pasa ahora a valer ese valor
						costesMinimos.set(v, getMatrizDeAdyacencia()[nodoActual][v]);
						//Y nodoActual queda inscrito como predecesor del nodo v
						nodosPredecesores.set(v, nodoActual);
					}
					
				}
			}
			
		}
		return arcosRecorridos;
	}

}
