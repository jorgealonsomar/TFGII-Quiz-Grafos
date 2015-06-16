package modelo;

import java.util.ArrayList;

public class GrafoDirigido extends Grafo {

	public GrafoDirigido(Integer nNodos, Double porcentajeArcos) {
		super(nNodos, porcentajeArcos);
	}

	public GrafoDirigido(Integer[][] matrizDeAdyacencia) {
		super(matrizDeAdyacencia);
	}

	/**
	 * Añade un nuevo arco a la matriz de adyacencia. // * El sentido del arco
	 * se decide de forma aleatoria.
	 * 
	 * @param nodo1
	 *            Nodo a un extremo del arco.
	 * @param nodo2
	 *            Nodo al otro extremo del arco.
	 * @param valorArco
	 *            Valor que tendrá el arco.
	 */
	@Override
	protected void addArco(Integer nodo1, Integer nodo2, int valorArco) {
		// if(new Random().nextBoolean()){
		// matrizDeAdyacencia[nodo1][nodo2] = valorArco;
		// } else {
		// matrizDeAdyacencia[nodo2][nodo1] = valorArco;
		// }

		getMatrizDeAdyacencia()[nodo1][nodo2] = valorArco;
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

}
