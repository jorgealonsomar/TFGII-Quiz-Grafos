package texto;

public class Textos_Interfaz {
	
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
	public static Texto tipTextNumPreguntas() {
		return new Texto("Número de preguntas que se generarán", "Number of question being generated");
	}
	
	
	/** Tiptext del área donde se muestran las preguntas que se van generando */
	public static Texto tipTextNumNodos() {
		return new Texto("Número de nodos que tendrá el grafo asociado a la pregunta",
				"Number of nodes of the graph associated to the question");
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
	
	
	/** Texto de la opción Importar Semilla del menú Archivo */
	public static Texto botonImportarSemilla(){
		return new Texto("Importar semilla", "Import seed");
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
	
	
	/** Texto junto al selector del modo de visualización del grafo */
	public static Texto textoVisualizacionGrafo() {
		return new Texto("Modo de visualización del grafo: ", "Graph visualization mode: ");
	}
	
	
	/** Texto asociado a la visualización de los grafos en forma de matriz de adyacencia */
	public static Texto visualizacionMatrizAdyacencia(){
		return new Texto("Matriz de adyacencia", "Adjacency matrix");
	}
	
	
	/** Texto asociado a la visualización de los grafos en forma de lista de adyacencia */
	public static Texto visualizacionListaAdyacencia(){
		return new Texto("Lista de adyacencia", "Adjacency list");
	}
	
	
	/** Texto asociado a la visualización de los grafos en forma de grafo visual */
	public static Texto visualizacionGrafoVisual(){
		return new Texto("Grafo (visual)", "Graph (visual)");
	}
	
	
	/** Texto junto al selector del tipo de pregunta */
	public static Texto textoTipoDePregunta() {
		return new Texto("Tipo de pregunta: ", "Type of question: ");
	}
	
	
	/** Botón de Generar Pregunta existente en cada Pestaña de Pregunta */
	public static Texto botonGenerarPregunta(){
		return new Texto("Generar y exportar pregunta", "Generate & export question");
	}
	
	
	/** Tiptext del botón de Generar Pregunta existente en cada Pestaña de Pregunta */
	public static Texto tipTextBotonGenerarPregunta(){
		return new Texto("Genera una pregunta, y, si se ha elegido un directorio, la exporta al mismo",
				"Generate a new question and, if a directory has been selected, it's exported there");
	}
	
	
	/** Tiptext del botón de Generar Pregunta existente en cada Pestaña de Pregunta */
	public static Texto tipTextBotonImportarSemilla(){
		return new Texto("Importa una pregunta a partir de una semilla",
				"Imports a question using the selected seed");
	}
	
	
	/** Texto del mensaje de error que salta al intentar generar las preguntas
	 * en un directorio que no existe */
	public static Texto errorDirectorioNoExiste(){
		return new Texto("El directorio seleccionado no existe.", "The selected directory does not exist.");
	}
	
	
	/** Texto del mensaje de error que salta al intentar generar las preguntas
	 * en un directorio que no existe */
	public static Texto errorSemillaIncorrecta(){
		return new Texto("La semilla introducida no es válida.", "The provided seed is not valid.");
	}
	
}
