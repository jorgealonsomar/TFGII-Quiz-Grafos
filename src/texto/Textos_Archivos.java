package texto;

public class Textos_Archivos {
	
	/** Nombre de archivo correspondiente a preguntas de profunidad */
	public static Texto nombreArchivoPregProfundidad(){
		return new Texto("preguntaProfundidad", "dfsQuestion.");
	}
	
	
	/** Nombre de archivo correspondiente a preguntas de anchura */
	public static Texto nombreArchivoPregAnchura(){
		return new Texto("preguntaAnchura", "bfsQuestion.");
	}
	
	
	/** Nombre de archivo correspondiente a preguntas de topología */
	public static Texto nombreArchivoPregTopologica(){
		return new Texto("preguntaTopologica", "popologicalQuestion.");
	}
	
	
	/** Nombre de archivo correspondiente a preguntas del algoritmo de Dijkstra */
	public static Texto nombreArchivoPregDijkstra(){
		return new Texto("preguntaDijkstra", "dijkstraQuestion.");
	}
	
	
	/** Nombre de archivo correspondiente a preguntas del algoritmo de Prim */
	public static Texto nombreArchivoPregPrim(){
		return new Texto("preguntaPrim", "primQuestion.");
	}
	
	
	/** Nombre de archivo correspondiente a preguntas del algoritmo de Kruskal */
	public static Texto nombreArchivoPregKruskal(){
		return new Texto("preguntaKruskal", "kruskalQuestion.");
	}
}
