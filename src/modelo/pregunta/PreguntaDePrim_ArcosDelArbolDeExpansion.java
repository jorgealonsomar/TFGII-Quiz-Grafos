package modelo.pregunta;

import modelo.Semilla;
import modelo.grafo.ListaDeArcos;
import texto.Textos_Preguntas;

/**
 * Pregunta de Prim de la clase Arcos del árbol de expansión.
 * @author Jorge Alonso Márquez
 */
public class PreguntaDePrim_ArcosDelArbolDeExpansion extends PreguntaDePrim {
	
	/**
	 * Arcos por los que se preguntará al alumno.
	 */
	private ListaDeArcos arcosAPreguntar;
	
	
	/**
	 * Constructor de la clase.
	 * @param nNodos
	 *            Número de nodos que tendrá el grafo.
	 * @param porcentajeDeArcos
	 *            Porcentaje de arcos que tendrá el grafo.
	 * @param visualizacionGrafo
	 *            Modos en los que se mostrará el grafo.
	 */
	public PreguntaDePrim_ArcosDelArbolDeExpansion(Integer nNodos, Double porcentajeDeArcos,
			 VisualizacionGrafo visualizacionGrafo) {
		super(nNodos, porcentajeDeArcos, visualizacionGrafo);
	}
	
	
	/**
	 * Constructor de la clase. Recupera una pregunta a partir de una semilla dada.
	 * @param semilla
	 *            Semilla que contiene los datos correspondientes a esta pregunta.
	 */
	public PreguntaDePrim_ArcosDelArbolDeExpansion(Semilla semilla){
		super(semilla);
	}
	
	
	/**
	 * Aplica sobre el grafo la parte del algoritmo de Prim que corresponde a las preguntas de la clase
	 * Arcos del árbol de expansión.
	 */
	@Override
	protected void aplicarAlgoritmo() {
		super.aplicarAlgoritmo();
		
		arcosAPreguntar = aplicarAlgoritmo_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar);
	}
	
	
	/**
	 * Construye el enunciado de esta pregunta de Prim de Arcos del árbol de expansión.
	 */
	@Override
	protected void construirEnunciado() {
		enunciado = Textos_Preguntas.enunciadoPregPrim_ArcosDelArbolDeExpansion();
	}
	
	
	/**
	 * Construye la parte a responder de esta pregunta de Prim de Arcos del árbol de expansión.
	 */
	@Override
	protected void construirParteAResponder() {
		construirParteAResponder_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosDelArbolDeExpansion);
	}
	
	
	/**
	 * Construye el texto que informa de la respuesta correcta de esta pregunta de Prim de Arcos del
	 * árbol de expansión.
	 */
	@Override
	protected void construirRespuestaCorrecta() {
		construirRespuestaCorrecta_PreguntaArcosDelArbolDeExpansion(arcosAPreguntar, arcosDelArbolDeExpansion);
	}
	
	
	/**
	 * Devuelve el número correspondiente a una pregunta de Prim de Arcos del árbol de expansión.
	 */
	@Override
	protected Integer getNumPregunta() {
		return Semilla.ALGORITMO_DE_PRIM_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}
	
	
	/**
	 * Devuelve el número asociado a las preguntas de Arcos del árbol de expansión.
	 * @return Número asociado a la clase de pregunta.
	 */
	@Override
	protected Integer getClaseDePregunta() {
		return PreguntaDePrim.PREGUNTA_ARCOS_DEL_ARBOL_DE_EXPANSION;
	}

}
