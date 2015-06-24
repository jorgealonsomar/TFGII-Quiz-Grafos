package modelo.grafo;

import java.util.ArrayList;
import java.util.Random;

import modelo.Arco;

public class GrafoDirigido extends Grafo {

	public GrafoDirigido(Integer nNodos, Double porcentajeArcos, boolean esPonderado, Random randomGenerator) {
		super(nNodos, porcentajeArcos, esPonderado, randomGenerator);
	}

	public GrafoDirigido(Integer[][] matrizDeAdyacencia) {
		super(matrizDeAdyacencia);
	}

	/** Añade un nuevo arco a la matriz de adyacencia. 
	 * El sentido del arco se decide de forma aleatoria.
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 * @param valorArco
	 *            Valor que tendrá el arco.
	 */
	@Override
	protected void addArco(Integer nodo1, Integer nodo2) {
		// if(new Random().nextBoolean()){
		// matrizDeAdyacencia[nodo1][nodo2] = valorArco;
		// } else {
		// matrizDeAdyacencia[nodo2][nodo1] = valorArco;
		// }

		getMatrizDeAdyacencia()[nodo1][nodo2] = generarValorDeArco();
	}

	protected void construirArcosExtra(Double porcentajeDeArcos) {

		// Almacenar en una lista los arcos no existentes
		ArrayList<Arco> arcosNoExistentes = new ArrayList<Arco>();
		for (int i = 0; i < getMatrizDeAdyacencia().length; i++) {
			for (int j = 0; j < getMatrizDeAdyacencia()[i].length; j++) {
				if (getMatrizDeAdyacencia()[i][j].equals(0)) {
					arcosNoExistentes.add(new Arco(i, j));
				}
			}
		}

		anadirArcosAlAzarDeLaLista(arcosNoExistentes, porcentajeDeArcos);
	}

	@Override
	public String toString() {
		return (super.toString() + "\n(directed graph)");
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

}
