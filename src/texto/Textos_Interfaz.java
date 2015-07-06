package texto;

/**
 * Textos empleados por los distintos elementos de la interfaz.
 * @author Jorge Alonso Márquez
 */
public class Textos_Interfaz {
	
	/**
	 * Devuelve el texto del botón de Elegir Directorio.
	 * @return Texto del botón de Elegir Directorio.
	 */
	public static Texto botonElegirDirectorio() {
		return new Texto("Elegir directorio", "Select directory");
	}
	
	
	/**
	 * Devuelve el tiptext de los elementos del panel de Elegir Directorio.
	 * @return Tiptext de los elementos del panel de Elegir Directorio.
	 */
	public static Texto tipTextElegirDirectorio() {
		return new Texto("Elige el directorio donde se guardarán las preguntas que se generen",
				"Choose a directory in witch the generated questions will be saved");
	}
	
	
	/**
	 * Devuelve el texto del botón de Limpiar del área de texto.
	 * @return Texto del botón de Limpiar del área de texto.
	 */
	public static Texto botonLimpiar() {
		return new Texto("Limpiar", "Clean");
	}
	
	
	/**
	 * Devuelve el tiptext del botón de Limpiar del área de texto.
	 * @return Tiptext del botón de Limpiar del área de texto.
	 */
	public static Texto tipTextBotonLimpiar() {
		return new Texto("Borra el contenido del área de preguntas",
				"Ereases the content of the questions area");
	}
	
	
	/**
	 * Devuelve el tiptext del área donde se muestran las preguntas que se van generando.
	 * @return Tiptext del área donde se muestran las preguntas que se van generando.
	 */
	public static Texto tipTextAreaPreguntas() {
		return new Texto("Aquí se muestran las preguntas que se van generando",
				"This area displays the questions as they are generated");
	}
	
	
	/**
	 * Devuelve el tiptext del selector del número de preguntas.
	 * @return Tiptext del selector del número de preguntas.
	 */
	public static Texto tipTextNumPreguntas() {
		return new Texto("Número de preguntas que se generarán", "Number of question being generated");
	}
	
	
	/**
	 * Devuelve el tiptext del selector del número de nodos.
	 * @return Tiptext del selector del número de nodos.
	 */
	public static Texto tipTextNumNodos() {
		return new Texto("Número de nodos que tendrá el grafo asociado a la pregunta",
				"Number of nodes of the graph associated to the question");
	}
	
	
	/**
	 * Devuelve el tiptext del selector del porcentaje de arcos.
	 * @return Tiptext del selector del porcentaje de arcos.
	 */
	public static Texto tipTextPorcentajeArcos() {
		return new Texto("Porcentaje de arcos que se añadirán al grafo. " 
				+ "0% para que exista únicamente un árbol de expansión mínimo para el grafo. "
				+"\n100% para que el grafo sea completamente conexo.",
				"Percentage of arcs that will be added to the graph. "
				+ "0% means there will be only a minimun spanning tree for the graph. "
				+ "100% means the graph will be totally connected."
						);
	}
	
	
	/**
	 * Devuelve el tiptext del selector de la posibilidad de arcos.
	 * @return Tiptext del selector de la posibilidad de arcos.
	 */
	public static Texto tipTextPosibilidadDeArcos() {
		return new Texto("Posibilidad de que cada arco se añada al grafo acíclico. ", 
				"Possibility of each arcs being added to the acyclic graph. ");
	}
	
	
	/**
	 * Devuelve el nombre de las preguntas de Recorrido en Profundidad.
	 * @return Nombre de las preguntas de Recorrido en Profundidad.
	 */
	public static Texto recorridoEnProfundidad(){
		return new Texto("Recorrido en Profundidad", "Depth First Search");
	}
	
	
	/**
	 * Devuelve el nombre de las preguntas de Recorrido en Anchura.
	 * @return Nombre de las preguntas de Recorrido en Anchura.
	 */
	public static Texto recorridoEnAnchura(){
		return new Texto("Recorrido en Anchura", "Breadth First Search");
	}
	
	
	/**
	 * Devuelvel el nombre de las preguntas de Clasificación Topológica.
	 * @return Nombre de las preguntas de Clasificación Topológica.
	 */
	public static Texto clasificacionTopologica(){
		return new Texto("Clasificación Topológica", "Topological Sort");
	}
	
	
	/**
	 * Devuelve el nombre de las preguntas de Algoritmo de Dijkstra.
	 * @return Nombre de las preguntas de Algoritmo de Dijkstra.
	 */
	public static Texto algoritmoDeDijkstra(){
		return new Texto("Algoritmo de Dijkstra", "Dijkstra's Algorithm");
	}
	
	
	/**
	 * Devuelve el nombre de las preguntas de Algoritmo de Prim.
	 * @return Nombre de las preguntas de Algoritmo de Prim.
	 */
	public static Texto algoritmoDePrim(){
		return new Texto("Algoritmo de Prim", "Prim's Algorithm");
	}
	
	
	/**
	 * Devuelve el nombre de las preguntas de Algoritmo de Kruskal.
	 * @return Nombre de las preguntas de Algoritmo de Kruskal.
	 */
	public static Texto algoritmoDeKruskal(){
		return new Texto("Algoritmo de Kruskal", "Kruskal Algorithm");
	}
	
	
	/**
	 * Devuelve el tiptext del botón de selección del idioma de la aplicación.
	 * @return Tiptext del botón de selección del idioma de la aplicación.
	 */
	public static Texto botonSeleccionDeIdioma(){
		return new Texto("Idioma seleccionado: Español", "Selected language: English");
	}
	
	
	/**
	 * Devuelve el texto de la opción Importar Semilla del menú Archivo.
	 * @return Texto de la opción Importar Semilla del menú Archivo.
	 */
	public static Texto botonImportarSemilla(){
		return new Texto("Importar pregunta", "Import question");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector del número de preguntas a generar.
	 * @return Texto junto al selector del número de preguntas a generar.
	 */
	public static Texto textoNumPreguntas(){
		return new Texto("Número de preguntas: ", "Number of questions: ");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector del número de nodos que tendrá el grafo.
	 * @return Texto junto al selector del número de nodos que tendrá el grafo.
	 */
	public static Texto textoNumNodos(){
		return new Texto("Número de nodos: ", "Number of nodes: ");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector del porcentaje de arcos que tendrá el grafo.
	 * @return Texto junto al selector del porcentaje de arcos que tendrá el grafo.
	 */
	public static Texto textoPorcentajeArcos(){
		return new Texto("Porcentaje de arcos: ", "Percentage of arcs: ");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector de la posibilidad de que se cree un arco. (Se muestra
	 * sólo para las preguntas de recorrido topológico)
	 * @return Texto junto al selector de la posibilidad de que se cree un arco.
	 */
	public static Texto textoPosibilidadDeArcos(){
		return new Texto("Posibilidad de arcos: ", "Possibility of arcs: ");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector de si el grafo es o no dirigido.
	 * @return Texto junto al selector de si el grafo es o no dirigido.
	 */
	public static Texto textoGrafoDirigido() {
		return new Texto("Grafo dirigido: ", "Directed graph: ");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector del modo de visualización del grafo.
	 * @return Texto junto al selector del modo de visualización del grafo.
	 */
	public static Texto textoVisualizacionGrafo() {
		return new Texto("Modo de visualización del grafo: ", "Graph visualization mode: ");
	}
	
	
	/**
	 * Devuelve el texto asociado a la visualización de los grafos en forma de matriz de adyacencia.
	 * @return Texto asociado a la visualización de los grafos en forma de matriz de adyacencia.
	 */
	public static Texto visualizacionMatrizAdyacencia(){
		return new Texto("Matriz de adyacencia", "Adjacency matrix");
	}
	
	
	/**
	 * Devuelve el texto asociado a la visualización de los grafos en forma de lista de adyacencia.
	 * @return Texto asociado a la visualización de los grafos en forma de lista de adyacencia.
	 */
	public static Texto visualizacionListaAdyacencia(){
		return new Texto("Lista de adyacencia", "Adjacency list");
	}
	
	
	/**
	 * Devuelve el texto asociado a la visualización de los grafos en forma de grafo visual.
	 * @return Texto asociado a la visualización de los grafos en forma de grafo visual.
	 */
	public static Texto visualizacionGrafoVisual(){
		return new Texto("Grafo (visual)", "Graph (visual)");
	}
	
	
	/**
	 * Devuelve el texto que va junto al selector de la clase de pregunta.
	 * @return Texto junto al selector de la clase de pregunta.
	 */
	public static Texto textoClaseDePregunta() {
		return new Texto("Clase de pregunta: ", "Class of question: ");
	}
	
	
	/**
	 * Devuelve el texto del botón de Generar Pregunta existente en cada Pestaña de Pregunta.
	 * @return Texto del botón de Generar Pregunta.
	 */
	public static Texto botonGenerarPregunta(){
		return new Texto("Generar y exportar pregunta", "Generate & export question");
	}
	
	
	/**
	 * Devuelve el tiptext del botón de Generar Pregunta existente en cada Pestaña de Pregunta.
	 * @return Tiptext del botón de Generar Pregunta.
	 */
	public static Texto tipTextBotonGenerarPregunta(){
		return new Texto("Genera una pregunta, y, si se ha elegido un directorio, la exporta al mismo",
				"Generate a new question and, if a directory has been selected, it's exported there");
	}
	
	
	/**
	 * Devuelve el tiptext del botón de Generar Pregunta existente en cada Pestaña de Pregunta.
	 * @return Tiptext del botón de Generar Pregunta.
	 */
	public static Texto tipTextBotonImportarSemilla(){
		return new Texto("Importa una pregunta a partir de una semilla",
				"Imports a question using the selected seed");
	}
	
	
	/**
	 * Devuelve el mensaje de error que salta al intentar generar las preguntas en un directorio que no
	 * existe.
	 * @return Mensaje del error al intentar generar las preguntas en un directorio que no existe.
	 */
	public static Texto errorDirectorioNoExiste(){
		return new Texto("El directorio seleccionado no existe.", "The selected directory does not exist.");
	}
	
	
	/**
	 * Devuelve el mensaje de error que salta al introducir una semilla incorrecta.
	 * @return Mensaje de error al introducir una semilla incorrecta.
	 */
	public static Texto errorSemillaIncorrecta(){
		return new Texto("La semilla introducida no es válida.", "The provided seed is not valid.");
	}
	
}
