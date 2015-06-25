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

	
	public ListaDeArcos algoritmoDeKruskal(){
		ListaDeArcos arcosArbolExpansion = new ListaDeArcos();

		GruposKruskal grupos = new GruposKruskal();
		
		//Construir una lista con todos los arcos del grafo
		ListaDeArcos arcosDelGrafo = listarArcosDelGrafo();
		
		//Aplicar el algoritmo
		
		//Por cada arco del grafo:
		while(!arcosDelGrafo.isEmpty()){
			Integer indiceArcoActual = arcosDelGrafo.getIndiceArcoConMenorPeso();
			Integer nodoExtremoMenor = arcosDelGrafo.getExtremoMenor(indiceArcoActual);
			Integer nodoExtremoMayor = arcosDelGrafo.getExtremoMayor(indiceArcoActual);
			
			//Si los dos extremos pertenecen al mismo grupo:
			if(!grupos.pertenecenAlMismoGrupo(nodoExtremoMenor, nodoExtremoMayor)){
				
				//Añadir el nodo al árbol de expansión
				arcosArbolExpansion.addArco(nodoExtremoMenor, nodoExtremoMayor,
						arcosDelGrafo.getPesoDelArco(indiceArcoActual));
				
				//Combinar ambos grupos
				grupos.combinarUltimosGruposComparados();

			}
			
			//Se retira el arco analizado de la lista de arcos del grafo
			arcosDelGrafo.retirarArco(indiceArcoActual);
		}
		
		return arcosArbolExpansion;
	}
	
	
	
	private class GruposKruskal{
		ArrayList<ArrayList<Integer>> grupos = new ArrayList<>();
		
		/** Grupo del primero de los dos nodos comparados en pertenecenAlMismoGrupo().
		 * Se almacena como variable global para su posterior uso en combinarUltimosComparados(). */
		Integer grupoDelNodo1 = null;
		
		/** Grupo del segundo de los dos nodos comparados en pertenecenAlMismoGrupo().
		 * Se almacena como variable global para su posterior uso en combinarUltimosComparados(). */
		Integer grupoDelNodo2 = null;
		
		/** Constructor de la clase.
		 * Construye los grupos, dejando un nodo en cada uno */
		public GruposKruskal(){
			//Por cada uno de los nNodos grupos
			for(int n = 0; n < getNNodos(); n++){
				//Se inicializa el grupo
				grupos.add(new ArrayList<Integer>());
				//Se añade a ese grupo el nodo n
				grupos.get(n).add(n);
			}
		}
		
		
		public boolean pertenecenAlMismoGrupo(Integer nodo1, Integer nodo2){
			for(int g = 0; g < grupos.size(); g++){
				if(grupos.get(g).contains(nodo1)){
					grupoDelNodo1 = g;
				}
				if(grupos.get(g).contains(nodo2)){
					grupoDelNodo2 = g;
				}
			}
			
			return (grupoDelNodo1.equals(grupoDelNodo2));
		}
		
		
		/** Combina los dos últimos grupos que se compararon mediante pertenecenAlMismoGrupo().
		 * Los elementos del segundo grupo se añaden al primero, quedando el segundo sin elementos. */
		public void combinarUltimosGruposComparados(){
			Integer tamanoOriginalDelGrupo2 = grupos.get(grupoDelNodo2).size();
			//Por cada nodo en el segundo grupo:
			for(int n = 0; n < tamanoOriginalDelGrupo2; n++){
				//Se retira un nodo de ese grupo
				Integer nodoAux = grupos.get(grupoDelNodo2).remove(0);
				//y se añade al otro
				grupos.get(grupoDelNodo1).add(nodoAux);
			}
		}
		
	}
	
	
	//Construir una lista con todos los arcos del grafo
	public ListaDeArcos listarArcosDelGrafo(){
		ListaDeArcos arcosDelGrafo = new ListaDeArcos();
		for(int f = 0; f < getMatrizDeAdyacencia().length; f++){
			for(int c = 0; c < getMatrizDeAdyacencia()[f].length; c++){
				Integer pesoDelArco = getMatrizDeAdyacencia()[f][c]; 
				if(pesoDelArco > 0){
					arcosDelGrafo.addArco(f, c, pesoDelArco);	
				}
			}
		}
		return arcosDelGrafo;
	}
	
}
