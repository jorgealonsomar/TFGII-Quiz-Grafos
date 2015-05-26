package util;

import sistema.TFGII;

public class Texto {
	
	private String textoSpa = "";
	private String textoEng = "";
	
	/** Constructor de la clase con textos diferenciados para cada idioma */
	private Texto(String textoSpa, String textoEng){
		this.textoSpa = textoSpa;
		this.textoEng = textoEng;
	}
	
	
	/** Constructor de la clase con un texto universal para todo idioma */
	private Texto(String textoUniversal){
		this.textoSpa = textoUniversal;
		this.textoEng = textoUniversal;
	}
	
	
	/** Devuelve el texto en idioma español */
	public String esp(){
		return textoSpa;
	}
	
	
	/** Devuelve el texto en idioma inglés */
	public String eng(){
		return textoEng;
	}
	
	
	/** Devuelve el texto en el idioma dado */
	public String getString(Idioma idioma){
		if(idioma == Idioma.ESP){
			return esp();
		} else if(idioma == Idioma.ENG){
			return eng();
		} else {
			TFGII.LOGGER.warning("Idioma seleccionado incorrectamente");
			return null;
		}
	}
	
	
	/** Concatena el texto dado a continuación de éste */
	public void concatenar(Texto textoAConcatenar){
		textoSpa += textoAConcatenar.esp();
		textoEng += textoAConcatenar.eng();
	}
	
	
	public static String adaptarCaracteresAXml(String cadena){
		cadena = cadena.replace("á", "&aacute");
		cadena = cadena.replace("é", "&eacute");
		cadena = cadena.replace("í", "&iacute");
		cadena = cadena.replace("ó", "&oacute");
		cadena = cadena.replace("ú", "&uacute");
		
		cadena = cadena.replace("Á", "&Aacute");
		cadena = cadena.replace("É", "&Eacute");
		cadena = cadena.replace("Í", "&Iacute");
		cadena = cadena.replace("Ó", "&Oacute");
		cadena = cadena.replace("Ú", "&Uacute");
		
		cadena = cadena.replace("ñ", "&ntilde");
		cadena = cadena.replace("Ñ", "&Ñtilde");
		
		return cadena;
	}
	
	
	// -------------------------------------------------------------------------
	// Interfaz:
	// -------------------------------------------------------------------------
	
	
	/** Texto del menú Archivo */
	public static Texto menuArchivo(){
		return new Texto("Archivo", "File");
	}
	
	
	/** Texto de la opción Salir del menú Archivo */
	public static Texto menuArchivo_Salir(){
		return new Texto("Salir", "Exit");
	}
	
	
	/** Texto de la opción Importar Consigna del menú Archivo */
	public static Texto menuArchivo_ImportarConsigna(){
		return new Texto("Importar Consigna", "Import Password");
	}
	
	
	/** Texto de la opción Importar Consigna del menú Archivo */
	public static Texto introduzcaConsigna(){
		return new Texto("Introduzca la consigna: ", "Insert the password: ");
	}
	
	
	/** Texto del menú Ayuda */
	public static Texto menuAyuda(){
		return new Texto("Ayuda", "Help");
	}
	
	
	/** Texto del botón de Elegir Directorio */
	public static Texto botonElegirDirectorio() {
		return new Texto("Elegir directorio", "Select directory");
	}
	
	
	/** Tiptext de los elementos del panel de Elegir Directorio */
	public static Texto tipTextElegirDirectorio() {
		return new Texto("Elige el directorio donde se guardarán las preguntas que se generen",
				"Choose a directory in witch the generated questions will be saved");
	}
	
	
	/** Tiptext del área donde se muestran las preguntas que se van generando */
	public static Texto tipTextAreaPreguntas() {
		return new Texto("Aquí se muestran las preguntas que se van generando",
				"This area displays the questions as they are generated");
	}
	
	
	/** Tiptext del área donde se muestran las preguntas que se van generando */
	public static Texto tipTextPorcentajeArcos() {
		return new Texto("Porcentaje de arcos que se añadirán al grafo. " 
				+ "0% para que exista únicamente un árbol de expansión mínimo para el grafo. "
				+"\n100% para que el grafo sea completamente conexo.",
				"Percentage of arcs that will be added to the graph. "
				+ "0% means there will be only a minimun spanning tree for the graph. "
				+ "100% means the graph will be totally connected."
						);
	}
	
	
	/** Nombre de las preguntas de Recorrido en Profundidad */
	public static Texto recorridoEnProfundidad(){
		return new Texto("Recorrido en Profundidad", "Depth First Search");
	}
	
	
	/** Nombre de las preguntas de Recorrido en Anchura */
	public static Texto recorridoEnAnchura(){
		return new Texto("Recorrido en Anchura", "Breadth First Search");
	}
	
	
	/** Nombre de las preguntas de Clasificación Topológica */
	public static Texto clasificacionTopologica(){
		return new Texto("Clasificación Topológica", "Topological Sort");
	}
	
	
	/** Nombre de las preguntas de Algoritmo de Dijkstra */
	public static Texto algoritmoDeDijkstra(){
		return new Texto("Algoritmo de Dijkstra", "Dijkstra's Algorithm");
	}
	
	
	/** Nombre de las preguntas de Algoritmo de Prim */
	public static Texto algoritmoDePrim(){
		return new Texto("Algoritmo de Prim", "Prim's Algorithm");
	}
	
	
	/** Nombre de las preguntas de Algoritmo de Kruskal */
	public static Texto algoritmoDeKruskal(){
		return new Texto("Algoritmo de Kruskal", "Kruskal Algorithm");
	}
	
	
	/** Boton de selección del idioma de la aplicación */
	public static Texto botonSeleccionDeIdioma(){
		return new Texto("Idioma seleccionado: Español", "Selected language: English");
	}
	
	
	/** Texto junto al selector del número de preguntas a generar */
	public static Texto textoNumPreguntas(){
		return new Texto("Número de preguntas: ", "Number of questions: ");
	}
	
	
	/** Texto junto al selector del número de nodos que tendrá el grafo */
	public static Texto textoNumNodos(){
		return new Texto("Número de nodos: ", "Number of nodes: ");
	}
	
	
	/** Texto junto al selector del porcentaje de arcos que tendrá el grafo */
	public static Texto textoPorcentajeArcos(){
		return new Texto("Porcentaje de arcos: ", "Percentage of arcs: ");
	}
	
	
	/** Texto junto al selector de si el grafo es o no dirigido */
	public static Texto textoGrafoDirigido() {
		return new Texto("Grafo dirigido: ", "Directed graph: ");
	}
	
	
	/** Botón de Generar Pregunta existente en cada Pestaña de Pregunta */
	public static Texto botonGenerarPregunta(){
		return new Texto("Generar Pregunta", "Generate Question");
	}
	
	
	/** Texto del mensaje de error que salta al intentar generar las preguntas
	 * en un directorio que no existe */
	public static Texto errorDirectorioNoExiste(){
		return new Texto("El directorio seleccionado no existe.", "The selected directory does not exist.");
	}
	
	
	/** Texto del mensaje de error que salta al intentar generar las preguntas
	 * en un directorio que no existe */
	public static Texto errorConsignaIncorrecta(){
		return new Texto("La consigna introducida no es válida.", "The provided password is not valid.");
	}
	
	
	// -------------------------------------------------------------------------
	// Preguntas:
	// -------------------------------------------------------------------------
	
	
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
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregAnchura(){
		return new Texto("Partiendo de A, recorre el grafo en anchura. Los nodos se deben escoger en orden alfabético. ",
				"Starting from A, do a Breadth-first search into the graph. Nodes must be chosen in alphabetic order.");
	}
	
	
	/** Enunciado de una pregunta de Recorrido en Anchura */
	public static Texto enunciadoPregProfundidad(){
		return new Texto("Partiendo de A, recorre el grafo en profunidad. Los nodos se deben escoger en orden alfabético. ",
				"Starting from A, do a Depth-first search into the graph. Nodes must be chosen in alphabetic order.");
	}
	

	// -------------------------------------------------------------------------
	// Archivos:
	// -------------------------------------------------------------------------
	
	
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
