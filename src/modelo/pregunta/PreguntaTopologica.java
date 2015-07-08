package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import modelo.grafo.GrafoDirigido;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

/**
 * Pregunta de topología.
 * @author Jorge Alonso Márquez
 */
public class PreguntaTopologica extends Pregunta {
	
	/**
	 * Nodos ordenados tras realizar la clasificación topológica sobre el grafo asociado a la
	 * pregunta.
	 */
	private ArrayList<Integer> ordenTopologico;
	
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaTopologica(Integer nNodos, Double porcentajeDeArcos,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, true, false, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaTopologica(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Aplica sobre el grafo el algoritmo que corresponde a las preguntas de topología
	 * (el algoritmo de Clasificación Topológica).
	 */
	@Override
	protected void aplicarAlgoritmo() {
		ordenTopologico = ((GrafoDirigido)getGrafo()).clasificacionTopologica();
	}
	
	
	/**
	 * Construye el título de esta pregunta de topología.
	 */
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregClasificacionTopologica();
	}
	

	/**
	 * Construye el enunciado de esta pregunta de topología.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregClasificacionTopologica();
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de topología.
	 */
	@Override
	protected void construirParteAResponder() {
		resultadoDeOrdenarElGrafo(ordenTopologico);
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de topología.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta(ordenTopologico);
	}
	
	
	/**
	 * Devuelve el número correspondiente a una pregunta de topología.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.CLASIFICACION_TOPOLOGICA;
	}
	
	
	/**
	 * Indica que los grafos usados en las preguntas de topología no son ponderados.
	 * @return Si el grafo asociado a la pregunta es o no ponderado.
	 */
	@Override
	protected boolean esPonderado() {
		return false;
	}
	
	
	/**
	 * Devuelve el nombre de archivo de las preguntas de topología.
	 * @return Nombre de archivo de la pregunta.
	 */
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregTopologica();
	}

}
