package texto;

/**
 * Nombres de archivo de cada uno de los ficheros generados que corresponde a cada
 * tipo de pregunta.
 * @author Jorge Alonso Márquez
 */
public class Textos_Archivos {
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas de profunidad.
	 * @return Nombre de archivo correspondiente a preguntas de profunidad.
	 */
	public static Texto nombreArchivoPregProfundidad(){
		return new Texto("preguntaProfundidad", "dfsQuestion.");
	}
	
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas de anchura.
	 * @return Nombre de archivo correspondiente a preguntas de anchura.
	 */
	public static Texto nombreArchivoPregAnchura(){
		return new Texto("preguntaAnchura", "bfsQuestion.");
	}
	
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas de topología.
	 * @return Nombre de archivo correspondiente a preguntas de topología.
	 */
	public static Texto nombreArchivoPregTopologica(){
		return new Texto("preguntaTopologica", "popologicalQuestion.");
	}
	
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas del algoritmo de Dijkstra.
	 * @return Nombre de archivo correspondiente a preguntas del algoritmo de Dijkstra.
	 */
	public static Texto nombreArchivoPregDijkstra(){
		return new Texto("preguntaDijkstra", "dijkstraQuestion.");
	}
	
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas del algoritmo de Prim.
	 * @return Nombre de archivo correspondiente a preguntas del algoritmo de Prim.
	 */
	public static Texto nombreArchivoPregPrim(){
		return new Texto("preguntaPrim", "primQuestion.");
	}
	
	
	/**
	 * Devuelve el nombre de archivo correspondiente a preguntas del algoritmo de Kruskal.
	 * @return Nombre de archivo correspondiente a preguntas del algoritmo de Kruskal.
	 */
	public static Texto nombreArchivoPregKruskal(){
		return new Texto("preguntaKruskal", "kruskalQuestion.");
	}
}
