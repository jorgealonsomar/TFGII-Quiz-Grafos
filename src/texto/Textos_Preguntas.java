package texto;

public class Textos_Preguntas {
	
	/** Inicio de una cláusula multichoice */
	public static Texto abrirClausulaMultichoice(){
		return new Texto("{1:MULTICHOICE:");
	}
	
	
	/** Inicio de una cláusula shortanswer */
	public static Texto abrirClausulaShortanswer(){
		return new Texto("{1:SHORTANSWER:");
	}
	
	
	/** Tilde, usada en los multichoice para separar las distintas opciones */
	public static Texto tilde(){
		return new Texto("~");
	}
	
	
	/** Coma de separación entre distintos elementos de una enumeración */
	public static Texto coma(){
		return new Texto(", ");
	}
	
	
	/** Cierre de una cláusula */
	public static Texto cerrarClausula(){
		return new Texto("} ");
	}
	
	
	/** Cierre de una corchete, y fin de frase. */
	public static Texto cerrarCorchete(){
		return new Texto("].");
	}
	
	
	/** Nombre de un nodo, correspondiente a un caracter dado. */
	public static Texto nombreDeNodo(Character caracter){
		return new Texto(caracter.toString());
	}
	
	
	/** Opción correcta (p. ej. en un multichoice) que añade un 100 del valor al peso de la nota */
	public static Texto opcionCorrecta100(){
		return new Texto("%100%");
	}
	
	
	/** Opción incorrecta (p. ej. en un multichoice) que resta un 100% del valor al peso de la nota*/
	public static Texto opcionQueResta100(){
		return new Texto("%-100%");
	}
	
	
	public static Texto comentarioAcierto(){
		return new Texto("#Correcto", "#Right");
	}
	
	
	public static Texto comentarioError(){
		return new Texto("#Error", "#Wrong");
	}
	
	
	/** Fragmento inicial de una pregunta sobre recorrido de grafo */
	public static Texto pregRecorrido_ResultadoDeRecorrerGrafo(){
		return new Texto("El resultado de recorrer el grafo es el siguiente: [ ",
				"The resulting path is as follows: [ ");
	}
	
	
	/** Fragmento inicial de una respuesta */
	public static Texto respuestaCorrectaEs(){
		return new Texto("La respuesta correcta es: [",
				"The right answer is: [ ");
	}
	
	
	/** Distancia desde el nodo A. Usado en las preguntas de distancia más corta (Dijkstra). */
	public static Texto distanciaDesdeElNodoA(){
		return new Texto("Distancia del nodo A al nodo ",
				"Distance from node A to node ");
	}
	
	
	/** Ruta. Usado en las preguntas de ruta más corta (Dijkstra) */
	public static Texto laRutaEs(){
		return new Texto("La ruta es: ",
				"The path is: ");
	}
	
	
	/** Distancia. Usado en las preguntas de ruta más corta (Dijkstra) */
	public static Texto laDistanciaEs(){
		return new Texto("La distancia es: ",
				"The lenght is: ");
	}
	
	
	/** Orden de selección. Usado en las preguntas de orden de selección (Dijkstra) */
	public static Texto ordenDeSeleccion(){
		return new Texto("Orden de selección",
				"Selection order");
	}
	
	
	/** Nodo. Usado en las preguntas de orden de selección (Dijkstra, Prim) */
	public static Texto nodo(){
		return new Texto("Nodo",
				"Node");
	}
	
	
	/** Predecesor. Usado en las preguntas de orden de selección (Dijkstra, Prim) */
	public static Texto predecesor(){
		return new Texto("Predecesor",
				"Predecessor");
	}
	
	
	/** Distancia. Usado en las preguntas de orden de selección (Dijkstra) */
	public static Texto distancia(){
		return new Texto("Distancia",
				"Distance");
	}
	
	
	/** Peso. Usado en las preguntas de orden de selección (Prim) */
	public static Texto pesoDelArco(){
		return new Texto("Peso del arco",
				"Weight of the arc");
	}
	
	
	/** Arco. Usado en las preguntas de orden de selección (Kruskal) */
	public static Texto arco(){
		return new Texto("Arco",
				"Arc");
	}
	
	
	/** Pertenece. Usado en las preguntas de hallar los arcos del árbol de expansión (Prim) */
	public static Texto pertenece(){
		return new Texto("Pertenece",
				"Belongs");
	}
	
	
	/** No pertenece. Usado en las preguntas de hallar los arcos del árbol de expansión (Prim) */
	public static Texto noPertenece(){
		return new Texto("No pertenece",
				"Does not belong");
	}
	
	
	/** Título de una pregunta de Recorrido en Anchura */
	public static Texto tituloPregAnchura(){
		return new Texto("Pregunta de Recorrido en Anchura",
				"Breadth-First Search Question.");
	}
	
	
	/** Título de una pregunta de Recorrido en Profundidad */
	public static Texto tituloPregProfundidad(){
		return new Texto("Pregunta de Recorrido en Profundidad",
				"Depth-First Search Question.");
	}
	
	
	/** Título de una pregunta de Clasificación Topológica */
	public static Texto tituloPregClasificacionTopologica(){
		return new Texto("Pregunta de Clasificación Topológica",
				"Topological Sort Question.");
	}
	
	
	/** Título de una pregunta del Algoritmo de Dijkstra */
	public static Texto tituloPregDijkstra(){
		return new Texto("Pregunta del Algoritmo de Dijkstra",
				"Dijkstra's Algorithm Question.");
	}
	
	
	/** Título de una pregunta del Algoritmo de Prim */
	public static Texto tituloPregPrim(){
		return new Texto("Pregunta del Algoritmo de Prim",
				"Prim's Algorithm Question.");
	}
	
	
	/** Título de una pregunta del Algoritmo de Kruskal */
	public static Texto tituloPregKruskal(){
		return new Texto("Pregunta del Algoritmo de Kruskal",
				"Kruskal's Algorithm Question.");
	}
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregAnchura(){
		return new Texto("Partiendo de A, recorre el grafo en anchura. En caso de poderse seleccionar más de un nodo,"
				+ "los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Breadth-first search into the graph. When more than one node can be chosen,"
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregProfundidad(){
		return new Texto("Partiendo de A, recorre el grafo en profundidad. En caso de poderse seleccionar más de un nodo,"
				+ "los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Depth-first search into the graph. When more than one node can be chosen,"
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta de Clasificación Topológica */
	public static Texto enunciadoPregClasificacionTopologica(){
		return new Texto("Realiza una clasificación topológica del grafo. En caso de poderse seleccionar más de un nodo,"
				+ "los nodos se deben escoger en orden alfabético.",
				"Do a Topological Sort of the graph. When more than one node can be chosen,"
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta del Algoritmo de Dijkstra: Distancias más cortas */
	public static Texto enunciadoPregDijkstra_DistanciasMasCortas(){
		return new Texto("Calcula la distancia mínima entre el nodo A y cada uno de los otros nodos del grafo.",
				"Find the shortest distance between node A and every other nodo in the graph.");
	}
	
	
	/** Enunciado de una pregunta del Algoritmo de Dijkstra: Ruta más corta */
	public static Texto enunciadoPregDijkstra_RutaMasCorta(Character nodoObjetivo){
		return new Texto("Encuentra la ruta más corta entre el nodo A y el nodo " + nodoObjetivo +
				", y di a qué distancia mínima se encuentran.",
				"Find the shortest path between node A and node " + nodoObjetivo +
				", and say what is the minimum distance between them.");
	}
	
	
	/** Enunciado de una pregunta del Algoritmo de Dijkstra: Orden de selección */
	public static Texto enunciadoPregDijkstra_OrdenDeSeleccion(){
		return new Texto("Aplica el algoritmo de Dijkstra sobre el grafo dado, tomando como origen el nodo A. "
				+ "Se ha de indicar el orden en el que se seleccionan los nodos, en nodo predecesor en el"
				+ "camino desde el origen y la distancia del camino mínimo desde el origen.",
				"Apply the Dijkstra algorithm on the graph, taking node A as the starting node. "
				+ "It must be indicated in witch order the nodes are selected, as well as the predecessor"
				+ "node in the path, and the lenght from the minimum path to the starting node.");
	}
	
	
	/** Enunciado de una pregunta del Algoritmo de Prim: Arcos del Árbol de Expansión */
	public static Texto enunciadoPregPrim_ArcosDelArbolDeExpansion(){
		return new Texto("Aplica el algoritmo de Prim sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Indica cuáles de los siguientes arcos pertenecen al árbol de expansión resultante.",
				"Apply the Prim algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Say which of the following arcs belong to the resulting spanning tree");
	}
	
	
	/** Enunciado de una pregunta del Algoritmo de Prim: Arcos del Árbol de Expansión */
	public static Texto enunciadoPregPrim_OrdenDeSeleccion(){
		return new Texto("Aplica el algoritmo de Prim sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Rellena la siguiente tabla.",
				"Apply the Prim algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Fill the following table.");
	}
	
	
	/** Texto asociado a las preguntas de Distancias más cortas (Dijkstra) */
	public static Texto tipoPreguntaDijkstra_DistanciasMasCortas(){
		return new Texto("Cálculo de distancias más cortas", "Shortest distances finding");
	}
	
	
	/** Texto asociado a las preguntas de Ruta más corta (Dijkstra) */
	public static Texto tipoPreguntaDijkstra_RutaMasCorta(){
		return new Texto("Ruta más corta entre dos nodos", "Shortest path between two nodes");
	}
	
	
	/** Texto asociado a las preguntas de Arcos del árbol de expansión (Prim y Kruskal) */
	public static Texto tipoPregunta_ArcosDelArbolDeExpansion(){
		return new Texto("Arcos del árbol de expansión", "Spanning tree arcs");
	}
	
	
	/** Texto asociado las preguntas de orden de selección (Dijkstra, Prim y Kruskal) */
	public static Texto tipoPregunta_OrdenDeSeleccion(){
		return new Texto("Orden de selección", "Selection order");
	}
	
}
