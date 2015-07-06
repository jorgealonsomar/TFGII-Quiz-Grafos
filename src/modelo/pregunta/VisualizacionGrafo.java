package modelo.pregunta;

/**
 * Enum con los distintos modos de visualización del grafo.
 * @author Jorge Alonso Márquez
 */
public enum VisualizacionGrafo {
	
	/**
	 * Mostrarlo como una matriz de adyacencia.
	 */
	MATRIZ_DE_ADYACENCIA,

	/**
	 * Mostrarlo como una lista de adyacencia.
	 */
	LISTA_DE_ADYACENCIA,
	
	/**
	 * Mostrarlo mediante una imagen visual.
	 */
	GRAFO_VISUAL,
	
	/**
	 * Mostrarlo mediante una imagen visual, y, adicionalmente, mostrar su matriz de adyacencia.
	 */
	GRAFO_VISUAL_MAS_MATRIZ;
}
