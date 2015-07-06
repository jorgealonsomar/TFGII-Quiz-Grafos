package modelo.grafo;

import java.util.ArrayList;
import java.util.Random;

import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;

/**
 * Grafo cuyos arcos son no dirigidos.
 * @author Jorge Alonso Márquez
 */
public class GrafoNoDirigido extends Grafo {
	
	/**
	 * Constructor de la clase. Construye un nuevo grafo no dirigido al azar a partir de los parámetros fijados.
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
	public GrafoNoDirigido(Integer nNodos, Double porcentajeArcos, boolean esPonderado, Random randomGenerator,
			boolean grafoSinCiclos) {
		super(nNodos, porcentajeArcos, esPonderado, randomGenerator, grafoSinCiclos);
	}
	
	
	/**
	 * Constructor de la clase. Crea el grafo a partir de una matriz de adyacencia dada.
	 * @param matrizDeAdyacencia
	 *            Matriz de adyacencia del grafo.
	 */
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
	
	
	/**
	 * Añade de forma aleatoria nuevos arcos no dirigidos al grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de nuevos arcos.
	 */
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
	
	
	/**
	 * Devuelve la cadena que representa a este grafo, consistente en una representación del
	 * mismo en forma de matriz de adyacencia.
	 */
	@Override
	public String toString() {
		return (super.toString() + "\n(undirected graph)");
	}
	
	
	/**
	 * Aplica al grafo el algoritmo de Prim.
	 * @return Lista ordenada de nodos resultante de aplicar el algoritmo.
	 */
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
	
	
	/**
	 * Aplica al grafo el algoritmo de Kruskal.
	 * @return Lista ordenada de nodos resultante de aplicar el algoritmo.
	 */
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
	
	
	/**
	 * Devuelve una lista con todos los arcos del grafo
	 * @return Lista con todos los arcos del grafo.
	 */
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
	
	
	/**
	 * Añade un nuevo arco dirigido al modelo del grafo visual.
	 * Al ser no dirigido, si un arco ya existe, no lo vuelve a añadir.
	 * @param grafoJung
	 *            Modelo del grafo visual.
	 * @param f
	 *            Nodo origen (fila en la matriz de adyacencia).
	 * @param c
	 *            Nodo destino (columna en la matriz de adyacencia).
	 */
	@Override
	public void añadirArcoAlGrafoVisual(SparseMultigraph<Integer, String> grafoJung, int f, int c) {
		if(!grafoJung.containsEdge(new String("Nodo " + c + "-" + f + ": " + getMatrizDeAdyacencia()[f][c]))){
			grafoJung.addEdge(new String("Nodo " + f + "-" + c + ": " + getMatrizDeAdyacencia()[f][c])
					, f, c, EdgeType.UNDIRECTED);
		}
	}
	
	
	
	/**
	 * Almacena los nodos de cada uno de los grupos de nodos que se almacenan durante el algoritmo
	 * de Kruskal. Al principio del algoritmo, cada nodo pertenece a un grupo. Al final del mismo,
	 * todos los nodos quedan reunidos en un único grupo.
	 * @author Jorge Alonso Márquez
	 */
	private class GruposKruskal{
		ArrayList<ArrayList<Integer>> grupos = new ArrayList<>();
		
		/**
		 * Grupo del primero de los dos nodos comparados en pertenecenAlMismoGrupo().
		 * Se almacena como variable global para su posterior uso en combinarUltimosComparados().
		 */
		Integer grupoDelNodo1 = null;
		
		/**
		 * Grupo del segundo de los dos nodos comparados en pertenecenAlMismoGrupo().
		 * Se almacena como variable global para su posterior uso en combinarUltimosComparados().
		 */
		Integer grupoDelNodo2 = null;
		
		/**
		 * Constructor de la clase.
		 * Construye los grupos iniciales, dejando un nodo en cada uno.
		 * */
		public GruposKruskal(){
			//Por cada uno de los nNodos grupos
			for(int n = 0; n < getNNodos(); n++){
				//Se inicializa el grupo
				grupos.add(new ArrayList<Integer>());
				//Se añade a ese grupo el nodo n
				grupos.get(n).add(n);
			}
		}
		
		
		/**
		 * Indica si los nodos dados pertenecen a un mismo grupo.
		 * @param nodo1 Primer nodo.
		 * @param nodo2 Segundo nodo.
		 * @return Si los nodos dados pertenecen a un mismo grupo.
		 */
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
		
		
		/**
		 * Combina los dos últimos grupos que se compararon mediante pertenecenAlMismoGrupo().
		 * Los elementos del segundo grupo se añaden al primero, quedando el segundo sin elementos.
		 */
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
}
