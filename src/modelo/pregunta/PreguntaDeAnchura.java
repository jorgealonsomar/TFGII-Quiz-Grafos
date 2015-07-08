package modelo.pregunta;

import java.util.ArrayList;

import modelo.Semilla;
import texto.Texto;
import texto.Textos_Archivos;
import texto.Textos_Preguntas;

/**
 * Pregunta de anchura.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDeAnchura extends Pregunta {
	
	/**
	 * Nodos ordenados tras recorrer el grafo en anchura.
	 */
	private ArrayList<Integer> recorridoEnAnchura;

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
	public PreguntaDeAnchura(Integer nNodos, Double porcentajeDeArcos, boolean grafoDirigido,
			VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, grafoDirigido, false, visualizacionGrafo);
	}

	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDeAnchura(Semilla semilla) {
		super(semilla);
	}

	
	/**
	 * Aplica sobre el grafo el algoritmo que corresponde a las preguntas de anchura
	 * (el algoritmo de Recorrido en Anchura).
	 */
	@Override
	protected void aplicarAlgoritmo() {
		recorridoEnAnchura = getGrafo().recorrerEnAnchura(0);
	}

	
	/**
	 * Construye el título de esta pregunta de anchura.
	 */
	@Override
	protected void construirTitulo() {
		titulo = Textos_Preguntas.tituloPregAnchura();
	}

	
	/**
	 * Construye el enunciado de esta pregunta de anchura.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregAnchura();
	}

	
	/**
	 * Construye la parte a responder de esta pregunta de anchura.
	 */
	@Override
	protected void construirParteAResponder() {
		resultadoDeOrdenarElGrafo(recorridoEnAnchura);
	}

	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de anchura.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		respuestaCorrecta(recorridoEnAnchura);
	}

	
	/**
	 * Devuelve el número correspondiente a una pregunta de anchura.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.RECORRIDO_EN_ANCHURA;
	}
	
	
	
	/**
	 * Indica que los grafos usados en las preguntas de anchura no son ponderados.
	 * @return Si el grafo asociado a la pregunta es o no ponderado.
	 */
	@Override
	protected boolean esPonderado() {
		return false;
	}

	
	/**
	 * Devuelve el nombre de archivo de las preguntas de anchura.
	 * @return Nombre de archivo de la pregunta.
	 */
	@Override
	public Texto getNombreDeArchivo() {
		return Textos_Archivos.nombreArchivoPregAnchura();
	}

}
