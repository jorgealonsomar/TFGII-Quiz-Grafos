package texto;

public class Textos_Preguntas {
	
	/** Inicio de una cláusula multichoice */
	public static Texto abrirClausulaMultichoice(){
		return new Texto("{1:MULTICHOICE:");
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
	
	
	/** Opción correcta en un multichoice que añade un 100 del valor al peso de la nota */
	public static Texto opcionMultichoiceCorrecta100(){
		return new Texto("%100%");
	}
	
	
	/** Opción incorrecta en un multichoice que resta un 100% del valor al peso de la nota*/
	public static Texto opcionMultichoiceQueResta100(){
		return new Texto("%-100%");
	}
	
	
	public static Texto comentarioAcierto(){
		return new Texto("#Correcto", "#Right");
	}
	
	
	public static Texto comentarioError(){
		return new Texto("#Error", "#Wrong");
	}
	
	
	/** Fragmento inicial de una pregunta sobre ordenación de grafo */
	public static Texto pregOrdenacion_ResultadoDeOrdenarGrafo(){
		return new Texto("El resultado de ordenar el grafo es el siguiente: [ ",
				"The result of sorting the graph is as follows: [ ");
	}
	
	
	/** Fragmento inicial de una pregunta sobre ordenación de grafo */
	public static Texto respuestaOrdenacion(){
		return new Texto("La respuesta correcta es: [",
				"The right answer is: [ ");
	}
	
	
	/** Título de una pregunta de Recorrido en Anchura */
	public static Texto tituloPregAnchura(){
		return new Texto("Pregunta de Recorrido en Anchura",
				"Breadth-First Search Question.");
	}
	
	
	/** Título de una pregunta de Recorrido en Profunidad */
	public static Texto tituloPregProfundidad(){
		return new Texto("Pregunta de Recorrido en Profunidad",
				"Depth-First Search Question.");
	}
	
	
	/** Título de una pregunta de Clasificación Topológica */
	public static Texto tituloPregClasificacionTopologica(){
		return new Texto("Pregunta de Clasificación Topológica",
				"Topological Sort Question.");
	}
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregAnchura(){
		return new Texto("Partiendo de A, recorre el grafo en anchura. Los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Breadth-first search into the graph. Nodes must be chosen in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregProfundidad(){
		return new Texto("Partiendo de A, recorre el grafo en profunidad. Los nodos se deben escoger en orden alfabético.",
				"Starting from A, do a Depth-first search into the graph. Nodes must be chosen in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta de Clasificación Topológica */
	public static Texto enunciadoPregClasificacionTopologica(){
		return new Texto("Realiza una clasificación topológica del grafo. Los nodos se deben escoger en orden alfabético.",
				"Do a Topological Sort of the graph. Nodes must be chosen in alphabetic order.");
	}
	
}
