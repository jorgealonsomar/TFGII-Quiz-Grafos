package modelo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Grafo {

	/** Número de nodos que componen el grafo */
	private Integer nNodos;
	
	/** Variable booleana que define si los arcos del grafo son o no poderados */
	private boolean esPonderado;
	
	/** Generador de números aleatorios */
	Random randomGenerator;
	
	/** Matriz de adyacencia del grafo */
	private Integer[][] matrizDeAdyacencia;
	
	
	/**
	 * Constructor de la clase. Construye un nuevo grafo al azar a partir de los
	 * parámetros fijados.
	 */
	public Grafo(Integer nNodos, Double porcentajeDeArcos, boolean esPonderado, Random randomGenerator) {
		this.setNNodos(nNodos);
		this.esPonderado = esPonderado;
		this.randomGenerator = randomGenerator;
		
		// Instanciar la matriz de adyacencia
		matrizDeAdyacencia = new Integer[nNodos][nNodos];
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				matrizDeAdyacencia[i][j] = 0;
			}
		}

		construirArcosMinimos();
		construirArcosExtra(porcentajeDeArcos);
	}
	
	
	/**
	 * Constructor de la clase. Crea el grafo a partir de una matriz de
	 * adyacencia dada.
	 */
	public Grafo(Integer[][] matrizDeAdyacencia) {
		this.matrizDeAdyacencia = matrizDeAdyacencia;
		this.nNodos = matrizDeAdyacencia.length;
	}
	
	
	/**
	 * Construye un árbol de expansión mínimo que recorra el grafo.
	 * 
	 * Une un nodo visitado al azar con un nodo no visitado al azar (que queda
	 * marcado como visitado), y repite este proceso hasta haber acabado con
	 * todos los nodos no visitados.
	 */
	private void construirArcosMinimos() {
		Random random = new Random();

		ArrayList<Integer> nodosNoVisitados = new ArrayList<Integer>(nNodos);
		for (int i = 0; i < nNodos; i++) {
			nodosNoVisitados.add(i);
		}
		ArrayList<Integer> nodosVisitados = new ArrayList<Integer>();

		// Visitamos un nodo inicial
		Integer nodoInicial = nodosNoVisitados.remove(random
				.nextInt(nodosNoVisitados.size()));
		nodosVisitados.add(nodoInicial);

		// Por cada nodo no visitado
		while (!nodosNoVisitados.isEmpty()) {
			// Cogemos un nodo de los visitados
			Integer nodoVisit = nodosVisitados.get(random
					.nextInt(nodosVisitados.size()));

			// Retiramos un nodo de los que aún no han sido visitados
			Integer nodoNoVisit = nodosNoVisitados.remove(random
					.nextInt(nodosNoVisitados.size()));

			// Construimos un arco entre esos dos nodos
			addArco(nodoVisit, nodoNoVisit);

			// Marcamos ese segundo nodo como visitado
			nodosVisitados.add(nodoNoVisit);
		}
	}
	
	
	@SuppressWarnings("unused")
	private void construirArcosConcretos() {
		// TODO (borrar antes de entregar)
		addArco(0, 1);
		addArco(0, 2);
		addArco(1, 3);
		addArco(1, 4);
		addArco(2, 4);
		addArco(4, 3);
	}
	
	
	protected abstract void construirArcosExtra(Double porcentajeDeArcos);
	
	
	/** */
	protected void anadirArcosAlAzarDeLaLista(ArrayList<Arco> listaDeArcos,
			Double porcentajeDeArcos) {
		Integer cantidadDeArcosAAnadir = (int) (porcentajeDeArcos * listaDeArcos
				.size());

		// Por cada arco que haya que añadir
		for (int i = 0; i < cantidadDeArcosAAnadir; i++) {
			// Se saca un arco de la lista de posibles arcos de existentes
			Arco arcoAAnadir = listaDeArcos.remove(new Random()
					.nextInt(listaDeArcos.size()));

			// Se añade al grafo ese arco
			addArco(arcoAAnadir.getNodoOrigen(), arcoAAnadir.getNodoDestino());
		}
	}
	
	
	/**
	 * Añade un nuevo arco a la matriz de adyacencia.
	 * 
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 * @param valorArco
	 *            Valor que tendrá el arco.
	 */
	protected abstract void addArco(Integer nodo1, Integer nodo2);
	
	
	protected Integer generarValorDeArco(){
		if(esPonderado){
			//Genera un peso entero para el arco de entre 1 y 10
			return randomGenerator.nextInt(10);			
		} else {
			return 1;
		}
	}
	
	
	/**
	 * Recorre en anchura el grafo.
	 * 
	 * @param nodoInicial
	 *            Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados
	 */
	public ArrayList<Integer> recorrerEnAnchura(int nodoInicial) {
		ArrayList<Integer> pendientes = new ArrayList<Integer>();
		ArrayList<Integer> recorrido = new ArrayList<Integer>();

		pendientes.add(nodoInicial);

		// Mientras queden nodos pendientes:
		while (!pendientes.isEmpty()) {

			// Se saca el siguiente nodo pendiente
			Integer nodoActual = pendientes.remove(0);

			// Por cada nodo vecino de ese nodo:
			for (Integer nodoVecino : hallarNodosVecinos(nodoActual)) {
				// Si ese vecino aún no ha sido visitado:
				if (!pendientes.contains(nodoVecino)
						&& !recorrido.contains(nodoVecino)) {
					// Ese vecino se añade a la lista de nodos pendientes
					pendientes.add(nodoVecino);
				}
			}

			// Se añade el nodo actual al recorrido
			recorrido.add(nodoActual);
		}

		return recorrido;
	}
	
	
	/** Recorre en profundidad el grafo.
	 * @param nodoInicial
	 *            Nodo desde el que se empieza a recorrer el grafo.
	 * @return Lista con los nodos ordenados
	 */
	public ArrayList<Integer> recorrerEnProfundidad(int nodoInicial) {
		ArrayList<Integer> recorrido = new ArrayList<Integer>();

		recorrerEnProfundidad_funcionRecursiva(nodoInicial, recorrido);

		return recorrido;
	}
	
	
	private void recorrerEnProfundidad_funcionRecursiva(int nodo,
			ArrayList<Integer> nodosRecorridos) {
		nodosRecorridos.add(nodo);

		for (Integer nodoVecino : hallarNodosVecinos(nodo)) {
			if (!nodosRecorridos.contains(nodoVecino)) {
				recorrerEnProfundidad_funcionRecursiva(nodoVecino,
						nodosRecorridos);
			}
		}

	}
	
	
	public ArrayList<Integer> recorridoTopologico() {
		ArrayList<Integer> recorrido = new ArrayList<Integer>();
		
		//Crear una copia auxiliar de la matriz de adyacencia que poder modificar
		Integer[][] matrizAdyacenciaAux = clonarMatrizDeAdyacencia();
		
		boolean seguirRecorriendo = true;
		while (seguirRecorriendo){
			
			//Encontrar el primer nodo sin arcos de entrada:
			Integer nodoSinArcosDeEntrada = null;
			//Por cada columna (recorridas en orden numérico):
			for(int j = 0; j < matrizAdyacenciaAux.length; j++){
				//Si ese nodo ya se ha añadido al recorrido, se ignora
				if(!recorrido.contains(j)){
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
				recorrido.add(nodoSinArcosDeEntrada);
			}
		}
		
		return recorrido;
	}
	
	
	public ResultadosDijkstra algoritmoDeDijkstra(int nodoInicial) {
		ResultadosDijkstra resultadosDijkstra = new ResultadosDijkstra(nNodos);
		ArrayList<Boolean> nodosVisitados = new ArrayList<Boolean>(matrizDeAdyacencia.length);
		for(int n = 0; n < nNodos; n++){
			nodosVisitados.add(false);
		}
		
		//Inicializar distancias
		//Por cada nodo:
		for(int n = 0; n < matrizDeAdyacencia.length; n++){
			if(n == nodoInicial){
				resultadosDijkstra.setDistanciaAlNodoOrigen(n, 0);
			}
		}
		
		
		//Aplicar el algoritmo
		
		//Mientras queden nodos sin visitar
		while(nodosVisitados.contains(false)){
			//Encontrar el nodo con menor distAOrigen
			Integer menorDistanciaAOrigenDeEntreLosNoVisitados = null;
			Integer noVisitadoAMenorDistancia = null;
			
			for(int n = 0; n < matrizDeAdyacencia.length; n++){
				if(nodosVisitados.get(n) == false){
					if(menorDistanciaAOrigenDeEntreLosNoVisitados == null ||
							resultadosDijkstra.getDistanciaAlNodoOrigen(n) < menorDistanciaAOrigenDeEntreLosNoVisitados){
						menorDistanciaAOrigenDeEntreLosNoVisitados = resultadosDijkstra.getDistanciaAlNodoOrigen(n);
						noVisitadoAMenorDistancia = n;
					}
				}
			}
			if(!noVisitadoAMenorDistancia.equals(nodoInicial)){
				resultadosDijkstra.addOrdenDeSeleccion(noVisitadoAMenorDistancia);
			}
			Integer nodoActual = noVisitadoAMenorDistancia;

			//Se marca como leído
			nodosVisitados.set(nodoActual, true);
			
			ArrayList<Integer> nodosVecinos = new ArrayList<Integer>();
			//Hallar los vecinos no visitados de ese nodo:
			for(int v = 0; v < matrizDeAdyacencia[nodoActual].length; v++){
				if(matrizDeAdyacencia[nodoActual][v] > 0						//(si son vecinos)
						&& nodosVisitados.get(v).equals(false)){	//Si el nodo vecino no ha sido visitado
					nodosVecinos.add(v);
				}
			}
			
			
			for(Integer nodoVecino : nodosVecinos){
				
				//Visitar vecino
				//Si (distAOrigen de n + distEntreAmbosNodos) < distAOrigen de v: 
				if( (resultadosDijkstra.getDistanciaAlNodoOrigen(nodoActual) + matrizDeAdyacencia[nodoActual][nodoVecino])
						< resultadosDijkstra.getDistanciaAlNodoOrigen(nodoVecino) ){
					//distAOrigen de v := (distAOrigen de n + distEntreAmbosNodos)
					resultadosDijkstra.setDistanciaAlNodoOrigen(nodoVecino,
							(resultadosDijkstra.getDistanciaAlNodoOrigen(nodoActual) + matrizDeAdyacencia[nodoActual][nodoVecino]));
					//nodoPrevio de v := n
					resultadosDijkstra.setNodoPrevio(nodoVecino, nodoActual);
				}
					
			}
			
		}
		
		return resultadosDijkstra;
	}
	
	
	private ArrayList<Integer> hallarNodosVecinos(int nodo) {
		ArrayList<Integer> nodosVecinos = new ArrayList<Integer>();
		for (int vecino_i = 0; vecino_i < nNodos; vecino_i++) {

			// Si se puede llegar del nodo al vecino i:
			if (matrizDeAdyacencia[vecino_i][nodo] != 0) {
				nodosVecinos.add(vecino_i);
			}

		}

		return nodosVecinos;
	}
	
	
	/** Crea una copia de la matriz de adyacencia */
	private Integer[][] clonarMatrizDeAdyacencia(){
		Integer[][] matrizAdyacenciaAux = new Integer[matrizDeAdyacencia.length][matrizDeAdyacencia[0].length];
		for(int i = 0; i < matrizDeAdyacencia.length; i++){
			for(int j = 0; j < matrizDeAdyacencia.length; j++){
				matrizAdyacenciaAux[i][j] = matrizDeAdyacencia[i][j];
			}
		}
		return matrizAdyacenciaAux;
	}
	
	
	public Integer getNNodos() {
		return nNodos;
	}
	

	public void setNNodos(Integer nNodos) {
		this.nNodos = nNodos;
	}
	

	public Integer[][] getMatrizDeAdyacencia() {
		return matrizDeAdyacencia;
	}
	

	@Override
	public String toString() {
		return toTablaMatrizDeAdyacencia();
	}
	
	
	public String toTablaMatrizDeAdyacencia(){
		String cadena = "";
		
		//Fila de cabecera (letras)
		cadena += "  ";
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += (convertirIndiceEnLetra(i) + " ");
		}
		
		//Resto de filas
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n";
			cadena += (convertirIndiceEnLetra(i) + " ");
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				cadena += (matrizDeAdyacencia[i][j] + " ");
			}
		}
		
		return cadena;
	}
	
	
	
	public String toTablaMatrizDeAdyacenciaHtml(){
		String cadena = "";
		cadena += "<table border=\"1\" style=\"width:100%\">";
		
		//Fila de cabecera (letras)
		cadena += "\n\t<tr>";
		cadena += "\n\t\t<td></td>";
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(i) + "</td>";
		}
		cadena += "\n\t</tr>";
		
		//Resto de filas
		for (int i = 0; i < matrizDeAdyacencia.length; i++) {
			cadena += "\n\t<tr>";
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(i) + "</td>";
			for (int j = 0; j < matrizDeAdyacencia[i].length; j++) {
				cadena += "\n\t\t<td>" + matrizDeAdyacencia[i][j] + "</td>";
			}
			cadena += "\n\t</tr>";
		}
		cadena += "\n</table>";
		
		return cadena;
	}
	
	
	public String toTablaListaDeAdyacencia(){
		String cadena = "";
		
		//Por cada fila de la matriz de adyacencia:
		for (int f = 0; f < matrizDeAdyacencia.length; f++) {
			if (f != 0) cadena += "\n";
			cadena += (convertirIndiceEnLetra(f) + ": ");
			
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++) {
				if(matrizDeAdyacencia[f][c] > 0){
					cadena += (convertirIndiceEnLetra(c));
					if (esPonderado) cadena += (" (" + matrizDeAdyacencia[f][c] + ")");
					cadena += ", ";
				}
			}
			//Borrar los dos últimos caracteres (", ")
			cadena = cadena.substring(0, cadena.length()-2);

		}
		return cadena;
	}
	
	
	public String toTablaListaDeAdyacenciaHtml(){
		String cadena = "";
		cadena += "<table border=\"1\" style=\"width:100%\">";
		
		//Por cada fila de la matriz de adyacencia:
		for (int f = 0; f < matrizDeAdyacencia.length; f++) {
			cadena += "\n\t<tr>";
			cadena += "\n\t\t<td>" + convertirIndiceEnLetra(f) + "</td>";
			
			cadena += "\n\t\t<td>";
			String cadenaAdyacentes = "";
			for(int c = 0; c < matrizDeAdyacencia[f].length; c++) {
				if(matrizDeAdyacencia[f][c] > 0){
					cadenaAdyacentes += convertirIndiceEnLetra(c);
					if (esPonderado) cadenaAdyacentes += (" (" + matrizDeAdyacencia[f][c] + ")");
					cadenaAdyacentes += ", ";
				}
			}
			//Borrar los dos últimos caracteres (", ")
			cadenaAdyacentes = cadenaAdyacentes.substring(0, cadenaAdyacentes.length()-2);
			cadena += cadenaAdyacentes + "</td>";
			cadena += "\n\t</tr>";
			
		}
		cadena += "\n</table>";
		
		return cadena;
	}
	
	
	public String toGrafoVisual(){
		String cadena = "";
		//TODO
		return cadena;
	}
	
	
	public String toGrafoVisualHtml(){
		String cadena = "";
		//TODO
		return cadena;
	}
	

	public static char convertirIndiceEnLetra(int indice) {
		return (char) (indice + 65);
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (this.getClass().equals(o.getClass())
				&& this.toString().equals(o.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
}
