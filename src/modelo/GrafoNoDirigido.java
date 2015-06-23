package modelo;

import java.util.ArrayList;
import java.util.Random;

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
	 *            Valor que tendrá el arco.
	 */
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

}
