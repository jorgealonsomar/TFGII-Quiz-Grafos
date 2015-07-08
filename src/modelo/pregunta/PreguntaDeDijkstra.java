package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.ResultadosDijkstra;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

/**
 * Pregunta abstracta de Dijkstra.
 * @author Jorge Alonso Márquez
 */
public abstract class PreguntaDeDijkstra extends Pregunta {
	
	/**
	 * Valor numérico correspondiente a las preguntas de la clase Distancias más cortas.
	 */
	public static final int PREGUNTA_DISTANCIAS_MAS_CORTAS = 0;
	
	/**
	 * Valor numérico correspondiente a las preguntas de la clase Ruta más corta.
	 */
	public static final int PREGUNTA_RUTA_MAS_CORTA = 1;
	
	/**
	 * Valor numérico correspondiente a las preguntas de la clase Orden de selección.
	 */
	public static final int PREGUNTA_ORDEN_DE_SELECCION = 2;
	
	
	/**
	 * Resultados de aplicar el algoritmo de Dijkstra sobre el grafo asociado a la pregunta.
	 */
	protected ResultadosDijkstra resultadosDijkstra;
	
	/**
	 * Distancias de cada nodo al nodo origen.
	 */
	protected ArrayList<Integer> distanciasAlNodoOrigen;
	
	/**
	 * Camino por el que tiene que avanzad cada nodo para ir al nodo objetivo.
	 */
	protected ArrayList<Integer> caminoAlNodoObjetivo;
	
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param grafoDirigido
	 *            Si el grafo es dirigido.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDeDijkstra(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, true, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeDijkstra(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Aplica sobre el grafo el algoritmo que corresponde a las preguntas de Dijkstra
	 * (el algoritmo de Dijkstra).
	 */
	@Override
	protected void aplicarAlgoritmo(){
		resultadosDijkstra = getGrafo().algoritmoDeDijkstra(0);
		
		distanciasAlNodoOrigen = resultadosDijkstra.getDistanciasAlNodoOrigen();
	}
	
	
	/**
	 * Construye el título de esta pregunta de Dijkstra.
	 */
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregDijkstra();
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Dijkstra.
	 */
	@Override
	protected abstract void construirEnunciado();

	
	/**
	 * Construye la parte a responder de esta pregunta de Dijkstra.
	 */
	@Override
	protected abstract void construirParteAResponder();
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Dijkstra.
	 */
	@Override
	protected abstract void construirRespuestaCorrecta();
	
	
	/**
	 * Indica que los grafos usados en las preguntas de Dijkstra son ponderados.
	 * @return Si el grafo asociado a la pregunta es o no ponderado.
	 */
	@Override
	protected boolean esPonderado() {
		return true;
	}
	
	
	/**
	 * Devuelve el nombre de archivo de las preguntas de Dijkstra.
	 * @return Nombre de archivo de la pregunta.
	 */
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregDijkstra();
	}

}
