package texto;

/**
 * Textos empleados en la generación de preguntas.
 * @author Jorge Alonso Márquez
 */
public class Textos_Preguntas {
	
	/**
	 * Devuelve el texto que se pone al inicio de una cláusula multichoice.
	 * @return Texto al inicio de una cláusula multichoice.
	 */
	public static Texto abrirClausulaMultichoice(){
		return new Texto("{1:MULTICHOICE:");
	}
	
	
	/**
	 * Devuelve el texto que se pone al inicio de una cláusula shortanswer.
	 * @return Texto al inicio de una cláusula shortanswer.
	 */
	public static Texto abrirClausulaShortanswer(){
		return new Texto("{1:SHORTANSWER:");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una tilde, usada en los multichoice para separar las
	 * distintas opciones.
	 * @return Texto correspondiente a una tilde.
	 */
	public static Texto tilde(){
		return new Texto("~");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una coma, usada para separar distintos elementos en una
	 * enumeración.
	 * @return Texto correspondiente a una coma.
	 */
	public static Texto coma(){
		return new Texto(", ");
	}
	
	
	/**
	 * Devuelve el texto correspondiente al cerrado de una cláusula de moodle-xml.
	 * @return Texto correspondiente al cerrado de una cláusula de moodle-xml.
	 */
	public static Texto cerrarClausula(){
		return new Texto("} ");
	}
	
	
	/**
	 * Devuelve un texto con un cierre de corchete y un punto de fin de frase.
	 * @return Texto con cierre de corchete, y fin de frase.
	 */
	public static Texto cerrarCorchete(){
		return new Texto("].");
	}
	
	
	/**
	 * Devuelve un texto con la cadena correspondiente a un nodo cuya letra sea el carácter dado.
	 * @return Texto con el nombre de un nodo, correspondiente al caracter dado.
	 */
	public static Texto nombreDeNodo(Character caracter){
		return new Texto(caracter.toString());
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una opción correcta en moodle-xml Anade un 100 del valor al
	 * peso de la nota.
	 * @return Texto correspondiente a una opción correcta.
	 */
	public static Texto opcionCorrecta100(){
		return new Texto("%100%");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una opción incorrecta en moodle-xml Resta un 100 del valor al
	 * peso de la nota.
	 * @return Texto correspondiente a una opción incorrecta.
	 */
	public static Texto opcionQueResta100(){
		return new Texto("%-100%");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una respuesta acertada en moodle-xml.
	 * @return Texto correspondiente a una respuesta acertada.
	 */
	public static Texto comentarioAcierto(){
		return new Texto("#Correcto", "#Right");
	}
	
	
	/**
	 * Devuelve el texto correspondiente a una respuesta fallida en moodle-xml.
	 * @return Texto correspondiente a una respuesta fallida.
	 */
	public static Texto comentarioError(){
		return new Texto("#Error", "#Wrong");
	}
	
	
	/**
	 * Devuelve el fragmento inicial de una pregunta sobre recorrido de grafo .
	 * @return Fragmento inicial de una pregunta sobre recorrido de grafo.
	 */
	public static Texto pregRecorrido_ResultadoDeRecorrerGrafo(){
		return new Texto("El resultado de recorrer el grafo es el siguiente: [ ",
				"The resulting path is as follows: [ ");
	}
	
	
	/**
	 * Devuelve el fragmento inicial de una respuesta.
	 * @return Fragmento inicial de una respuesta.
	 */
	public static Texto respuestaCorrectaEs(){
		return new Texto("La respuesta correcta es: [",
				"The right answer is: [ ");
	}
	
	
	/**
	 * Devuelve el texto que presenta la distancia desde el nodo A.
	 * @return Texto que presenta la distancia desde el nodo A..
	 */
	public static Texto distanciaDesdeElNodoA(){
		return new Texto("Distancia del nodo A al nodo ",
				"Distance from node A to node ");
	}
	
	
	/**
	 * Devuelve el texto que presenta la ruta más corta (Se usa en preguntas de Dijsktra).
	 * @return Texto que presenta la ruta más corta.
	 */
	public static Texto laRutaEs(){
		return new Texto("La ruta es: ",
				"The path is: ");
	}
	
	
	/**
	 * Devuelve el texto que presenta la distancia (Se usa en preguntas de Dijsktra).
	 * @return Texto que presenta la distancia.
	 */
	public static Texto laDistanciaEs(){
		return new Texto("La distancia es: ",
				"The lenght is: ");
	}
	
	
	/**
	 * Devuelve el texto relativo al orden de selección (Se usa en preguntas de Dijsktra).
	 * @return Texto relativo al orden de selección.
	 */
	public static Texto ordenDeSeleccion(){
		return new Texto("Orden de selección",
				"Selection order");
	}
	
	
	/**
	 * Devuelve el texto relativo al nodo (Se usa en preguntas de Dijsktra y de Prim).
	 * @return Texto relativo al nodo.
	 */
	public static Texto nodo(){
		return new Texto("Nodo",
				"Node");
	}
	
	
	/**
	 * Devuelve el texto relativo al nodo predecesor (Se usa en preguntas de Dijsktra y de Prim).
	 * @return Texto relativo al nodo predecesor.
	 */
	public static Texto predecesor(){
		return new Texto("Predecesor",
				"Predecessor");
	}
	
	
	/**
	 * Devuelve el texto relativo a la distancia (Se usa en preguntas de Dijsktra).
	 * @return Texto relativo a la distancia.
	 */
	public static Texto distancia(){
		return new Texto("Distancia",
				"Distance");
	}
	
	
	/**
	 * Devuelve el texto relativo al peso (Se usa en preguntas de Prim).
	 * @return Texto relativo al peso.
	 */
	public static Texto pesoDelArco(){
		return new Texto("Peso del arco",
				"Weight of the arc");
	}
	
	
	/**
	 * Devuelve el texto relativo al arco (Se usa en preguntas de Kruskal).
	 * @return Texto relativo al arco.
	 */
	public static Texto arco(){
		return new Texto("Arco",
				"Arc");
	}
	
	
	/**
	 * Devuelve un texto que indica pertenencia (Se usa en las preguntas de hallar los arcos del árbol
	 * de expansión de Prim).
	 * @return Texto que indica pertenencia.
	 */
	public static Texto pertenece(){
		return new Texto("Pertenece",
				"Belongs");
	}
	
	
	/**
	 * Devuelve un texto que indica no pertenencia (Se usa en las preguntas de hallar los arcos del árbol
	 * de expansión de Prim).
	 * @return Texto que indica no pertenencia.
	 */
	public static Texto noPertenece(){
		return new Texto("No pertenece",
				"Does not belong");
	}
	
	
	/**
	 * Devuelve el título de una pregunta de Recorrido en Anchura.
	 * @return Título de una pregunta de Recorrido en Anchura.
	 */
	public static Texto tituloPregAnchura(){
		return new Texto("Pregunta de Recorrido en Anchura",
				"Breadth-First Search Question.");
	}
	
	
	/**
	 * Devuelve el título de una pregunta de Recorrido en Profundidad.
	 * @return Título de una pregunta de Recorrido en Profundidad.
	 */
	public static Texto tituloPregProfundidad(){
		return new Texto("Pregunta de Recorrido en Profundidad",
				"Depth-First Search Question.");
	}
	
	
	/**
	 * Devuelve el título de una pregunta de Clasificación Topológica.
	 * @return Título de una pregunta de Clasificación Topológica.
	 */
	public static Texto tituloPregClasificacionTopologica(){
		return new Texto("Pregunta de Clasificación Topológica",
				"Topological Sort Question.");
	}
	
	
	/**
	 * Devuelve el título de una pregunta del Algoritmo de Dijkstra.
	 * @return Título de una pregunta del Algoritmo de Dijkstra.
	 */
	public static Texto tituloPregDijkstra(){
		return new Texto("Pregunta del Algoritmo de Dijkstra",
				"Dijkstra's Algorithm Question.");
	}
	
	
	/**
	 * Devuelve el título de una pregunta del Algoritmo de Prim.
	 * @return Título de una pregunta del Algoritmo de Prim.
	 */
	public static Texto tituloPregPrim(){
		return new Texto("Pregunta del Algoritmo de Prim",
				"Prim's Algorithm Question.");
	}
	
	
	/**
	 * Devuelve el título de una pregunta del Algoritmo de Kruskal.
	 * @return Título de una pregunta del Algoritmo de Kruskal.
	 */
	public static Texto tituloPregKruskal(){
		return new Texto("Pregunta del Algoritmo de Kruskal",
				"Kruskal's Algorithm Question.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta de Recorrido en Anchura.
	 * @return Enunciado de una pregunta de Recorrido en Anchura.
	 */
	public static Texto enunciadoPregAnchura(){
		return new Texto("Partiendo de A, recorre el grafo en anchura. En caso de poderse seleccionar más de un nodo, "
				+ "los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Breadth-first search into the graph. When more than one node can be chosen, "
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta de Recorrido en Anchura.
	 * @return Enunciado de una pregunta de Recorrido en Anchura.
	 */
	public static Texto enunciadoPregProfundidad(){
		return new Texto("Partiendo de A, recorre el grafo en profundidad. En caso de poderse seleccionar más de un nodo, "
				+ "los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Depth-first search into the graph. When more than one node can be chosen, "
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta de Clasificación Topológica.
	 * @return Enunciado de una pregunta de Clasificación Topológica.
	 */
	public static Texto enunciadoPregClasificacionTopologica(){
		return new Texto("Realiza una clasificación topológica del grafo. En caso de poderse seleccionar más de un nodo, "
				+ "los nodos se deben escoger en orden alfabético.",
				"Do a Topological Sort of the graph. When more than one node can be chosen, "
				+ "nodes must be selected in alphabetic order.");
	}
	
	
	/**
	 * Devuelve el Enunciado de una pregunta del Algoritmo de Dijkstra de Distancias más cortas.
	 * @return Enunciado de una pregunta del Algoritmo de Dijkstra de Distancias más cortas.
	 */
	public static Texto enunciadoPregDijkstra_DistanciasMasCortas(){
		return new Texto("Calcula la distancia mínima entre el nodo A y cada uno de los otros nodos del grafo. "
				+ "Si crees que la distancia es infinita (no existe el camino), pon una 'i'.",
				"Find the shortest distance between node A and every other nodo in the graph. "
				+ "If you think the distance is infinte, write an 'i'");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Dijkstra de Ruta más corta.
	 * @return Enunciado de una pregunta del Algoritmo de Dijkstra de Ruta más corta.
	 */
	public static Texto enunciadoPregDijkstra_RutaMasCorta(Character nodoObjetivo){
		return new Texto("Encuentra la ruta más corta entre el nodo A y el nodo " + nodoObjetivo +
				", y di a qué distancia mínima se encuentran.",
				"Find the shortest path between node A and node " + nodoObjetivo +
				", and say what is the minimum distance between them.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Dijkstra de Orden de selección.
	 * @return Enunciado de una pregunta del Algoritmo de Dijkstra de Orden de selección.
	 */
	public static Texto enunciadoPregDijkstra_OrdenDeSeleccion(){
		return new Texto("Aplica el algoritmo de Dijkstra sobre el grafo dado, tomando como origen el nodo A. "
				+ "Se ha de indicar el orden en el que se seleccionan los nodos, en nodo predecesor en el "
				+ "camino desde el origen y la distancia del camino mínimo desde el origen. ",
				"Apply the Dijkstra algorithm on the graph, taking node A as the starting node. "
				+ "It must be indicated in witch order the nodes are selected, as well as the predecessor "
				+ "node in the path, and the lenght from the minimum path to the starting node.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Prim de Arcos del Arbol de Expansión.
	 * @return Enunciado de una pregunta del Algoritmo de Prim de Arcos del Arbol de Expansión.
	 */
	public static Texto enunciadoPregPrim_ArcosDelArbolDeExpansion(){
		return new Texto("Aplica el algoritmo de Prim sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Indica cuáles de los siguientes arcos pertenecen al árbol de expansión resultante. ",
				"Apply the Prim algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Say which of the following arcs belong to the resulting spanning tree. ");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Prim de Arcos del Arbol de Expansión.
	 * @return Enunciado de una pregunta del Algoritmo de Prim de Arcos del Arbol de Expansión.
	 */
	public static Texto enunciadoPregPrim_OrdenDeSeleccion(){
		return new Texto("Aplica el algoritmo de Prim sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Rellena la siguiente tabla.",
				"Apply the Prim algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Fill the following table.");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Kruskal de Arcos del Arbol de Expansión.
	 * @return Enunciado de una pregunta del Algoritmo de Kruskal de Arcos del Arbol de Expansión.
	 */
	public static Texto enunciadoPregKruskal_ArcosDelArbolDeExpansion(){
		return new Texto("Aplica el algoritmo de Kruskal sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Indica cuáles de los siguientes arcos pertenecen al árbol de expansión resultante. ",
				"Apply the Kruskal algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Say which of the following arcs belong to the resulting spanning tree. ");
	}
	
	
	/**
	 * Devuelve el enunciado de una pregunta del Algoritmo de Kruskal de Arcos del Arbol de Expansión.
	 * @return Enunciado de una pregunta del Algoritmo de Kruskal de Arcos del Arbol de Expansión.
	 */
	public static Texto enunciadoPregKruskal_OrdenDeSeleccion(){
		return new Texto("Aplica el algoritmo de Kruskal sobre el grafo dado, tomando como origen el nodo A. "
				+ "En caso de poderse seleccionar más de un nodo, los nodos se deben escoger en orden alfabético. "
				+ "Rellena la siguiente tabla.",
				"Apply the Kruskal algorithm on the graph, taking node A as the starting node. "
				+ "When more than one node can be chosen, nodes must be selected in alphabetic order. "
				+ "Fill the following table.");
	}
	
	
	/**
	 * Devuelve el Texto asociado a las preguntas de Distancias más cortas (Se usa en preguntas de Dijkstra).
	 * @return Texto asociado a las preguntas de Distancias más cortas.
	 */
	public static Texto tipoPreguntaDijkstra_DistanciasMasCortas(){
		return new Texto("Cálculo de distancias más cortas", "Shortest distances finding");
	}
	
	
	/**
	 * Devuelve el Texto asociado a las preguntas de Ruta más corta (Se usa en preguntas de Dijkstra).
	 * @return Texto asociado a las preguntas de Ruta más corta.
	 */
	public static Texto tipoPreguntaDijkstra_RutaMasCorta(){
		return new Texto("Ruta más corta entre dos nodos", "Shortest path between two nodes");
	}
	
	
	/**
	 * Devuelve el Texto asociado a las preguntas de Arcos del árbol de expansión (Se usa en preguntas
	 * de Prim y de Kruskal).
	 * @return Texto asociado a las preguntas de Arcos del árbol de expansión.
	 */
	public static Texto tipoPregunta_ArcosDelArbolDeExpansion(){
		return new Texto("Arcos del árbol de expansión", "Spanning tree arcs");
	}
	
	
	/**
	 * Devuelve el Texto asociado a las preguntas de Orden de selección (Se usa en preguntas
	 * de Dijkstra, de Prim y de Kruskal).
	 * @return Texto asociado a las preguntas de Orden de selección.
	 */
	public static Texto tipoPregunta_OrdenDeSeleccion(){
		return new Texto("Orden de selección", "Selection order");
	}
	
}
